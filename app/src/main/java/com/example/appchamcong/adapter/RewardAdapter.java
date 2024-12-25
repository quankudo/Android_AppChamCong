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
import com.example.appchamcong.domain.Reward;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;
import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {
    private List<Reward> list;
    private Context context;

    public RewardAdapter(List<Reward> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reward_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.price.setText("Số tiền: " + FormatPrice.formatNumber(list.get(position).getSotien()) +"đ");
        holder.date.setText(FormatDateTime.formatDateToString( list.get(position).getNgaytao()));
        holder.reason.setText(list.get(position).getLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price, date, reason;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textView40);
            reason = itemView.findViewById(R.id.tv_time);
            date = itemView.findViewById(R.id.text111);
        }
    }
}
