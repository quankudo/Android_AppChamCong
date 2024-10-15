package com.example.appchamcong.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.ManageNotifyAdapter;
import com.example.appchamcong.domain.Notify;

import java.util.ArrayList;

public class NotifyManageActivity extends AppCompatActivity {
    ArrayList<Notify> list;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notify_manage);
        init();
        RecyclerView recyclerView = findViewById(R.id.manage_Notify_rec);
        ManageNotifyAdapter manageNotifyAdapter = new ManageNotifyAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(manageNotifyAdapter);
    }

    public void init(){
        list = new ArrayList<>();
        Notify notify1 = new Notify("Thiết kế web", "19:30");
        Notify notify2 = new Notify("Làm Game", "18:30");
        Notify notify3 = new Notify("Làm web", "19:50");
        Notify notify4 = new Notify("Làm App", "07:00");
        list.add(notify1);
        list.add(notify2);
        list.add(notify3);
        list.add(notify4);
    }

    public void initData(){
        title.setText("Quản lý thông báo");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
    }
}