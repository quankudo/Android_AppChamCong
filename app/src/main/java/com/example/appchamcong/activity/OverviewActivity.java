package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.Staff;

import java.util.ArrayList;

public class OverviewActivity extends AppCompatActivity {
    ArrayList<com.example.appchamcong.domain.Staff> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overview);
        init();
        RecyclerView recyclerView = findViewById(R.id.recOverview);
        Staff salaryAdapter = new Staff(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(salaryAdapter);
    }

    public void init()
    {
        list = new ArrayList<>();
        com.example.appchamcong.domain.Staff s1 = new com.example.appchamcong.domain.Staff("Nguyễn Phước Kỳ", 1, 1, 0, 0, 0);
        list.add(s1);
    }
}