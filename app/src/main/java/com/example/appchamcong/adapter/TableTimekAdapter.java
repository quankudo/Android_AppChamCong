package com.example.appchamcong.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;

import java.util.ArrayList;

public class TableTimekAdapter extends RecyclerView.Adapter<TableTimekAdapter.ViewHolder> {
    ArrayList<String> list;

    public TableTimekAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TableTimekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_timek_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TableTimekAdapter.ViewHolder holder, int position) {
        holder.stt.setText(position+1 + "");
        holder.date.setText(list.get(position));
        Log.d("item"+position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.tvSTT);
            date = itemView.findViewById(R.id.tvDate);
        }
    }
}
