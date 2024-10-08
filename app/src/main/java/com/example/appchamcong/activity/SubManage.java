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
import com.example.appchamcong.adapter.ManageAdapter;
import com.example.appchamcong.domain.Manage;

import java.util.ArrayList;

public class SubManage extends AppCompatActivity {
    ArrayList<Manage> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_manage);
        init();
        RecyclerView recyclerView = findViewById(R.id.recManage);
        ManageAdapter manageAdapter = new ManageAdapter(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(manageAdapter);
}
    public void init(){
        list = new ArrayList<>();
        Manage m1 = new Manage("Nguyễn Phước Kỳ", "0346387246", "15:23 28/09/2024", 100000);
        list.add(m1);
    }
}