package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.GroupTimeKeeping;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    ArrayList<GroupTimeKeeping> list;

    public GroupAdapter(ArrayList<GroupTimeKeeping> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.count.setText("Nhân viên: "+list.get(position).getCount());
        holder.type.setText(list.get(position).getType());
        holder.date.setText(list.get(position).getDate());
        holder.icon.setImageResource(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, date, count, type;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameGr);
            date = itemView.findViewById(R.id.textDateGr);
            count = itemView.findViewById(R.id.textCountGr);
            type = itemView.findViewById(R.id.textTypeGr);
            icon = itemView.findViewById(R.id.iconType);
        }
    }
}
