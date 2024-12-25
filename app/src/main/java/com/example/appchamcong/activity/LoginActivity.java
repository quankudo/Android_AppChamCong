package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.R;
import com.example.appchamcong.Utils.JwtUtils;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.UserViewModel;
import com.example.appchamcong.domain.User;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button submit;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String token = new JwtUtils(LoginActivity.this).getToken();

        if(token!=null && token.length()>20){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(UserViewModel.class);

        AnhXa();

        userViewModel.getLogin().observe(LoginActivity.this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> resource) {
                switch (resource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(LoginActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        new JwtUtils(LoginActivity.this).saveToken(resource.data);
                        ApiClient.getClient(LoginActivity.this);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(LoginActivity.this, "Error" + resource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(email.getText().toString(), password.getText().toString());
                userViewModel.login(user);
            }
        });
    }
    private void AnhXa() {
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        submit = findViewById(R.id.button);
    }
}