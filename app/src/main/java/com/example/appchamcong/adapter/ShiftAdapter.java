package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Shift;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ViewHolder> {
    ArrayList<Shift> list;

    public ShiftAdapter(ArrayList<Shift> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ShiftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shift_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShiftAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.textCount.setText(list.get(position).getCount() + "");
        holder.color.setBackgroundColor(list.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, textCount;
        ImageView color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCount = itemView.findViewById(R.id.textCount);
            txtName = itemView.findViewById(R.id.txtShiftName);
            color = itemView.findViewById(R.id.image_color);
        }
    }
}
