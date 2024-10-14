package com.example.appchamcong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appchamcong.Fragment.LayoutCreateJonStepScanWifi;
import com.example.appchamcong.Fragment.LayoutCreateJobStepTwo;
import com.example.appchamcong.Fragment.layoutCreatJobStepOne;

public class HourlyTimekeepingStepsUsingWifiAdapter extends FragmentStateAdapter {

    public HourlyTimekeepingStepsUsingWifiAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LayoutCreateJobStepTwo();
            case 1:
                return new layoutCreatJobStepOne();
            case 2:
                return new LayoutCreateJonStepScanWifi();

            default:
                return new layoutCreatJobStepOne();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
