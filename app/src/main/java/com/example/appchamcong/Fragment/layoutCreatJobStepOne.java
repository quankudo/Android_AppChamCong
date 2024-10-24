package com.example.appchamcong.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TimekeepingFormApdapter;
import com.example.appchamcong.domain.TimekeepingForm;

import java.util.ArrayList;
import java.util.List;


public class layoutCreatJobStepOne extends Fragment {
    private LinearLayout lnl;
    private RadioGroup radioGroup;
    private RecyclerView rv;
    private TimekeepingFormApdapter apdapter;
    private List<TimekeepingForm> list;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_layout_creat_job_step_one, container, false);
        lnl = view.findViewById(R.id.lnlHidden1);
        radioGroup = view.findViewById(R.id.radioGroupHidden);

        rv =view.findViewById(R.id.rcvTimekeepingForm);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //add du lieu
        list = new ArrayList<>();
        TimekeepingForm tkp1 = new TimekeepingForm("Chấm công theo ngày", "Mô tả: chấm cả ngày, chấm nữa ngày...");
        list.add(tkp1);

        TimekeepingForm tkp2 = new TimekeepingForm("Chấm công theo ca", "Mô tả: chấm cả ngày, chấm nữa ngày...");
        list.add(tkp2);

        TimekeepingForm tkp3 = new TimekeepingForm("Chấm công theo giờ", "Mô tả: thời gian vào làm, tan làm...");
        list.add(tkp3);
        apdapter = new TimekeepingFormApdapter(list, getContext());

        rv.setAdapter(apdapter);

        return view;
    }
    public void hidden() {
        lnl.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
    }

}