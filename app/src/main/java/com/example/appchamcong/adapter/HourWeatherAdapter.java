package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Hour_Weather;

import java.util.ArrayList;

public class HourWeatherAdapter extends RecyclerView.Adapter<HourWeatherAdapter.ViewHolder> {
    ArrayList<Hour_Weather> list;

    public HourWeatherAdapter(ArrayList<Hour_Weather> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HourWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_hour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourWeatherAdapter.ViewHolder holder, int position) {
        holder.hour.setText(list.get(position).getHour()+"");
        holder.temp.setText(list.get(position).getTemperature()+"°");
        holder.icon.setImageResource(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hour, temp;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            temp = itemView.findViewById(R.id.temp);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
