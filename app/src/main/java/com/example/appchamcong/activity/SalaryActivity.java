package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.Staff;

import java.util.ArrayList;

public class SalaryActivity extends AppCompatActivity {
    ArrayList<com.example.appchamcong.domain.Staff> list;
    ImageButton btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salary);
        init();
        RecyclerView recyclerView = findViewById(R.id.recSalary);
        Staff salaryAdapter = new Staff(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(salaryAdapter);

        initMapping();
        initEvent();
    }

    private void initEvent() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    public void init()
    {
        list = new ArrayList<>();
        com.example.appchamcong.domain.Staff s1 = new com.example.appchamcong.domain.Staff("Nguyễn Phước Kỳ", 1, 1, 0, 0, 0);
        list.add(s1);
    }

    public void initMapping(){
        btnClose = findViewById(R.id.btnClose);
    }
}
