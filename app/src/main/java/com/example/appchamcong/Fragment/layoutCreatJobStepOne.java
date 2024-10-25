package com.example.appchamcong.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.AdvancedIntegrationAdapter;
import com.example.appchamcong.adapter.TimekeepingFormApdapter;
import com.example.appchamcong.domain.AdvancedIntegration;
import com.example.appchamcong.domain.TimekeepingForm;

import java.util.ArrayList;
import java.util.List;


public class layoutCreatJobStepOne extends Fragment {
    private LinearLayout lnl1, lnl2;
    private RadioGroup radioGroup;
    private RecyclerView rcvTimekeepingForm,rcvAdvancedIntegration;
    private TimekeepingFormApdapter apdapterTF;
    private AdvancedIntegrationAdapter apdapterAI;

    private List<TimekeepingForm> listTF;
    private List<AdvancedIntegration> listAI;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_layout_creat_job_step_one, container, false);
        lnl1 = view.findViewById(R.id.lnlHidden1);
        lnl2 = view.findViewById(R.id.lnlHidden2);

        //HINH THUC CHAM CONG
        rcvTimekeepingForm =view.findViewById(R.id.rcvTimekeepingForm);
        rcvTimekeepingForm.setLayoutManager(new LinearLayoutManager(getContext()));

        //add du lieu
        listTF = new ArrayList<>();
        TimekeepingForm tkp1 = new TimekeepingForm("Chấm công theo ngày", "Mô tả: chấm cả ngày, chấm nữa ngày...");
        listTF.add(tkp1);

        TimekeepingForm tkp2 = new TimekeepingForm("Chấm công theo ca", "Mô tả: chấm cả ngày, chấm nữa ngày...");
        listTF.add(tkp2);

        TimekeepingForm tkp3 = new TimekeepingForm("Chấm công theo giờ", "Mô tả: thời gian vào làm, tan làm...");
        listTF.add(tkp3);
        apdapterTF = new TimekeepingFormApdapter(listTF, getContext());

        rcvTimekeepingForm.setAdapter(apdapterTF);

        //TICH HOP NANG CAO
        rcvAdvancedIntegration = view.findViewById(R.id.rcvAdvancedIntegration);
        rcvAdvancedIntegration.setLayoutManager(new LinearLayoutManager(getContext()));

        //add du lieu
        listAI = new ArrayList<>();
        AdvancedIntegration ad1 = new AdvancedIntegration("Tích hợp chấm công bằng Wifi", "Kết nối wifi tại nơi làm việc để tiến hành chấm công");
        listAI.add(ad1);

        AdvancedIntegration ad2 = new AdvancedIntegration("Tích hợp chấm công bằng mã QR Code", "Quét mã QR Code tại nơi làm việc để tiến hành chấm công");
        listAI.add(ad2);

        AdvancedIntegration ad3 = new AdvancedIntegration("Tích hợp chấm công bằng Vị trí", "Vị trí nhân viên sẽ được lưu trữ lại tại mỗi lần chấm công");
        listAI.add(ad3);

        AdvancedIntegration ad4 = new AdvancedIntegration("Không tích hợp nâng cao", "Nhân viên của bạn chỉ cần chấm công 1 lần trong ngày. Lưu ý tính năng này sẽ không cần chấm công Vào làm và Ra làm");
        listAI.add(ad4);
        apdapterAI = new AdvancedIntegrationAdapter(listAI, getContext());
        rcvAdvancedIntegration.setAdapter(apdapterAI);

        return view;
    }
    public void hidden() {
        lnl1.setVisibility(View.INVISIBLE);
        lnl2.setVisibility(View.INVISIBLE);

    }

}