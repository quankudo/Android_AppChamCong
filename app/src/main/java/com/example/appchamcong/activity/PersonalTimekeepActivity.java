package com.example.appchamcong.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.CalendarAdapter;
import com.example.appchamcong.adapter.ShiftAdapter;
import com.example.appchamcong.domain.Shift;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PersonalTimekeepActivity extends AppCompatActivity {

    private TextView monthYearTxt;
    private RecyclerView recyclerView, recShift;
    private LocalDate localDate;
    private ArrayList<Shift> listShift;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_timekeep);
        initWidget();
        localDate = LocalDate.now();
        setMonthView();
        initListShift();
        setShiftView();
    }

    private void setShiftView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recShift.setLayoutManager(layoutManager);
        recShift.setAdapter(new ShiftAdapter(listShift));
    }

    private void initListShift() {
        listShift = new ArrayList<>();
        Shift shift1 = new Shift(R.color.red, "Ca toi: ", 0); listShift.add(shift1);
        Shift shift2 = new Shift(R.color.yellow, "Ca sang: ", 0); listShift.add(shift2);
        Shift shift3 = new Shift(R.color.blue, "Nhieu ca: ", 0); listShift.add(shift3);
        Shift shift4 = new Shift(R.color.white, "Tang ca: ", 0); listShift.add(shift4);
        Shift shift5 = new Shift(R.color.gray, "Chua cham: ", 0); listShift.add(shift5);
        Shift shift6 = new Shift(R.color.pink, "Nghi co ly do: ", 0); listShift.add(shift6);
        Shift shift7 = new Shift(R.color.yellow, "Nghi khong co ly do: ", 0); listShift.add(shift7);
        Shift shift8 = new Shift(R.color.red, "Nghi co luong: ", 0); listShift.add(shift8);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearTxt.setText(MonthYearFromDate(localDate));
        ArrayList<String> dayInMonth = dayInMonthArray(localDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(dayInMonth);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = YearMonth.now().lengthOfMonth();
        LocalDate firstOfMonth = localDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i =1 ; i<=42; i++) {
            if(i<=dayOfWeek || i>daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String MonthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidget() {
        recyclerView = findViewById(R.id.rec_grid_date);
        monthYearTxt = findViewById(R.id.month_year);
        recShift = findViewById(R.id.rec_work);
    }
}