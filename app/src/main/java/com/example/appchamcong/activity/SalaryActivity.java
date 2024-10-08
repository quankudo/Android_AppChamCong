package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.JoinTeamAdapter;
import com.example.appchamcong.adapter.SalaryAdapter;
import com.example.appchamcong.domain.Salary;

import java.util.ArrayList;

public class SalaryActivity extends AppCompatActivity {
    ArrayList<Salary> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salary);
        init();
        RecyclerView recyclerView = findViewById(R.id.recSalary);
        SalaryAdapter salaryAdapter = new SalaryAdapter(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(salaryAdapter);
        };

    public void init()
    {
        list = new ArrayList<>();
        Salary s1 = new Salary("Nguyễn Phước Kỳ", 1, 1, 0, 0, 0);
        list.add(s1);
    }
    }
