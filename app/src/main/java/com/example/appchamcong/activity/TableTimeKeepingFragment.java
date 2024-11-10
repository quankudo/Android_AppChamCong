package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TableTimekAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TableTimeKeepingFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    ArrayList<String> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_table_time_keeping, container, false);
        initData(10, 2024);
        recyclerView = v.findViewById(R.id.rcv_table_tk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TableTimekAdapter(list));
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        return v;
    }

    public void initData(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE, dd/MM/yyyy", new Locale("vi"));

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        list = new ArrayList<>();
        for (int day = 1; day <= maxDay; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            String formattedDate = dateFormat.format(calendar.getTime());
            list.add(formattedDate);
            Log.d("List", list.get(day-1)+"");
        }
        Log.d("List", list.size()+"");
    }
}