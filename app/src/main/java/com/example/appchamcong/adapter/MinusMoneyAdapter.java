package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.FormatDateTime;
import com.example.appchamcong.Utils.FormatPrice;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.domain.MinusMoney;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;
import java.util.List;

public class MinusMoneyAdapter extends RecyclerView.Adapter<MinusMoneyAdapter.ViewHolder> {
    private List<Deduct> list;
    private Context context;

    public MinusMoneyAdapter(List<Deduct> list, Context context) {
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
        holder.price.setText("Số tiền: " + FormatPrice.formatNumber( list.get(position).getSoTien()) +"đ");
        holder.reason.setText(list.get(position).getLyDo());
        holder.date.setText(FormatDateTime.formatDateToString(list.get(position).getNgayTao()));
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
        }
    }
}
