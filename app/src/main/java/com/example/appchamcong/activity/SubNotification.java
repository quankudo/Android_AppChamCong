package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.NotifyAdapter;
import com.example.appchamcong.domain.Notify;

import java.util.ArrayList;

public class SubNotification extends AppCompatActivity {
    ArrayList<Notify> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_notification);
        init();
        RecyclerView r1 = findViewById(R.id.recNotification);
        NotifyAdapter n1 = new NotifyAdapter(list);
        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(r1.getContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_line));
        r1.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        r1.setLayoutManager(linearLayoutManager);
        r1.setAdapter(n1);
    }

    public  void init(){
        list = new ArrayList<>();
        Notify n1 = new Notify(R.drawable.fingerprint_50, "Nguyễn phước kỳ đã bắt đầu chấm vào lúc 15:25 ngày 28/09/2004", "Gửi lúc 15:25");
        Notify n2 = new Notify(R.drawable.fingerprint_50, "Yêu cầu tham gia nhóm\n" +
                "Nguyễn Phước Kỳ đã yêu cầu tham \n" +
                "gia nhóm “Thiết Kế Web”", "Gửi lúc 15:25");
        list.add(n1);
        list.add(n2);
    }
}