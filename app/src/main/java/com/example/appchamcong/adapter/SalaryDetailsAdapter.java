package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.SalaryDetails;

import java.util.ArrayList;

public class SalaryDetailsAdapter extends RecyclerView.Adapter<SalaryDetailsAdapter.ViewHolder> {
    ArrayList<SalaryDetails> list;
    Context context;
    public SalaryDetailsAdapter(ArrayList<SalaryDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SalaryDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salary_details_child, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryDetailsAdapter.ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getDate());
        holder.price.setText(list.get(position).getTotal()+"");
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView.setAdapter(new SalaryDetailsChildAdapter(list.get(position).getList()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, price;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_salaryDetails);
            price = itemView.findViewById(R.id.price_salaryDetails);
            recyclerView = itemView.findViewById(R.id.rcv_salaryDetails);
        }
    }
}
