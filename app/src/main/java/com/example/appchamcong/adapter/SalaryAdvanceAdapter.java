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
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;
import java.util.List;

public class SalaryAdvanceAdapter extends RecyclerView.Adapter<SalaryAdvanceAdapter.ViewHolder> {
    private List<SalaryAdvance> list;
    private Context context;

    public SalaryAdvanceAdapter(List<SalaryAdvance> list, Context context) {
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
        holder.price.setText("Số tiền: " + FormatPrice.formatNumber( list.get(position).getPrice()) + "đ");
        holder.date.setText(FormatDateTime.formatDateToString(list.get(position).getNgayung()));
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
