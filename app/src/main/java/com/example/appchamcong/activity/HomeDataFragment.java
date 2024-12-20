package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.UserViewModel;
import com.example.appchamcong.activity.GroupFragment;
import com.example.appchamcong.activity.PersonFragment;
import com.example.appchamcong.adapter.TimekeepingOptionsAdapter;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.TimekeepingOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeDataFragment extends Fragment {
    private TabLayout tabLayout;
    private ImageButton btn_add_home;
    private UserViewModel userViewModel;
    private TextView Name, Today;
    TimekeepingOptions to = new TimekeepingOptions();
    List<TimekeepingOptions> list = to.addData();
    TimekeepingOptionsAdapter adapter = new TimekeepingOptionsAdapter(list, getContext());
    @SuppressLint({"MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_data, container, false);
        replaceFragment(new PersonFragment());
        btn_add_home = (ImageButton)v.findViewById(R.id.btn_add_home);
        Name = v.findViewById(R.id.textView);
        Today = v.findViewById(R.id.textView2);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần +1
        int year = calendar.get(Calendar.YEAR);
        Today.setText("Dương lịch: " + day + "/" + month + "/" + year);

        btn_add_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(UserViewModel.class);

        LoadData();

        tabLayout = v.findViewById(R.id.tabLayout);
        TabLayout.Tab tabPersonal = tabLayout.newTab();
        tabPersonal.setCustomView(createCustomTab("Cá nhân", true));
        tabLayout.addTab(tabPersonal);

        TabLayout.Tab tabGroup = tabLayout.newTab();
        tabGroup.setCustomView(createCustomTab("Nhóm", false));
        tabLayout.addTab(tabGroup);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTab(tab, true);
                int position = tab.getPosition();
                if (position == 0) {
                    replaceFragment(new PersonFragment());
                } else {
                    replaceFragment(new GroupFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTab(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return v;
    }

    private void LoadData(){
        userViewModel.getMyInfo().observe(getActivity(), new Observer<Resource<ApiResponse<MyInfo>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<MyInfo>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Log.d("jwt", "Loading");
                        break;
                    case SUCCESS:
                        Name.setText(apiResponseResource.data.getData().getHovaten());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Log.d("jwt", "Error" + apiResponseResource.message + "login");
                        break;
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // Tạo custom view cho tab
        private View createCustomTab(String title, boolean isSelected) {
            View tabView = LayoutInflater.from(getContext()).inflate(R.layout.custom_tap, null);
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            tabTitle.setText(title);
    
            if (isSelected) {
                tabTitle.setTextColor(Color.WHITE);
                tabView.setBackgroundResource(R.drawable.tab_selected_background); // Nền xanh khi chọn
            } else {
                tabTitle.setTextColor(getResources().getColor(R.color.gray));
                tabView.setBackgroundResource(R.drawable.tab_unselected_background); // Nền xám khi chưa chọn
            }
    
            return tabView;
        }

    // Cập nhật trạng thái của tab
    private void updateTab(TabLayout.Tab tab, boolean isSelected) {
        View tabView = tab.getCustomView();
        if (tabView != null) {
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            if (isSelected) {
                tabTitle.setTextColor(Color.WHITE);
                tabView.setBackgroundResource(R.drawable.tab_selected_background);
            } else {
                tabTitle.setTextColor(getResources().getColor(R.color.gray));
                tabView.setBackgroundResource(R.drawable.tab_unselected_background);
            }
        }
    }

    public void show(){

            View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            bottomSheetDialog.setContentView(view);
            RecyclerView rcv = view.findViewById(R.id.rcvTimekeepingOptions);
            rcv.setLayoutManager(new LinearLayoutManager(getContext()));


            Log.d("TimekeepingOptionsList", "List size: " + list.size());
            // Khởi tạo Adapter và đặt nó vào RecyclerView
            adapter = new TimekeepingOptionsAdapter(list, getContext());
            rcv.setAdapter(adapter);

            bottomSheetDialog.show();


            ImageView btnClose = view.findViewById(R.id.cancel_btn);
            btnClose.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });

            Button btnNext = view.findViewById(R.id.btnNextTKPO);
            btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewJobCreationProcess.class);

                TimekeepingOptions selectedOption = adapter.getSelectedOption();
                if (selectedOption != null) {
                    String selectedName = selectedOption.getName();
                    String selectedDescription = selectedOption.getDescribe();
                    intent.putExtra("Name", selectedName);
                    intent.putExtra("Describe", selectedDescription);
                    startActivity(intent);
                }

            }
        });
    }}