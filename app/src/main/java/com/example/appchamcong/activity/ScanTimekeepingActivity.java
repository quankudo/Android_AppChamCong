package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanTimekeepingActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan_timekeeping);
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

        findViewById(R.id.ScanQRCode).setOnClickListener(v -> {
            startQRCodeScanner();
        });
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
    }

    private void handleScannedData(String scannedData) {

    }
}