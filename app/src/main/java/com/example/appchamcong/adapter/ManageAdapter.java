package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Manage;

import java.util.ArrayList;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ViewHolder> {
    ArrayList<Manage> list;

    public ManageAdapter(ArrayList<Manage> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAdapter.ViewHolder holder, int position) {
        holder.tvDate.setText(list.get(position).getValueDate());
        holder.tvSalary.setText(list.get(position).getValueSalary() + "đ");
        holder.tvNumber.setText(list.get(position).getValueNumber());
        holder.tvName.setText(list.get(position).getValueName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvNumber, tvSalary, tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvNumber = itemView.findViewById(R.id.number);
            tvSalary = itemView.findViewById(R.id.salary);
            tvDate = itemView.findViewById(R.id.dates);
        }
    }
}
