package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Day_Weather;

import java.util.ArrayList;

public class DayWeatherAdapter extends RecyclerView.Adapter<DayWeatherAdapter.ViewHolder> {
    ArrayList<Day_Weather> list;

    public DayWeatherAdapter(ArrayList<Day_Weather> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DayWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayWeatherAdapter.ViewHolder holder, int position) {
        holder.day.setText(list.get(position).getDay());
        holder.icon.setImageResource(list.get(position).getIcon());
        holder.fromTemp.setText(list.get(position).getFromTemperature()+"°");
        holder.toTemp.setText(list.get(position).getToTemperature()+"°");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView day, toTemp, fromTemp;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            toTemp = itemView.findViewById(R.id.toTemp);
            fromTemp = itemView.findViewById(R.id.fromTemp);
            icon = itemView.findViewById(R.id.icon1);
        }
    }
}
