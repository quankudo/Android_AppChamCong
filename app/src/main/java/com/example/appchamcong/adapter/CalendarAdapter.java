package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<String> days;

    public CalendarAdapter(ArrayList<String> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        if(days.get(position).equals("")){
            holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_black));
        }
        else{
            holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_light));
        }
        holder.dayOfMonth.setText(days.get(position));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dayOfMonth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.cellDayTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    getAdapterPosition(), (String) dayOfMonth.getText()
                }
            });
        }
    }
}
