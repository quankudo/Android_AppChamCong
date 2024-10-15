package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.SalaryDetailsChild;

import java.util.ArrayList;

public class SalaryDetailsChildAdapter extends RecyclerView.Adapter<SalaryDetailsChildAdapter.ViewHolder> {
    ArrayList<SalaryDetailsChild> list;

    public SalaryDetailsChildAdapter(ArrayList<SalaryDetailsChild> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SalaryDetailsChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salary_details_grchildren, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryDetailsChildAdapter.ViewHolder holder, int position) {
        if(list.get(position).getType().equals("chamcong")){
            holder.desc.setText("Ca: "+list.get(position).getShift());
        }
        else{
            holder.desc.setText(list.get(position).getReason());
        }
        holder.title.setText(list.get(position).getTitle());
        holder.icon.setImageResource(list.get(position).getIcon());
        holder.date.setText(list.get(position).getTime());
        holder.price.setText(list.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, desc, price, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon_sdg);
            title = itemView.findViewById(R.id.title_sdg);
            desc = itemView.findViewById(R.id.desc_sdg);
            price = itemView.findViewById(R.id.price_sdg);
            date = itemView.findViewById(R.id.date_sdg);
        }
    }
}
