package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.PaymentHistoryAdapter;
import com.example.appchamcong.domain.PaymentHistory;

import java.util.ArrayList;

public class PaymentManagementActivity extends AppCompatActivity {
    ArrayList<PaymentHistory> list;
    RecyclerView recyclerView;
    TextView title;
    Button btnThanhtoan, btnxemct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initMapping();
        initData();
        initEvent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new PaymentHistoryAdapter(list));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void initEvent() {
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnxemct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentManagementActivity.this, PaymentDetailsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }

    public void initData(){
        title.setText("Quản lý thanh toán");
        list = new ArrayList<>();
        PaymentHistory paymentHistory1 = new PaymentHistory(1, 150000, "Chủ nhật, 09/05/2024 15:54");
        PaymentHistory paymentHistory2 = new PaymentHistory(2, 200000, "Thứ 2, 15/10/2024 07:30");
        list.add(paymentHistory1);
        list.add(paymentHistory2);
    }

    public void initMapping(){

        title = findViewById(R.id.title_header);
        recyclerView = findViewById(R.id.rcv_lstt);
        btnThanhtoan = findViewById(R.id.btn_thanhtoan);
        btnxemct = findViewById(R.id.btn_tt_xemct);
    }
}