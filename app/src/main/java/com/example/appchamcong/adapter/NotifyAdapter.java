package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.Notify;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {
    ArrayList <Notify> list;

    public NotifyAdapter(ArrayList<Notify> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotifyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.ViewHolder holder, int position) {
        holder.finger.setImageResource(list.get(position).getIcon());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton finger;
        TextView title, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            finger = itemView.findViewById(R.id.finger);
            title = itemView.findViewById(R.id.notify);
            date = itemView.findViewById(R.id.date);
        }
    }
}
