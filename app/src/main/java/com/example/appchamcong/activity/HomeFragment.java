package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appchamcong.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {
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
        return view;}


    private void openBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        ImageView btnClose = view.findViewById(R.id.cancel_btn);
        btnClose.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewJobCreationProcess.class);
                startActivity(intent);
            }
        });

    }
}