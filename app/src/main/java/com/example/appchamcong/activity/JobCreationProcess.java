package com.example.appchamcong.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

import java.util.ArrayList;

public class JobCreationProcess extends AppCompatActivity {
    private ArrayList<RadioButton> radioBtnTimepeekingFormat;
    private ArrayList<RadioButton> radioBtnAdvancedIntegration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.job_creation_process);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo danh sách RadioButton
        radioBtnTimepeekingFormat = new ArrayList<>();


        // Thiết lập RadioButton cho từng lựa chọn
        setupRadioButton(R.id.byDay, R.id.radioByDay,radioBtnTimepeekingFormat );
        setupRadioButton(R.id.byShift, R.id.radioByShift, radioBtnTimepeekingFormat);
        setupRadioButton(R.id.hourly, R.id.radioHourly, radioBtnTimepeekingFormat);

        //tich hop nang cao
        radioBtnAdvancedIntegration = new ArrayList<>();


        setupRadioButton(R.id.lnlWifi, R.id.radioWifi, radioBtnAdvancedIntegration);
        setupRadioButton(R.id.lnlQR, R.id.radioQR, radioBtnAdvancedIntegration);
        setupRadioButton(R.id.lnlLocation, R.id.radioLocation, radioBtnAdvancedIntegration);
        setupRadioButton(R.id.lnlNoAdvancedIntegration, R.id.radioNoAdvancedIntegration, radioBtnAdvancedIntegration);

    }

    // Hàm thiết lập RadioButton với LinearLayout
    private void setupRadioButton(int layoutId, int radioButtonId, ArrayList<RadioButton> arr) {
        LinearLayout layout = findViewById(layoutId);
        RadioButton radioButton = findViewById(radioButtonId);
        arr.add(radioButton);

        layout.setOnClickListener(v -> {
            if (!radioButton.isChecked()) {
                radioButton.setChecked(true);
            }
        });  onCheckedChangeListener(arr);

    }

    // Listener để bỏ chọn các RadioButton khác khi một RadioButton được chọn
//    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if (isChecked) {
//                // Bỏ chọn tất cả các RadioButton khác
//                for (RadioButton radioButton : radioBtnTimepeekingFormat) {
//                    if (radioButton != buttonView) {
//                        radioButton.setChecked(false);
//                    }
//                }
//            }
//        }
//    };
    private void onCheckedChangeListener(ArrayList<RadioButton> arr){
        for(RadioButton btn : arr){
            btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        uncheckOtherRadioButtons(arr, btn);
                    }
                }
            });
        }
    }
    private void uncheckOtherRadioButtons(ArrayList<RadioButton> arr, RadioButton selectedRadioButton) {
        for (RadioButton radioButton : arr) {
            if (radioButton != selectedRadioButton) {
                radioButton.setChecked(false);
            }
        }
    }
}
