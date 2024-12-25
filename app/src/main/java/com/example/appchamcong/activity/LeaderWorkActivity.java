package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.FormatPrice;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.domain.JoinTeam;
import com.example.appchamcong.domain.WorkDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Calendar;
import java.util.List;

public class LeaderWorkActivity extends AppCompatActivity {
    private WorkViewModel workViewModel;
    private TabLayout tabLayout;
    LinearLayout btnNhanvien, btnXemThem, btnXetDuyet, btnLoiNhuan_Nhom;
    TextView btnBangLuongTheoThang, btnXemTongQuan, workname;
    ImageButton btnThongBao, btnSettings_Nhom;
    private TextView month, tongChuaThanhToan, tongTienCong, tongDaUng;
    private String qrcodeText = "";
    private List<JoinTeam> listJoinWork;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        String groupDate = intent.getStringExtra("groupDate");
        int groupId = intent.getIntExtra("groupId", 1);

        initMapping();
        workname.setText(groupName);
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        month.setText("Tiền công tháng "+ thang);

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(WorkViewModel.class);

        workViewModel.getSubJoinWork(groupId).observe(LeaderWorkActivity.this, new Observer<Resource<ApiResponse<List<JoinTeam>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<JoinTeam>>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(LeaderWorkActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        listJoinWork = apiResponseResource.data.getData();

                        btnXetDuyet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(listJoinWork.size()>0){
                                    Intent intent = new Intent(LeaderWorkActivity.this, SubJoinTeam.class);
                                    intent.putExtra("groupId", groupId);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                }
                                else {
                                    Intent intent = new Intent(LeaderWorkActivity.this, ConsiderJoinTeamActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                }
                            }
                        });
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(LeaderWorkActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        workViewModel.getWorkDetail(groupId).observe(LeaderWorkActivity.this, new Observer<Resource<ApiResponse<WorkDetails>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<WorkDetails>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(LeaderWorkActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        setDataForWork(apiResponseResource.data.getData());
                        qrcodeText = apiResponseResource.data.getData().getQrCode();
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(LeaderWorkActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        tabLayout = findViewById(R.id.tabLayoutLeader);
        TabLayout.Tab tabPersonal = tabLayout.newTab();
        tabPersonal.setCustomView(createCustomTab("Chấm công", true));
        tabLayout.addTab(tabPersonal);

        TabLayout.Tab tabGroup = tabLayout.newTab();
        tabGroup.setCustomView(createCustomTab("Tổng quan", false));
        tabLayout.addTab(tabGroup);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTab(tab, true);
                int position = tab.getPosition();
                if (position == 0) {
                    replaceFragment(new TimekeepingFragment(groupId));
                } else {
                    replaceFragment(new GeneralFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTab(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        initEvent();
    }

    private void setDataForWork(WorkDetails data) {
        workname.setText(data.getWorkName());
        tongChuaThanhToan.setText(FormatPrice.formatNumber(data.getChuaThanhToan()) + "đ");
        tongTienCong.setText(FormatPrice.formatNumber(data.getTienCong())+ "đ");
        tongDaUng.setText(FormatPrice.formatNumber(data.getTongDaUng()) + "đ");
    }

    private void initEvent() {
        btnNhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SubManage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });

        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SubNotification.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnBangLuongTheoThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, SalaryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnSettings_Nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });

        btnLoiNhuan_Nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, ProfitManageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemTongQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderWorkActivity.this, OverviewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void initMapping() {
        btnNhanvien = findViewById(R.id.btn_nhanvien);
        btnXemThem  = findViewById(R.id.btnXemThem);
        btnBangLuongTheoThang = findViewById(R.id.btnBangLuongTheoThang);
        btnThongBao = findViewById(R.id.btnThongBao);
        btnSettings_Nhom = findViewById(R.id.btnSettings_Nhom);
        btnXetDuyet = findViewById(R.id.btnXetDuyet);
        btnLoiNhuan_Nhom = findViewById(R.id.btnLoiNhuan_Nhom);
        btnXemTongQuan = findViewById(R.id.textView13);


        month = findViewById(R.id.month);
        tongChuaThanhToan = findViewById(R.id.textView14);
        tongTienCong = findViewById(R.id.tongTienCong);
        tongDaUng = findViewById(R.id.tongDaUng);
        workname = findViewById(R.id.workname);
    }

    private View createCustomTab(String title, boolean isSelected) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tap, null);
        TextView tabTitle = tabView.findViewById(R.id.tab_title);
        tabTitle.setText(title);

        if (isSelected) {
            tabTitle.setTextColor(Color.WHITE);
            tabView.setBackgroundResource(R.drawable.tab_selected_background); // Nền xanh khi chọn
        } else {
            tabTitle.setTextColor(getResources().getColor(R.color.gray));
            tabView.setBackgroundResource(R.drawable.tab_unselected_background); // Nền xám khi chưa chọn
        }

        return tabView;
    }

    private void updateTab(TabLayout.Tab tab, boolean isSelected) {
        View tabView = tab.getCustomView();
        if (tabView != null) {
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            if (isSelected) {
                tabTitle.setTextColor(Color.WHITE);
                tabView.setBackgroundResource(R.drawable.tab_selected_background);
            } else {
                tabTitle.setTextColor(getResources().getColor(R.color.gray));
                tabView.setBackgroundResource(R.drawable.tab_unselected_background);
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_leader, fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("MissingInflatedId")
    private void openBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_setting, null);
        TextView qrcode = view.findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LeaderWorkActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_qrcode_custom, null);
                builder.setView(dialogView);

                ImageView img = dialogView.findViewById(R.id.image_qr);

                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    BitMatrix bitMatrix = barcodeEncoder.encode(qrcodeText, BarcodeFormat.QR_CODE, 400, 400);
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    img.setImageBitmap(bitmap); // Gán bitmap vào ImageView
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

}