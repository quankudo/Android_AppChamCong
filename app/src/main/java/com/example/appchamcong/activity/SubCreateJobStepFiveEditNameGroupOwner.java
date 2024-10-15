package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.Fragment.LayoutCreateJobStepFive;
import com.example.appchamcong.R;

public class SubCreateJobStepFiveEditNameGroupOwner extends AppCompatActivity {
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_create_job_step_five_edit_name_group_owner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText groupNameEditText = findViewById(R.id.edtFullName);
                String newGroupName = groupNameEditText.getText().toString();

                // Trả về kết quả
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newGroupName", newGroupName);
                setResult(RESULT_OK, resultIntent);

            }
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubCreateJobStepFiveEditNameGroupOwner.this, NewJobCreationProcess.class);
                startActivity(intent);
            }
        });
    }
}