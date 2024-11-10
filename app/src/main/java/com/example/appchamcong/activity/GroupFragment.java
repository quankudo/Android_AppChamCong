package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.GroupAdapter;
import com.example.appchamcong.adapter.PersonAdapter;
import com.example.appchamcong.domain.GroupTimeKeeping;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.ArrayList;

public class GroupFragment extends Fragment {
    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ArrayList<GroupTimeKeeping> list;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        init();
        recyclerView = view.findViewById(R.id.recGroups);
        groupAdapter = new GroupAdapter(list, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupAdapter);
        return view;
    }
    public void init(){
        list = new ArrayList<>();
        GroupTimeKeeping groupTimeKeeping1 = new GroupTimeKeeping("Thiết kế web", "31/09/2024","Vị trí", 2, R.drawable.location_on_30);
        GroupTimeKeeping groupTimeKeeping2 = new GroupTimeKeeping("App chấm công mobile", "15/09/2024","Vị trí", 5, R.drawable.location_on_30);
        list.add(groupTimeKeeping1);
        list.add(groupTimeKeeping2);
    }
}