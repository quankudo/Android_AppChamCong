package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

public class QRCodeActivity extends AppCompatActivity {
    TextView title, QRText;
    ImageButton btnClose;
    ImageView QRImage;
    LinearLayout btnSaoChepMaNhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qrcode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        QRText = findViewById(R.id.textView10);
        QRImage = findViewById(R.id.imageView5);



        initMapping();
        initData();
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

        btnSaoChepMaNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
            }
        });
    }

    public void initData(){
        title.setText("Mã QR Code tham gia vào nhóm");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
        btnSaoChepMaNhom = findViewById(R.id.btnSaoChepMaNhom);
    }

    public void toastShow() {
        Toast toast = new Toast(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_successful_layout, findViewById(R.id.layout_toast_successful));
        TextView tv = v.findViewById(R.id.tvToast);
        tv.setText("Sao chép mã thành công");
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}