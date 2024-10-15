package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.PersonAdapter;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.ArrayList;

public class PersonFragment extends Fragment {

    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private ArrayList<Person_Timekeeping> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        init();
        recyclerView = view.findViewById(R.id.recPersons);
        personAdapter = new PersonAdapter(list, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(personAdapter);
        return view;
    }
    public void init(){
        list = new ArrayList<>();
        Person_Timekeeping person_timekeeping1 = new Person_Timekeeping("Làm Game", "30/10/2024");
        Person_Timekeeping person_timekeeping2 = new Person_Timekeeping("Học bài", "26/10/2024");
        Person_Timekeeping person_timekeeping3 = new Person_Timekeeping("Làm App", "19/12/2024");
        list.add(person_timekeeping1);
        list.add(person_timekeeping2);
        list.add(person_timekeeping3);
    }
}