package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.FormatPrice;
import com.example.appchamcong.domain.Timekeeping;

import java.util.List;

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.ViewHolder>{
    List<Timekeeping> list;
    private Context context;

    public TimekeepingAdapter(List<Timekeeping> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TimekeepingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timekeeping_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingAdapter.ViewHolder holder, int position) {
        holder.ten.setText(list.get(position).getTen());
        holder.ngayDiLam.setText("Đi làm " + list.get(position).getNgayDiLam() + " ngày");
        holder.ngayNghi.setText("Nghỉ "+ list.get(position).getNgayNghi() +" ngày");
        holder.ungluong.setText(FormatPrice.formatNumber(list.get(position).getDaUng()));
        holder.tiencong.setText(FormatPrice.formatNumber(list.get(position).getTienCong()));

        if(list.get(position).isTrangThai()){
            holder.daCham.setVisibility(View.VISIBLE);
            holder.chuaCham.setVisibility(View.GONE);
        }
        else {
            holder.daCham.setVisibility(View.GONE);
            holder.chuaCham.setVisibility(View.VISIBLE);
            holder.btnCham.setText("Cham cong "+list.get(position).getTen());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ten, ngayDiLam, ngayNghi, ungluong, tiencong;
        Button btnCham;
        LinearLayout daCham, chuaCham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.textView86);
            ngayDiLam = itemView.findViewById(R.id.textView26);
            ngayNghi = itemView.findViewById(R.id.textView87);
            ungluong = itemView.findViewById(R.id.textView28);
            tiencong = itemView.findViewById(R.id.tien_cong);
            daCham = itemView.findViewById(R.id.daCham);
            chuaCham = itemView.findViewById(R.id.chuaCham);
            btnCham = itemView.findViewById(R.id.btnCham);
        }
    }
}
