package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.JoinTeamAdapter;
import com.example.appchamcong.adapter.NotifyAdapter;
import com.example.appchamcong.domain.JoinTeam;

import java.util.ArrayList;

public class SubJoinTeam extends AppCompatActivity {
    ArrayList<JoinTeam> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_join_team);
        inin();
        RecyclerView recyclerView = findViewById(R.id.recJoinTeam);
        JoinTeamAdapter joinTeamAdapter = new JoinTeamAdapter(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(joinTeamAdapter);
    }

    private void inin() {
        list = new ArrayList<>();
        JoinTeam j1 = new JoinTeam("Nguyen Phuoc Ky","0274267267", "Chua cap nhat");
        list.add(j1);
    }
}