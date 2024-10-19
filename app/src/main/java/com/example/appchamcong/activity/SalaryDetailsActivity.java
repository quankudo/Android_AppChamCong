package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.SalaryDetailsAdapter;
import com.example.appchamcong.domain.SalaryDetails;
import com.example.appchamcong.domain.SalaryDetailsChild;

import java.util.ArrayList;

public class SalaryDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<SalaryDetails> list;
    TextView title;
    ImageButton btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salary_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initMapping();
        initData();
        initEvent();
        recyclerView = findViewById(R.id.recSalaryDetailsParent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new SalaryDetailsAdapter(list, this));

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

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
    }

    private void initData() {
        title.setText("09/2024 - Chi tiết lương");
        list = new ArrayList<>();
        ArrayList<SalaryDetailsChild> children1 = new ArrayList<>();
        SalaryDetailsChild child1 = new SalaryDetailsChild(R.drawable.user_32, "Cham cong", "Ca toi", 10000,"18:30", "chamcong");
        children1.add(child1);
        SalaryDetails salaryDetails1 = new SalaryDetails("THU 3, 29/09/2024", 180000, "1", children1);

        ArrayList<SalaryDetailsChild> children2 = new ArrayList<>();
        SalaryDetailsChild child2 = new SalaryDetailsChild(R.drawable.user_32, "Cham cong", "Ca sang", 10000,"07:30", "chamcong");
        children2.add(child2);
        SalaryDetails salaryDetails2 = new SalaryDetails("THU 4, 26/07/2024", 180000, "1", children2);

        ArrayList<SalaryDetailsChild> children3 = new ArrayList<>();
        SalaryDetailsChild child3 = new SalaryDetailsChild(R.drawable.user_32, "Cham cong", "Ca toi", 10000,"19:30", "chamcong");
        children3.add(child3);
        SalaryDetails salaryDetails3 = new SalaryDetails("THU 6, 10/07/2024", 180000, "1", children3);

        ArrayList<SalaryDetailsChild> children4 = new ArrayList<>();
        SalaryDetailsChild child4 = new SalaryDetailsChild(R.drawable.user_32, "Nghi co phep", 10000,"19:30", "Ly do: Dau sot ret","nghi");
        children3.add(child4);
        SalaryDetails salaryDetails4 = new SalaryDetails("THU 7, 17/05/2024", 180000, "1", children4);
        list.add(salaryDetails1);
        list.add(salaryDetails2);
        list.add(salaryDetails3);
        list.add(salaryDetails4);
    }
}