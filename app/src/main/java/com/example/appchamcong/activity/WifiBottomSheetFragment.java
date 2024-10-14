package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.net.wifi.ScanResult;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.ListWifiAdapter;
import com.example.appchamcong.domain.Wifi;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class WifiBottomSheetFragment extends BottomSheetDialogFragment {

    private ListView listViewWifi;
    private WifiManager wifiManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_sheet_scan_wifi, container, false);

        listViewWifi = view.findViewById(R.id.listViewWifi);
        wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Kiểm tra nếu Wi-Fi đã được bật
        if (wifiManager.isWifiEnabled()) {
            scanWifiNetworks();
        } else {
            Toast.makeText(getContext(), "Vui lòng bật Wi-Fi", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void scanWifiNetworks() {
        // Gọi startScan để quét mạng Wi-Fi
        wifiManager.startScan();


        // Lấy danh sách mạng Wi-Fi hiện có
        @SuppressLint("MissingPermission") List<ScanResult> scanResults = wifiManager.getScanResults();

        // Tạo một mảng để chứa tên mạng Wi-Fi
        ArrayList<Wifi> listWf = new ArrayList<>();

        for (int i = 0; i < scanResults.size(); i++) {
            Wifi item = new Wifi(scanResults.get(i).SSID,scanResults.get(i).BSSID );
            listWf.add(item);
        }

        // Tạo adapter cho ListView

        ListWifiAdapter adapter = new ListWifiAdapter(getActivity(), R.layout.custom_list_wifi,listWf );
        listViewWifi.setAdapter(adapter);
    }
}
