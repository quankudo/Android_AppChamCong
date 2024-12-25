package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class UpdateInfor extends AppCompatActivity {
    TextView title;
    ImageButton btnClose;
    Button btn_luu;
    EditText HoVaTen, Sdt, Email;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_infor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initMapping();

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(UserViewModel.class);

        userViewModel.getMyInfo().observe(UpdateInfor.this, new Observer<Resource<ApiResponse<MyInfo>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<MyInfo>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(UpdateInfor.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        HoVaTen.setText(apiResponseResource.data.getData().getHovaten());
                        Email.setText(apiResponseResource.data.getData().getEmail());
                        Sdt.setText(apiResponseResource.data.getData().getSdt());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(UpdateInfor.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

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

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("update", "SUCCESS");
                setResult(1001, intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    public void initData(){
        title.setText("Chỉnh sửa thông tin");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnClose = findViewById(R.id.chevLeftClose);
        btn_luu = findViewById(R.id.btn_luu);
        HoVaTen = findViewById(R.id.HoVaTen);
        Email = findViewById(R.id.Email);
        Sdt = findViewById(R.id.Sdt);
    }
}