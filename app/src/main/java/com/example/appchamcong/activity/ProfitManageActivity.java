package com.example.appchamcong.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appchamcong.R;
import com.google.android.material.tabs.TabLayout;

public class ProfitManageActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profit_manage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initMapping();
        initData();

        tabLayout = findViewById(R.id.tabLayout_profit);
        TabLayout.Tab tabPersonal = tabLayout.newTab();
        tabPersonal.setCustomView(createCustomTab("Thu vào", true));
        tabLayout.addTab(tabPersonal);

// Tạo tab nhóm và thêm vào TabLayout
        TabLayout.Tab tabGroup = tabLayout.newTab();
        tabGroup.setCustomView(createCustomTab("Chi ra", false));
        tabLayout.addTab(tabGroup);
        replaceFragment(new RetractProfitFragment());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTab(tab, true);
                int position = tab.getPosition();
                if (position == 0) {
                    replaceFragment(new RetractProfitFragment());
                } else {
                    replaceFragment(new PayOutProfitFragment());
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
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_profit, fragment);
        fragmentTransaction.commit();
    }

    // Tạo custom view cho tab
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

    public void initData(){
        title.setText("Quản lý lợi nhuận");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
    }
}