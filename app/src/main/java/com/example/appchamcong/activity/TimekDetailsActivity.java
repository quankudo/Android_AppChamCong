package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.FormatPrice;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.TimeKeepingViewModel;
import com.example.appchamcong.domain.TimeKeepResponse;
import com.example.appchamcong.domain.TimeKeepingDetails;
import com.example.appchamcong.domain.TimekeepingReq;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimekDetailsActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    private TimeKeepingViewModel timeKeepingViewModel;
    TextView txtghichu, tenCongViec, loaiChamCong, tienCong, ngayChamCong;
    TextView title;
    ImageButton btnClose;
    Button chamCongHalf, chamCong, khongLyDo, btnCoLyDo;
    EditText et, reason;
    int IdNhom, IdNhanVien, EmployeeId;
    int position;
    private String tinhCong;
    private boolean nghiPhep;
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

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền nếu chưa được cấp
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            // Quyền đã được cấp, bắt đầu quét QR
            //startQRCodeScanner();
        }

        initMapping();

        Intent intent = getIntent();
        IdNhom = intent.getIntExtra("IdNhom", 0);
        IdNhanVien = intent.getIntExtra("IdNhanVien", 0);

        position = getIntent().getIntExtra("position", -1);
        EmployeeId = getIntent().getIntExtra("EmployeeId", 0);

        timeKeepingViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(TimeKeepingViewModel.class);

        if(IdNhom!=0 && IdNhanVien!=0){
            timeKeepingViewModel.getTimeKeepingDetailsByOwner(IdNhom, IdNhanVien).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<TimeKeepingDetails>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<TimeKeepingDetails>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            tenCongViec.setText(apiResponseResource.data.getData().getTenCongViec());
                            tienCong.setText(FormatPrice.formatNumber(apiResponseResource.data.getData().getTienCong()) +"đ");

                            Calendar calendar = Calendar.getInstance();
                            int month = calendar.get(Calendar.MONTH) + 1;
                            int year = calendar.get(Calendar.YEAR);
                            ngayChamCong.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+year);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        if(EmployeeId!=0){
            timeKeepingViewModel.getTimeKeepingDetails(EmployeeId).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<TimeKeepingDetails>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<TimeKeepingDetails>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            tenCongViec.setText(apiResponseResource.data.getData().getTenCongViec());
                            tienCong.setText(FormatPrice.formatNumber(apiResponseResource.data.getData().getTienCong()) +"đ");

                            Calendar calendar = Calendar.getInstance();
                            int month = calendar.get(Calendar.MONTH) + 1;
                            int year = calendar.get(Calendar.YEAR);
                            ngayChamCong.setText(position+"/"+month+"/"+year);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        initData();
        initEvent();
        txtghichu = findViewById(R.id.txtghichu);
        txtghichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
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
                if((IdNhom!=0 && IdNhanVien!=0) || EmployeeId!=0){
                    TimekeepingReq req = new TimekeepingReq();
                    req.setIdNhanVien(IdNhanVien);
                    req.setIdCv(IdNhom);
                    req.setLyDo("");
                    req.setNghiPhep(false);
                    req.setTimeStart(0);
                    req.setSalary(0);
                    req.setNgayChamCong(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    req.setTinhCong("ALL");
                    req.setQrCode("1");
                    //req.setGhiChu(et.getText().toString());
                    if(EmployeeId!=0){
                        nghiPhep = false;
                        tinhCong = "ALL";
                        startQRCodeScanner();
                    }
                    else if(IdNhom!=0 && IdNhanVien!=0){
                        timeKeepingViewModel.timeKeepingByOwner(req).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<String>>>() {
                            @Override
                            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                                switch (apiResponseResource.status) {
                                    case LOADING:
                                        // Hiển thị trạng thái loading
                                        Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                                        break;
                                    case SUCCESS:
                                        toastShow();
                                        break;
                                    case ERROR:
                                        // Hiển thị thông báo lỗi
                                        Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                }
                else{
                    toastShow();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("ChamCong", "ALL");
                    returnIntent.putExtra("position", position);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            }
        });

        chamCongHalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((IdNhom!=0 && IdNhanVien!=0) || EmployeeId!=0){
                    TimekeepingReq req = new TimekeepingReq();
                    req.setIdNhanVien(IdNhanVien);
                    req.setIdCv(IdNhom);
                    req.setLyDo("");
                    req.setNghiPhep(false);
                    req.setTimeStart(0);
                    req.setSalary(0);
                    req.setNgayChamCong(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    req.setTinhCong("HALF");
                    req.setQrCode("1");
                    if(EmployeeId!=0){
                        nghiPhep = false;
                        tinhCong = "HALF";
                        startQRCodeScanner();
                    }
                    else if(IdNhom!=0 && IdNhanVien!=0){
                        timeKeepingViewModel.timeKeepingByOwner(req).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<String>>>() {
                            @Override
                            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                                switch (apiResponseResource.status) {
                                    case LOADING:
                                        // Hiển thị trạng thái loading
                                        Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                                        break;
                                    case SUCCESS:
                                        toastShow();
                                        break;
                                    case ERROR:
                                        // Hiển thị thông báo lỗi
                                        Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                }
                else {
                    toastShow();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("ChamCong", "HALF");
                    returnIntent.putExtra("position", position);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            }
        });

        btnCoLyDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((IdNhom!=0 && IdNhanVien!=0) || EmployeeId!=0){
                    TimekeepingReq req = new TimekeepingReq();
                    req.setIdNhanVien(IdNhanVien);
                    req.setIdCv(IdNhom);
                    req.setLyDo(reason.getText().toString());
                    req.setNghiPhep(true);
                    req.setTimeStart(0);
                    req.setSalary(0);
                    req.setNgayChamCong(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    req.setTinhCong("COLD");
                    req.setQrCode("1");
                    if(EmployeeId!=0){
                        nghiPhep = true;
                        tinhCong = "COLD";
                        startQRCodeScanner();
                    }
                    else if(IdNhom!=0 && IdNhanVien!=0){
                        timeKeepingViewModel.timeKeepingByOwner(req).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<String>>>() {
                            @Override
                            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                                switch (apiResponseResource.status) {
                                    case LOADING:
                                        // Hiển thị trạng thái loading
                                        Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                                        break;
                                    case SUCCESS:
                                        toastShow();
                                        break;
                                    case ERROR:
                                        // Hiển thị thông báo lỗi
                                        Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                }
                else {
                    toastShow();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("ChamCong", "COLD");
                    returnIntent.putExtra("position", position);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            }
        });

        khongLyDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((IdNhom!=0 && IdNhanVien!=0) || EmployeeId!=0){
                    TimekeepingReq req = new TimekeepingReq();
                    req.setIdNhanVien(IdNhanVien);
                    req.setIdCv(IdNhom);
                    req.setLyDo("");
                    req.setNghiPhep(true);
                    req.setTimeStart(0);
                    req.setSalary(0);
                    req.setNgayChamCong(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    req.setTinhCong("KLD");
                    //req.setGhiChu(et.getText().toString());
                    if(EmployeeId!=0){
                        tinhCong = "KLD";
                        nghiPhep = true;
                        startQRCodeScanner();
                    }
                    else if(IdNhom!=0 && IdNhanVien!=0){
                        req.setQrCode("1");
                        timeKeepingViewModel.timeKeepingByOwner(req).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<String>>>() {
                            @Override
                            public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                                switch (apiResponseResource.status) {
                                    case LOADING:
                                        // Hiển thị trạng thái loading
                                        Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                                        break;
                                    case SUCCESS:
                                        toastShow();
                                        break;
                                    case ERROR:
                                        // Hiển thị thông báo lỗi
                                        Toast.makeText(TimekDetailsActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                        finish();
                    }
                }
                else {
                    toastShow();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("ChamCong", "KLD");
                    returnIntent.putExtra("position", position);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

            }
        });
    }

    public void show(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_timek_details);
        et = dialog.findViewById(R.id.editText);
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
        reason = findViewById(R.id.etReason);
        tenCongViec = findViewById(R.id.textView19);
        loaiChamCong = findViewById(R.id.textView21);
        tienCong = findViewById(R.id.textView23);
        ngayChamCong = findViewById(R.id.textView27);
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

    @SuppressLint("MissingInflatedId")
    public void toastError(String errorMessage) {
        Toast toast = new Toast(TimekDetailsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_error_layout, (ViewGroup) findViewById(R.id.layout_toast_error));
        TextView tvToastError = v.findViewById(R.id.tvToastError);
        tvToastError.setText(errorMessage);
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private void startQRCodeScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Quét mã QR");
        integrator.setCameraId(0); // Sử dụng camera sau
        integrator.setBeepEnabled(true); // Bật âm thanh khi quét
        integrator.setBarcodeImageEnabled(true); // Bật chế độ lưu ảnh mã QR
        integrator.initiateScan();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, bắt đầu quét QR
                startQRCodeScanner();
            } else {
                // Quyền bị từ chối, thông báo cho người dùng
                Toast.makeText(this, "Quyền Camera bị từ chối. Không thể quét mã QR.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Xử lý kết quả sau khi quét
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Hiển thị kết quả mã QR được quét
                String scannedData = result.getContents();
                Toast.makeText(this, "Mã QR: " + scannedData, Toast.LENGTH_LONG).show();

                // Thực hiện các hành động tùy chỉnh với dữ liệu
                handleScannedData(scannedData);
            } else {
                Toast.makeText(this, "Quét mã QR thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            // Nếu mã QR không hợp lệ hoặc quá ngắn
            Toast.makeText(this, "Dữ liệu mã QR không hợp lệ.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleScannedData(String scannedData) {
        if (scannedData.length() >= 5) {
            TimekeepingReq req = new TimekeepingReq();
            req.setIdNhanVien(IdNhanVien);
            req.setIdCv(EmployeeId);
            req.setLyDo(reason.getText().toString());
            req.setNghiPhep(nghiPhep);
            req.setTimeStart(0);
            req.setSalary(0);
            req.setNgayChamCong(position);
            req.setTinhCong(tinhCong);
            req.setQrCode(scannedData);

            timeKeepingViewModel.timeKeeping(req).observe(TimekDetailsActivity.this, new Observer<Resource<ApiResponse<String>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(TimekDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            toastShow();
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            toastError("Error" + apiResponseResource.message);
                            break;
                    }
                }
            });
        }
        else {
            // Nếu mã QR không hợp lệ hoặc quá ngắn
            Toast.makeText(this, "Dữ liệu mã QR không hợp lệ.", Toast.LENGTH_SHORT).show();
        }
    }
}