package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.adapter.GroupAdapter;
import com.example.appchamcong.adapter.TimekeepingAdapter;
import com.example.appchamcong.domain.Timekeeping;

import java.util.List;

public class TimekeepingFragment extends Fragment {
    private WorkViewModel workViewModel;
    private RecyclerView rcv;
    private TimekeepingAdapter adapter;
    private int groupId;
    public TimekeepingFragment(int groupId){
        this.groupId = groupId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timekeeping, container, false);

        rcv = view.findViewById(R.id.rcv_timekeeping);

        workViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(WorkViewModel.class);

        workViewModel.getEmployeeTimekeeping(groupId).observe(getActivity(), new Observer<Resource<ApiResponse<List<Timekeeping>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<Timekeeping>>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        adapter = new TimekeepingAdapter(apiResponseResource.data.getData(), getActivity());
                        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
                        rcv.setAdapter(adapter);
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(getActivity(), "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }
}