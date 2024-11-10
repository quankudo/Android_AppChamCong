package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TimekeepingOptionsAdapter;
import com.example.appchamcong.domain.TimekeepingOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TimekeepingOptions to = new TimekeepingOptions();
    List<TimekeepingOptions> list = to.addData();
    TimekeepingOptionsAdapter adapter = new TimekeepingOptionsAdapter(list, getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button buttonCreateJob = view.findViewById(R.id.buttonCreateJob);
        buttonCreateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetDialog();
            }
        });

        Button btnJoinTeam = view.findViewById(R.id.buttonJoinTeam);
        return view;
    }


    private void openBottomSheetDialog() {
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



    }
}