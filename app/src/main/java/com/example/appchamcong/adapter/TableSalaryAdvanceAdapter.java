package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.TableSalaryAdvance;

import java.util.ArrayList;

public class TableSalaryAdvanceAdapter extends RecyclerView.Adapter<TableSalaryAdvanceAdapter.ViewHolder> {
    ArrayList<TableSalaryAdvance> list;

    public TableSalaryAdvanceAdapter(ArrayList<TableSalaryAdvance> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TableSalaryAdvanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_salaryadvance_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TableSalaryAdvanceAdapter.ViewHolder holder, int position) {
        holder.stt.setText(position+1+"");
        holder.date.setText(list.get(position).getDate());
        holder.price.setText(list.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, date, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.tvSTT1);
            date = itemView.findViewById(R.id.tvDate1);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }
}
