package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.Repository.TimekeepingRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.FormatPrice;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.TimeKeepingViewModel;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.adapter.CalendarAdapter;
import com.example.appchamcong.adapter.ShiftAdapter;
import com.example.appchamcong.domain.Shift;
import com.example.appchamcong.domain.TimeKeepResponse;
import com.example.appchamcong.domain.WorkDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonalTimekeepActivity extends AppCompatActivity {
    private WorkViewModel workViewModel;
    private TimeKeepingViewModel timeKeepingViewModel;
    private static final int REQUEST_CODE = 1000;
    private TextView monthYearTxt, btnXemThem_CaNhan, btnXemChiTiet_CaNhan, btnXemTongQuan, tv_xemthongke, ChuaNhan;
    private RecyclerView recyclerView, recShift;
    private LocalDate localDate;
    private ArrayList<Shift> listShift;
    private LinearLayout ungluong, tangca, phucap, trutien, loinhuan, thanhtoan, thongke, xemthem, ln_xemthem_canhan;
    private Button btn_cham;
    ImageButton btnClose, setting_person;
    private TextView ten, tongNgayCong, tienCong1Ngay, tongNghi, tongTangCa, tongThuong, tongLuong, tongDaUng,
    tongTru, tongDaThanhToan, tongChuaNhan;
    int EmployeeId, IdNhom, IdNhanVien;

    CalendarAdapter calendarAdapter;
    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_timekeep);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        //Khi chu nhom xem thong tin nhan vien
        int IdChuNhom = intent.getIntExtra("IdChuNhom", 0);
        IdNhom = intent.getIntExtra("IdNhom", 0);
        IdNhanVien = intent.getIntExtra("IdNhanVien", 0);
        String TenNhanVien = intent.getStringExtra("TenNhanVien");

        //Cong viec ca nhan cua 1 nguoi
        int personId = intent.getIntExtra("personId", 0);
        String personName = intent.getStringExtra("personName");

        //Cong viec nhom cua 1 nhan vien
        EmployeeId = intent.getIntExtra("EmployeeId", 0);

        initWidget();

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(WorkViewModel.class);

        timeKeepingViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(TimeKeepingViewModel.class);

        if(EmployeeId!=0){
            workViewModel.getWorkDetailByEmployee(EmployeeId).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<WorkDetails>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<WorkDetails>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setDataForWork(apiResponseResource.data.getData());
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            timeKeepingViewModel.getTimeKeepings(EmployeeId).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<List<TimeKeepResponse>>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<List<TimeKeepResponse>>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setMonthView(apiResponseResource.data.getData(), EmployeeId);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }


        if(IdNhanVien!=0 && IdNhom!=0){
            workViewModel.getWorkDetailByOwner(IdNhanVien, IdNhom).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<WorkDetails>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<WorkDetails>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setDataForWork(apiResponseResource.data.getData());
                            ten.setText(apiResponseResource.data.getData().getWorkName() +" - " + TenNhanVien);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            timeKeepingViewModel.getTimeKeepingsByOwner(IdNhanVien, IdNhom).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<List<TimeKeepResponse>>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<List<TimeKeepResponse>>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setMonthView(apiResponseResource.data.getData(), 0);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        if(personId!=0){
            workViewModel.getWorkDetail(personId).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<WorkDetails>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<WorkDetails>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setDataForWork(apiResponseResource.data.getData());
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            timeKeepingViewModel.getTimeKeepings(personId).observe(PersonalTimekeepActivity.this, new Observer<Resource<ApiResponse<List<TimeKeepResponse>>>>() {
                @Override
                public void onChanged(Resource<ApiResponse<List<TimeKeepResponse>>> apiResponseResource) {
                    switch (apiResponseResource.status) {
                        case LOADING:
                            // Hiển thị trạng thái loading
                            Toast.makeText(PersonalTimekeepActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            setMonthView(apiResponseResource.data.getData(), 0);
                            break;
                        case ERROR:
                            // Hiển thị thông báo lỗi
                            Toast.makeText(PersonalTimekeepActivity.this, "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        initEvent();
        localDate = LocalDate.now();
        initListShift();
        setShiftView();
    }

    private void setDataForWork(WorkDetails data) {
        ten.setText(data.getWorkName());
        tongChuaNhan.setText(FormatPrice.formatNumber(data.getChuaThanhToan()) + "đ");
        tongLuong.setText(FormatPrice.formatNumber(data.getTienCong())+ "đ");
        tongTangCa.setText(FormatPrice.formatNumber(data.getTongTangCa()) + "đ");
        tongThuong.setText(FormatPrice.formatNumber(data.getTongThuong()) + "đ");
        tongNghi.setText(FormatPrice.formatNumber(data.getTongNghiCoLuong()) + "đ");
        tongDaUng.setText(FormatPrice.formatNumber(data.getTongDaUng()) + "đ");
        tongTru.setText(FormatPrice.formatNumber(data.getTruTien()) + "đ");
        ChuaNhan.setText(FormatPrice.formatNumber(data.getChuaThanhToan()) + " đ");
    }

    private void initEvent() {
        ungluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, SalaryAdvanceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        tangca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, OvertimeActivity.class);
                intent.putExtra("EmployeeId", EmployeeId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        phucap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, RewardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        trutien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, DeductMoneyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        loinhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, ProfitManageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, PaymentManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btn_cham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, TimekDetailsActivity.class);
                if(EmployeeId!=0){
                    intent.putExtra("position", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    intent.putExtra("EmployeeId", EmployeeId);
                }
                else if(IdNhom!=0 || IdNhanVien!=0){
                    intent.putExtra("IdNhom", IdNhom);
                    intent.putExtra("IdNhanVien", IdNhanVien);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemThem_CaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnXemThem_CaNhan.getText().equals("Xem thêm")) {
                    ln_xemthem_canhan.setVisibility(View.VISIBLE);
                    btnXemThem_CaNhan.setText("Thu gon");
                }
                else {
                    ln_xemthem_canhan.setVisibility(View.GONE);
                    btnXemThem_CaNhan.setText("Xem thêm");
                }
            }
        });

        btnXemTongQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, OverviewActivity.class);
                intent.putExtra("TYPE", "Person");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnXemChiTiet_CaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalTimekeepActivity.this, SalaryDetailsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

//        tv_xemthongke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PersonalTimekeepActivity.this, SalaryDetailsActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//            }
//        });

        setting_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void setShiftView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recShift.setLayoutManager(layoutManager);
        recShift.setAdapter(new ShiftAdapter(listShift));
    }

    private void initListShift() {
        listShift = new ArrayList<>();
        Shift shift1 = new Shift(ContextCompat.getColor(this, R.color.red), "Ca toi: ", 0); listShift.add(shift1);
        Shift shift2 = new Shift(ContextCompat.getColor(this, R.color.yellow), "Ca sang: ", 0); listShift.add(shift2);
        Shift shift3 = new Shift(ContextCompat.getColor(this, R.color.blue), "Nhieu ca: ", 0); listShift.add(shift3);
        Shift shift4 = new Shift(ContextCompat.getColor(this, R.color.white), "Tang ca: ", 0); listShift.add(shift4);
        Shift shift5 = new Shift(ContextCompat.getColor(this, R.color.green), "Chua cham: ", 0); listShift.add(shift5);
        Shift shift6 = new Shift(ContextCompat.getColor(this, R.color.gray), "Nghi co ly do: ", 0); listShift.add(shift6);
        Shift shift7 = new Shift(ContextCompat.getColor(this, R.color.pink), "Nghi khong co ly do: ", 0); listShift.add(shift7);
        Shift shift8 = new Shift(ContextCompat.getColor(this, R.color.green), "Nghi co luong: ", 0); listShift.add(shift8);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(List<TimeKeepResponse> list, int EmployId) {
        monthYearTxt.setText(MonthYearFromDate(localDate));
        ArrayList<String> dayInMonth = dayInMonthArray(localDate, list);
        if(EmployId!=0){
            calendarAdapter = new CalendarAdapter(dayInMonth, this, EmployId);
        }
        else {
            calendarAdapter = new CalendarAdapter(dayInMonth, this);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date, List<TimeKeepResponse> list) {
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
                boolean flag = false;
                for(TimeKeepResponse time : list){
                    if(time.getNgay()==(i-dayOfWeek)){
                        daysInMonthArray.add(String.valueOf(i-dayOfWeek) + time.getLoaiChamCong());
                        flag=true;
                    }
                }
                if(!flag){
                    daysInMonthArray.add(String.valueOf(i-dayOfWeek));
                }
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
        ungluong = findViewById(R.id.ungluong_gr);
        tangca = findViewById(R.id.tangca_gr);
        phucap = findViewById(R.id.phucap_gr);
        trutien = findViewById(R.id.trutien_gr);
        loinhuan = findViewById(R.id.loinhuan_gr);
        thanhtoan = findViewById(R.id.thanhtoan_gr);
        thongke = findViewById(R.id.thongke_gr);
        xemthem = findViewById(R.id.xemthem_gr);
        btn_cham = findViewById(R.id.btn_attendance);
        btnClose = findViewById(R.id.btnClose);
        ln_xemthem_canhan = findViewById(R.id.ln_xemthem_canhan);
        ln_xemthem_canhan.setVisibility(View.GONE);
        btnXemThem_CaNhan = findViewById(R.id.btnXemThem_CaNhan);
        btnXemChiTiet_CaNhan = findViewById(R.id.btnXemChiTiet_CaNhan);
        btnXemTongQuan = findViewById(R.id.textView13);
        tv_xemthongke = findViewById(R.id.tv_xemthongke);
        setting_person = findViewById(R.id.setting_person);

        //Thong tin chi tiet cong viec ca nhan
        ten = findViewById(R.id.ten);
        tongNgayCong = findViewById(R.id.tongNgayCong);
        tienCong1Ngay = findViewById(R.id.tienCong1Ngay);
        tongNghi = findViewById(R.id.tongNghi);
        tongTangCa = findViewById(R.id.tongTangCa);
        tongThuong = findViewById(R.id.tongThuong);
        tongLuong = findViewById(R.id.tongLuong);
        tongDaUng = findViewById(R.id.tongDaUng);
        tongTru = findViewById(R.id.tongTruTien);
        tongDaThanhToan = findViewById(R.id.tongDaThanhToan);
        tongChuaNhan = findViewById(R.id.tongChuaNhan);
        ChuaNhan = findViewById(R.id.textView14);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            String result = data.getStringExtra("ChamCong");
            // Cập nhật lại Adapter hoặc RecyclerView dựa trên dữ liệu trả về, nếu cần
            if (position != -1) {
                // Cập nhật item tại vị trí position trong adapter
                calendarAdapter.updateData(position, result); // Ví dụ: Gọi phương thức trong adapter để cập nhật item
            }
        }
    }

    private void openBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_setting, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}