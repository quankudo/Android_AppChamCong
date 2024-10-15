package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

import com.example.appchamcong.R;
import com.google.android.material.tabs.TabLayout;

public class LeaderWorkActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    LinearLayout btnNhanvien;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_work);

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
                    replaceFragment(new TimekeepingFragment());
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

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        btnNhanvien = findViewById(R.id.btn_nhanvien);
        btnNhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

}