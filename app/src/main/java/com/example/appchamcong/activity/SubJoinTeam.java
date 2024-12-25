package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.adapter.JoinTeamAdapter;
import com.example.appchamcong.adapter.NotifyAdapter;
import com.example.appchamcong.domain.JoinTeam;

import java.util.ArrayList;
import java.util.List;

public class SubJoinTeam extends AppCompatActivity {
    private WorkViewModel workViewModel;
    ImageButton btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_join_team);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", 1);

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(WorkViewModel.class);


        workViewModel.getSubJoinWork(groupId).observe(SubJoinTeam.this, new Observer<Resource<ApiResponse<List<JoinTeam>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<JoinTeam>>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(SubJoinTeam.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        RecyclerView recyclerView = findViewById(R.id.recJoinTeam);
                        JoinTeamAdapter joinTeamAdapter = new JoinTeamAdapter(apiResponseResource.data.getData(), workViewModel);
                        DividerItemDecoration dividerItemDecoration = new  DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                        recyclerView.addItemDecoration(dividerItemDecoration);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubJoinTeam.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(joinTeamAdapter);
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(SubJoinTeam.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        workViewModel.getAgreeJoin().observe(SubJoinTeam.this, new Observer<Resource<ApiResponse<String>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(SubJoinTeam.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        toastShow(apiResponseResource.data.getData());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(SubJoinTeam.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        workViewModel.getRefuseJoin().observe(SubJoinTeam.this, new Observer<Resource<ApiResponse<String>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(SubJoinTeam.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        toastShow(apiResponseResource.data.getData());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(SubJoinTeam.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        initMapping();
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
    }

    public void toastShow(String mess) {
        Toast toast = new Toast(SubJoinTeam.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_successful_layout, (ViewGroup) findViewById(R.id.layout_toast_successful));
        TextView tvToast = v.findViewById(R.id.tvToast);
        tvToast.setText(mess);
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private void initMapping() {
        btnClose = findViewById(R.id.btnClose);
    }

}