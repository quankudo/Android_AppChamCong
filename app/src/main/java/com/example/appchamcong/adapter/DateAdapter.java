package com.example.appchamcong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {

    private List<String> dateList;
    private int selectedPosition = 0; // Đặt vị trí mặc định là 0 (Ngày cuối cùng của mỗi tháng)
    private Context context;

    public DateAdapter(Context context, List<String> dateList) {
        this.context = context;
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        String date = dateList.get(position);
        holder.textViewDate.setText(date);
        holder.textViewDate.setTextColor(Color.WHITE);

        // Đặt RadioButton được chọn dựa trên vị trí đã chọn
        holder.radioButtonDate.setChecked(position == selectedPosition);

        // Xử lý sự kiện khi RadioButton được chọn
        holder.radioButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged(); // Cập nhật lại danh sách để chỉ một RadioButton được chọn
            }
        });

        // Xử lý sự kiện khi nhấn vào TextView
        holder.textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged(); // Cập nhật lại danh sách để chỉ một RadioButton được chọn
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate;
        RadioButton radioButtonDate;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            radioButtonDate = itemView.findViewById(R.id.radioButtonDate);
        }
    }
}
