package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;

public class SalaryAdvanceAdapter extends RecyclerView.Adapter<SalaryAdvanceAdapter.ViewHolder> {
    private ArrayList<SalaryAdvance> list;
    private Context context;

    public SalaryAdvanceAdapter(ArrayList<SalaryAdvance> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SalaryAdvanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advance_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryAdvanceAdapter.ViewHolder holder, int position) {
        holder.price.setText("Số tiền: " + list.get(position).getPrice()+ ",000 đ");
        holder.date.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textView40);
            date = itemView.findViewById(R.id.tv_time);
        }
    }
}
