package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

public class QRCodeOfMe extends AppCompatActivity {
    TextView title, tieuDeMa, noidungMa;
    ImageButton btnClose;
    LinearLayout btnSaoChepMaNhom, btn_share;
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
                Toast toast = new Toast(QRCodeOfMe.this);
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.toast_successful_layout, (ViewGroup) findViewById(R.id.layout_toast_successful));
                TextView tv = v.findViewById(R.id.tvToast);
                tv.setText("Sao chép mã thành công");
                toast.setView(v);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain"); // hoặc "image/*" nếu muốn chia sẻ hình ảnh

                // Nội dung cần chia sẻ
                String shareMessage = "Đây là nội dung muốn chia sẻ!";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

//                Uri imageUri = Uri.parse("file://path/to/your/image.jpg"); // Đường dẫn đến hình ảnh
//                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                shareIntent.setType("image/*");

                // Tạo bộ chọn ứng dụng chia sẻ
                Intent chooser = Intent.createChooser(shareIntent, "Chia sẻ qua");

                // Kiểm tra nếu có ứng dụng hỗ trợ chia sẻ
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }

    public void initData(){
        title.setText("Mã QR Code của tôi");
        tieuDeMa.setText("Mã gồm các ký tự");
        noidungMa.setText("66f**********2e6");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
        tieuDeMa = findViewById(R.id.textView9);
        noidungMa = findViewById(R.id.textView10);
        btnSaoChepMaNhom = findViewById(R.id.btnSaoChepMaNhom);
        btn_share = findViewById(R.id.btn_share);
    }
}