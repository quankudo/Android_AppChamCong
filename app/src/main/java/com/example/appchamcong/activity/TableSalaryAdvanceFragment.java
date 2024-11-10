package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.TableSalaryAdvanceAdapter;
import com.example.appchamcong.domain.TableSalaryAdvance;

import java.util.ArrayList;

public class TableSalaryAdvanceFragment extends Fragment {
    View v;
    ArrayList<TableSalaryAdvance> list;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_table_salary_advance, container, false);
        initData();
        recyclerView = v.findViewById(R.id.rcv_table_tsa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TableSalaryAdvanceAdapter(list));
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        return v;
    }

    private void initData() {
        list = new ArrayList<>();
        TableSalaryAdvance salaryAdvance1 = new TableSalaryAdvance("CN, 01/09/2024", 100000);
        list.add(salaryAdvance1);
    }
}