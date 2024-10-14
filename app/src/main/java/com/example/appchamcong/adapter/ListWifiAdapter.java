package com.example.appchamcong.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Wifi;

import java.util.ArrayList;
import java.util.List;

public class ListWifiAdapter extends ArrayAdapter<Wifi> {
    Activity context;
    int layout;
    ArrayList<Wifi> myList;

    public ListWifiAdapter( Activity context, int layout, ArrayList<Wifi> myList) {
        super(context, layout, myList);
        this.context = context;
        this.layout = layout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView = myInflater.inflate(layout, null);
        Wifi wifi = myList.get(position);
        TextView nameWF = convertView.findViewById(R.id.tvNameWifi);
        nameWF.setText(wifi.getName());
        TextView MAC = convertView.findViewById(R.id.tvMAC);
        MAC.setText(wifi.getMAC());
        return convertView;

    }
}
