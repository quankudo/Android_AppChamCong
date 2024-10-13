package com.example.appchamcong.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.DateAdapter;

import java.util.ArrayList;
import java.util.List;

public class addDateToLayoutBottomSheetSelectDate extends AppCompatActivity {

    private RecyclerView recyclerViewDates;
    private DateAdapter dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bottom_sheet_select_date);

        // Khởi tạo RecyclerView
        recyclerViewDates = findViewById(R.id.recyclerViewDates);
        recyclerViewDates.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách các ngày
        List<String> dateList = new ArrayList<>();
        dateList.add("Ngày cuối cùng của mỗi tháng");
        for (int i = 1; i <= 28; i++) {
            dateList.add("Ngày " + i);
        }

        // Khởi tạo Adapter và đặt nó vào RecyclerView
        dateAdapter = new DateAdapter(this, dateList);
        recyclerViewDates.setAdapter(dateAdapter);
    }
}

