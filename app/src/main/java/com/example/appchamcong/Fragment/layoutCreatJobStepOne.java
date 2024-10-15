package com.example.appchamcong.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.appchamcong.R;


public class layoutCreatJobStepOne extends Fragment {
    private LinearLayout lnl;
    private RadioGroup radioGroup;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_layout_creat_job_step_one, container, false);
        lnl = view.findViewById(R.id.lnlHidden1);
        radioGroup = view.findViewById(R.id.radioGroupHidden);
        return view;
    }
    public void hidden() {
        lnl.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
    }

}