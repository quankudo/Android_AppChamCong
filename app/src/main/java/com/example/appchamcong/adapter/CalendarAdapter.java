package com.example.appchamcong.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.activity.GeneralFragment;
import com.example.appchamcong.activity.PersonalTimekeepActivity;
import com.example.appchamcong.activity.TimekDetailsActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<String> days;
    private Context context;
    private int EmployeeId;
    private static final int REQUEST_CODE = 1000;
    int dayDeduct = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    public CalendarAdapter(ArrayList<String> days, Context context, int EmployeeId) {
        this.days = days;
        this.context = context;
        this.EmployeeId = EmployeeId;
    }

    public CalendarAdapter(ArrayList<String> days, Context context) {
        this.days = days;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new ViewHolder(v);
    }

    public void CallIntent(int index) {
        Intent intent = new Intent(context, TimekDetailsActivity.class);
        intent.putExtra("position", index);
        if(EmployeeId!=0){
            intent.putExtra("position", index-(lastDay-dayDeduct));
            intent.putExtra("EmployeeId", EmployeeId);
        }
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE);
        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        final int index = position;
        if(days.get(position).equals("")){
            holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_black));
        }
        else if(context instanceof PersonalTimekeepActivity){
            if(days.get(position).contains("ALL")){
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_green));
            }

            else if(days.get(position).contains("HALF")){
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_blue_light));
            }

            else if(days.get(position).contains("KLD")){
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_brown));
            }

            else if(days.get(position).contains("COLD")){
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_yelllow));
            }

            else {
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_light));
            }
        }
        else {
            holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_light));
            holder.dayOfMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        if (days.get(position) != null && !days.get(position).isEmpty()) {
            try {
                int dayAtPosition = Integer.parseInt(days.get(position));
                int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                if (dayAtPosition > currentDayOfMonth) {
                    holder.dayOfMonth.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.custom_btn_black));
                    holder.dayOfMonth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String numberPart = days.get(position).replaceAll("[^0-9]", "");
        holder.dayOfMonth.setText(numberPart);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void updateData(int position, String result) {
        switch (result) {
            case "ALL" : {
                days.set(position, days.get(position) + "ALL");
                notifyItemChanged(position);
                break;
            }
            case "HALF" : {
                days.set(position, days.get(position) + "HALF");
                notifyItemChanged(position);
                break;
            }

            case "COLD" : {
                days.set(position, days.get(position) + "COLD");
                notifyItemChanged(position);
                break;
            }

            case "KLD" : {
                days.set(position, days.get(position) + "KLD");
                notifyItemChanged(position);
                break;
            }
        }
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
