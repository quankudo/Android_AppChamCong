package com.example.appchamcong.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Wifi;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LayoutCreateJonStepScanWifi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LayoutCreateJonStepScanWifi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LayoutCreateJonStepScanWifi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateJonStepScanWifi.
     */
    // TODO: Rename and change types and number of parameters
    public static LayoutCreateJonStepScanWifi newInstance(String param1, String param2) {
        LayoutCreateJonStepScanWifi fragment = new LayoutCreateJonStepScanWifi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_jon_step_scan_wifi, container, false);
        Button btn = view.findViewById(R.id.btnAddWifi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                } else {
                    showWifiBottomSheet();
                }
            }
        });
        return view;
    }
    private void showWifiBottomSheet() {
        WifiBottomSheetFragment wifiBottomSheetFragment = new WifiBottomSheetFragment();
        wifiBottomSheetFragment.show(getActivity().getSupportFragmentManager(), wifiBottomSheetFragment.getTag());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showWifiBottomSheet();
            } else {
                Toast.makeText(getActivity(), "Quyền truy cập vị trí bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onItemSelected(String selectedItem) {
        // Xử lý item được chọn
        Toast.makeText(getContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
    }
}