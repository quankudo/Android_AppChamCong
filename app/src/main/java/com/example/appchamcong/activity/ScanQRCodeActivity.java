package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.adapter.GroupAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRCodeActivity extends AppCompatActivity {
    private WorkViewModel workViewModel;
    EditText input1, input2, input3, input4, input5;
    private static final int CAMERA_REQUEST_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan_qrcode);

        // Thiết lập giao diện hỗ trợ hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(WorkViewModel.class);

        initMapping();

        // Kiểm tra quyền camera
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền nếu chưa được cấp
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            // Quyền đã được cấp, bắt đầu quét QR
            //startQRCodeScanner();
        }

        // Gán sự kiện cho nút Scan QR Code
        findViewById(R.id.btnScanQRCode).setOnClickListener(v -> {
            startQRCodeScanner();
        });
    }

    private void initMapping() {
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        input5 = findViewById(R.id.input5);
    }

    // Phương thức bắt đầu quét mã QR
    private void startQRCodeScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Quét mã QR");
        integrator.setCameraId(0); // Sử dụng camera sau
        integrator.setBeepEnabled(true); // Bật âm thanh khi quét
        integrator.setBarcodeImageEnabled(true); // Bật chế độ lưu ảnh mã QR
        integrator.initiateScan();
    }

    // Xử lý yêu cầu quyền
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
    }

    private void handleScannedData(String scannedData) {
        if (scannedData.length() >= 5) {
            input1.setText(String.valueOf(scannedData.charAt(0)));
            input2.setText(String.valueOf(scannedData.charAt(1)));
            input3.setText(String.valueOf(scannedData.charAt(2)));
            input4.setText(String.valueOf(scannedData.charAt(3)));
            input5.setText(String.valueOf(scannedData.charAt(4)));

//            if ("C7B25".equals(scannedData)) {
//                Intent intent = new Intent(ScanQRCodeActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }

            workViewModel.AddMember(scannedData).observe(this, new Observer<Resource<ApiResponse<String>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<String>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(ScanQRCodeActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            Toast.makeText(ScanQRCodeActivity.this, apiResponseResource.data.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ScanQRCodeActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            toastError("Error" + apiResponseResource.message);
                            break;
                    }
                }
            });

        } else {
            // Nếu mã QR không hợp lệ hoặc quá ngắn
            Toast.makeText(this, "Dữ liệu mã QR không hợp lệ.", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("MissingInflatedId")
    public void toastError(String errorMessage) {
        Toast toast = new Toast(ScanQRCodeActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_error_layout, (ViewGroup) findViewById(R.id.layout_toast_error));
        TextView tvToastError = v.findViewById(R.id.tvToastError);
        tvToastError.setText(errorMessage);
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}