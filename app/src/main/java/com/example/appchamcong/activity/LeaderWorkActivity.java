package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TimekeepingOptionsAdapter;
import com.example.appchamcong.domain.TimekeepingOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaderWorkActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    LinearLayout btnNhanvien, btnXemThem, btnXetDuyet, btnLoiNhuan_Nhom;
    TextView btnBangLuongTheoThang, btnXemTongQuan;
    ImageButton btnThongBao, btnSettings_Nhom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        String groupDate = intent.getStringExtra("groupDate");
        int groupId = intent.getIntExtra("groupId", 1);

        tabLayout = findViewById(R.id.tabLayoutLeader);
        TabLayout.Tab tabPersonal = tabLayout.newTab();
        tabPersonal.setCustomView(createCustomTab("Chấm công", true));
        tabLayout.addTab(tabPersonal);

        TabLayout.Tab tabGroup = tabLayout.newTab();
        tabGroup.setCustomView(createCustomTab("Tổng quan", false));
        tabLayout.addTab(tabGroup);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTab(tab, true);
                int position = tab.getPosition();
                if (position == 0) {
                    replaceFragment(new TimekeepingFragment(groupId));
                } else {
                    replaceFragment(new GeneralFragment());
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

        initMapping();
        initEvent();
    }

    private void initEvent() {
        btnNhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SubManage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });

        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SubNotification.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnBangLuongTheoThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SalaryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnSettings_Nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });

        btnXetDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LeaderWorkActivity.this, ConsiderJoinTeamActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnLoiNhuan_Nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, ProfitManageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemTongQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, OverviewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void initMapping() {
        btnNhanvien = findViewById(R.id.btn_nhanvien);
        btnXemThem  = findViewById(R.id.btnXemThem);
        btnBangLuongTheoThang = findViewById(R.id.btnBangLuongTheoThang);
        btnThongBao = findViewById(R.id.btnThongBao);
        btnSettings_Nhom = findViewById(R.id.btnSettings_Nhom);
        btnXetDuyet = findViewById(R.id.btnXetDuyet);
        btnLoiNhuan_Nhom = findViewById(R.id.btnLoiNhuan_Nhom);
        btnXemTongQuan = findViewById(R.id.textView13);
    }

    private View createCustomTab(String title, boolean isSelected) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tap, null);
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_leader, fragment);
        fragmentTransaction.commit();
    }

    private void openBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_setting, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

}