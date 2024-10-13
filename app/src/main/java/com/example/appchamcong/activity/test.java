package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.DateAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView openDialog = findViewById(R.id.selectDate);
        openDialog.setOnClickListener(v -> clickOpenBottomSheetDialog());
    }

    public void clickOpenBottomSheetDialog() {
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_select_date, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);

        // Khởi tạo RecyclerView
        RecyclerView recyclerView = viewDialog.findViewById(R.id.recyclerViewDates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách ngày
        List<String> dateList = new ArrayList<>();
        dateList.add("Ngày cuối cùng của mỗi tháng");
        for (int i = 1; i <= 31; i++) {
            dateList.add("Ngày " + i);
        }

        // Khởi tạo Adapter và gán vào RecyclerView
        DateAdapter dateAdapter = new DateAdapter(this, dateList);
        recyclerView.setAdapter(dateAdapter);

        // Thiết lập sự kiện cho nút xác nhận
        Button btnConfirm = viewDialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            // Xử lý xác nhận lựa chọn ở đây
            bottomSheetDialog.dismiss();
        });
        ImageView btnClose = viewDialog.findViewById(R.id.btnCloseBottomSheet);
        btnClose.setOnClickListener(v -> {
            // Xử lý xác nhận lựa chọn ở đây
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
}
