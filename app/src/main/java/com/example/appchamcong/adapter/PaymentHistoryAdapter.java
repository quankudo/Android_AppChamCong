package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.PaymentHistory;

import java.util.ArrayList;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder> {
    ArrayList<PaymentHistory> list;

    public PaymentHistoryAdapter(ArrayList<PaymentHistory> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PaymentHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_history_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryAdapter.ViewHolder holder, int position) {
        holder.title.setText("Đã thanh toán lần "+ list.get(position).getIndex());
        holder.date.setText(list.get(position).getDate());
        holder.price.setText(list.get(position).getTotalPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle_ph);
            date = itemView.findViewById(R.id.date_ph);
            price = itemView.findViewById(R.id.price_ph);
        }
    }
}
