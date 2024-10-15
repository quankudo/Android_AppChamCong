package com.example.appchamcong.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appchamcong.Fragment.LayoutCreateJobStepSix;
import com.example.appchamcong.Fragment.LayoutCreateJobStepTwo;
import com.example.appchamcong.Fragment.layoutCreatJobStepOne;

import java.util.zip.Inflater;

public class TimekeepingStepsForIndividualsByTheHourAdapter extends FragmentStateAdapter {
    public TimekeepingStepsForIndividualsByTheHourAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        layoutCreatJobStepOne a = new layoutCreatJobStepOne();
        a.hidden();
        switch (position){
            case 0:
                return a;
            case 1:
                return new LayoutCreateJobStepTwo();
            case 2:
                return new LayoutCreateJobStepSix();
            default:
                return a;

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
