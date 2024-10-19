package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;

import java.util.ArrayList;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {
    ArrayList<String> list;
    private int selectedPosition = -1;

    public ChooseAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAdapter.ViewHolder holder, int position) {
        holder.radio.setText(list.get(position));
        holder.radio.setChecked(position == selectedPosition);
        final int index = position;
        holder.radio.setOnClickListener(v -> {
            selectedPosition = index;
            notifyDataSetChanged(); // Cập nhật lại giao diện
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radio = itemView.findViewById(R.id.radio_btn);
        }
    }
}
