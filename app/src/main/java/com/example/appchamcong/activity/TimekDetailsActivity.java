package com.example.appchamcong.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

public class TimekDetailsActivity extends AppCompatActivity {
    TextView txtghichu;
    TextView title;
    ImageButton btnClose;
    Button chamCongHalf, chamCong, khongLyDo, btnCoLyDo;

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timek_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initMapping();
        initData();
        initEvent();
        txtghichu = findViewById(R.id.txtghichu);
        txtghichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        position = getIntent().getIntExtra("position", -1);

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
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        chamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ChamCong", "ALL");
                returnIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        chamCongHalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ChamCong", "HALF");
                returnIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        btnCoLyDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ChamCong", "COLD");
                returnIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        khongLyDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ChamCong", "KLD");
                returnIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    public void show(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_timek_details);
        ImageView cancel = dialog.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void initData(){
        title.setText("Chi tiết chấm công");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
        chamCong = findViewById(R.id.btnChamCong);
        chamCongHalf = findViewById(R.id.btn_ChamCongHalf);
        khongLyDo = findViewById(R.id.button4);
        btnCoLyDo = findViewById(R.id.btnCoLyDo);
    }

    public void toastShow() {
        Toast toast = new Toast(TimekDetailsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_successful_layout, (ViewGroup) findViewById(R.id.layout_toast_successful));
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}