package com.example.appchamcong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appchamcong.Fragment.LayoutCreatJobStepOne;
import com.example.appchamcong.Fragment.LayoutCreateJobStepTwo;
import com.example.appchamcong.Fragment.LayoutCreateJobStepSix;

public class IndividualCheckIn extends FragmentStateAdapter {

    private final Fragment stepOneFragment;
    private final Fragment stepTwoFragment;
    private final Fragment stepSixFragment;

    public IndividualCheckIn(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        stepOneFragment = new LayoutCreatJobStepOne();
        stepTwoFragment = new LayoutCreateJobStepTwo();
        stepSixFragment = new LayoutCreateJobStepSix();

        // Gọi phương thức hidden() nếu đã được khai báo trong LayoutCreateJobStepOne
        if (stepOneFragment instanceof LayoutCreatJobStepOne) {
            ((LayoutCreatJobStepOne) stepOneFragment).hidden();
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return stepOneFragment;
            case 1:
                return stepTwoFragment;
            case 2:
                return stepSixFragment;
            default:
                return stepOneFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
