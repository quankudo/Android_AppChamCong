package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;

import java.util.ArrayList;

public class Staff extends RecyclerView.Adapter<Staff.ViewHolder> {
    ArrayList<com.example.appchamcong.domain.Staff> list;

    public Staff(ArrayList<com.example.appchamcong.domain.Staff> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Staff.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Staff.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getValueName());
        holder.tvWorkday.setText(list.get(position).getValueWorkday() +"");
        holder.tvSalary.setText(list.get(position).getValueSalary() +"");
        holder.tvPaidLeave.setText(list.get(position).getValuePaidLeave() +" đ");
        holder.tvReceived.setText(list.get(position).getValueReceived() +" đ");
        holder.tvNotReceived.setText(list.get(position).getValueNotReceived() +" đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvWorkday, tvPaidLeave, tvSalary, tvReceived, tvNotReceived;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvWorkday = itemView.findViewById(R.id.workday);
            tvPaidLeave = itemView.findViewById(R.id.paidleave);
            tvSalary = itemView.findViewById(R.id.salary);
            tvReceived = itemView.findViewById(R.id.received);
            tvNotReceived = itemView.findViewById(R.id.notreceived);
        }
    }
}
