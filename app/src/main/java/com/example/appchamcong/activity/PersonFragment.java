package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.adapter.PersonAdapter;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.ArrayList;
import java.util.List;

public class PersonFragment extends Fragment {
    private WorkViewModel workViewModel;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        init();
        recyclerView = view.findViewById(R.id.recPersons);

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(WorkViewModel.class);

        workViewModel.getWorkPerson().observe(getActivity(), new Observer<Resource<ApiResponse<List<Person_Timekeeping>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<Person_Timekeeping>>> listResource) {
                switch (listResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Log.d("jwt", "Loading");
                        break;
                    case SUCCESS:
                        personAdapter = new PersonAdapter(listResource.data.getData(), getActivity());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(personAdapter);
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Log.d("jwt", "Error" + listResource.message);
                        break;
                }
            }
        });

        return view;
    }
    public void init(){
    }
}