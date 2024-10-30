package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.MinusMoney;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;

public class MinusMoneyAdapter extends RecyclerView.Adapter<MinusMoneyAdapter.ViewHolder> {
    private ArrayList<MinusMoney> list;
    private Context context;

    public MinusMoneyAdapter(ArrayList<MinusMoney> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_minus_money_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.price.setText("Số tiền: " + list.get(position).getPrice()+ ",000 đ");
        holder.reason.setText(list.get(position).getReason());
        holder.minutes.setText(list.get(position).getMinutes()+" Phut");
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price, date, reason, minutes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textView40);
            reason = itemView.findViewById(R.id.tv_time);
            date = itemView.findViewById(R.id.text111);
            minutes = itemView.findViewById(R.id.text112);
        }
    }
}
