package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TimekeepingOptionsAdapter;
import com.example.appchamcong.domain.TimekeepingOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

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
        RecyclerView rcv = view.findViewById(R.id.rcvTimekeepingOptions);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<TimekeepingOptions> list = new ArrayList<>();
        TimekeepingOptionsAdapter adapter = new TimekeepingOptionsAdapter(list, getContext());
        TimekeepingOptions to1 = new TimekeepingOptions(R.drawable.user_timepicking_form, "Chấm cho cá nhân","Bạn sẽ tự chấm công cho riêng cá nhân bạn");
        list.add(to1);

        TimekeepingOptions to2 = new TimekeepingOptions(R.drawable.people, "Chấm cho nhân viên","Với tư cách là chủ, bạn sẽ tự chấm công cho nhân viên của mình");
        list.add(to2);

        TimekeepingOptions to3 = new TimekeepingOptions(R.drawable.connect, "Nhân viên tự chấm","Bạn sẽ theo dõi toàn bộ quá trình chấm công của tất cả nhân viên");
        list.add(to3);

        TimekeepingOptions to4 = new TimekeepingOptions(R.drawable.scan, "Tham gia nhóm bằng QR code","Bạn sẽ tham gia vào một công việc được tạo từ người chủ của mình");
        list.add(to4);
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
                startActivity(intent);
            }
        });

    }
}