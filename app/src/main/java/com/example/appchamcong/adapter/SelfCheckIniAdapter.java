package com.example.appchamcong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appchamcong.Fragment.LayoutCreateJobStepFive;
import com.example.appchamcong.Fragment.LayoutCreateJobStepFour;
import com.example.appchamcong.Fragment.LayoutCreateJonStepScanWifi;
import com.example.appchamcong.Fragment.LayoutCreateJobStepTwo;
import com.example.appchamcong.Fragment.LayoutCreatJobStepOne;

public class SelfCheckIniAdapter extends FragmentStateAdapter {

    public SelfCheckIniAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LayoutCreatJobStepOne();
            case 1:
                return new LayoutCreateJobStepTwo();
            case 2:
                return new LayoutCreateJonStepScanWifi();
            case 3:
                return new LayoutCreateJobStepFour();
            case 4:
                return new LayoutCreateJobStepFive();

            default:
                return new LayoutCreatJobStepOne();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
