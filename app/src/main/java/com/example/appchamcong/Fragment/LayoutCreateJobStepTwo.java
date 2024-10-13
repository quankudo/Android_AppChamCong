package com.example.appchamcong.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.appchamcong.R;
import com.example.appchamcong.adapter.DateAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class LayoutCreateJobStepTwo extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LayoutCreateJobStepTwo() {
        // Required empty public constructor
    }

    public static LayoutCreateJobStepTwo newInstance(String param1, String param2) {
        LayoutCreateJobStepTwo fragment = new LayoutCreateJobStepTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_creat_job_step_two, container, false);

        // Khởi tạo đối tượng ImageView cho selectDate
        ImageView openDialog = view.findViewById(R.id.selectDate);
        openDialog.setOnClickListener(v -> clickOpenBottomSheetDialog());

        return view;
    }

    public void clickOpenBottomSheetDialog() {
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_select_date, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity()); // Sử dụng getActivity() để cung cấp ngữ cảnh
        bottomSheetDialog.setContentView(viewDialog);

        // Khởi tạo RecyclerView
        RecyclerView recyclerView = viewDialog.findViewById(R.id.recyclerViewDates);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Sử dụng getActivity()

        // Tạo danh sách ngày
        List<String> dateList = new ArrayList<>();
        dateList.add("Ngày cuối cùng của mỗi tháng");
        for (int i = 1; i <= 31; i++) {
            dateList.add("Ngày " + i);
        }

        // Khởi tạo Adapter và gán vào RecyclerView
        DateAdapter dateAdapter = new DateAdapter(getActivity(), dateList); // Sử dụng getActivity()
        recyclerView.setAdapter(dateAdapter);

        // Thiết lập sự kiện cho nút xác nhận
        Button btnConfirm = viewDialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            // Xử lý xác nhận lựa chọn ở đây
            bottomSheetDialog.dismiss();
        });

        // Nút đóng bottom sheet
        ImageView btnClose = viewDialog.findViewById(R.id.btnCloseBottomSheet);
        btnClose.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
}
