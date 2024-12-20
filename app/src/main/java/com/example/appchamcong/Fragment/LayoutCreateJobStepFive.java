package com.example.appchamcong.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.appchamcong.R;
import com.example.appchamcong.activity.SubCreateJobStepFiveEditNameGroupOwner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LayoutCreateJobStepFive extends Fragment {
    private ActivityResultLauncher<Intent> editGroupNameLauncher;
    private  TextView edtSelectDate, edtSelectHours;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_create_job_step_five, container, false);

        // Đăng ký ActivityResultLauncher
        editGroupNameLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        // Xử lý kết quả trả về từ SubCreateJobStepFiveEditNameGroupOwner
                        String newName = result.getData().getStringExtra("newGroupName");
                        // Ví dụ: cập nhật TextView hoặc làm gì đó với kết quả này
                        EditText nameGroupOwner = view.findViewById(R.id.editNameGroupOwner);
                        nameGroupOwner.setText( newName);
                    }
                }
        );

        // Xử lý sự kiện click
        ImageView btnEdit = view.findViewById(R.id.editNameGroupOwner);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubCreateJobStepFiveEditNameGroupOwner.class);
                editGroupNameLauncher.launch(intent);  // Sử dụng launcher để mở Activity
            }
        });

        edtSelectDate = view.findViewById(R.id.edtstarDate);
        edtSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });

        edtSelectHours = view.findViewById(R.id.edtreminderTime);
        edtSelectHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectHours();
            }
        });
        return view;
    }

    public void SelectDate(){
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtSelectDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    public void SelectHours(){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        calendar.set(0,0,0,hourOfDay, minute);
                        edtSelectHours.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, hour, minute, true);
    timePickerDialog.show();
    }
    }
