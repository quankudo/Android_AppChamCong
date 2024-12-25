package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.UserViewModel;
import com.example.appchamcong.domain.MyInfo;

public class InfoAccountActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    TextView title, Ten, Email, NgayTao;
    ImageButton btnClose;
    LinearLayout lnChinhSua, lnSaoChep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Ten = findViewById(R.id.Ten);
        Email = findViewById(R.id.EmailAccount);
        NgayTao = findViewById(R.id.NgayTao);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(UserViewModel.class);

        userViewModel.getMyInfo().observe(InfoAccountActivity.this, new Observer<Resource<ApiResponse<MyInfo>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<MyInfo>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(InfoAccountActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        Ten.setText(apiResponseResource.data.getData().getHovaten());
                        Email.setText(apiResponseResource.data.getData().getEmail());
                        NgayTao.setText(apiResponseResource.data.getData().getNgayTao());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(InfoAccountActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
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

        lnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoAccountActivity.this, UpdateInfor.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        lnSaoChep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
            }
        });
    }

    public void initData(){
        title.setText("Thông tin tài khoản");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
        lnChinhSua = findViewById(R.id.ln_chinhsuatt);
        lnSaoChep = findViewById(R.id.lnSaoChep);
    }

    public void toastShow() {
        Toast toast = new Toast(InfoAccountActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_successful_layout, (ViewGroup) findViewById(R.id.layout_toast_successful));
        TextView tvToast = v.findViewById(R.id.tvToast);
        tvToast.setText("Đã sao chép thành công");
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}