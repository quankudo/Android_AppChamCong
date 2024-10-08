package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.JoinTeam;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JoinTeamAdapter extends RecyclerView.Adapter<JoinTeamAdapter.Viewholder> {
    @NonNull
    ArrayList<JoinTeam> list;

    public JoinTeamAdapter(@NonNull ArrayList<JoinTeam> list) {
        this.list = list;
    }

    public JoinTeamAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_join_team,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinTeamAdapter.Viewholder holder, int position) {
            holder.tvName.setText(list.get(position).getValueName());
            holder.tvNumber.setText(list.get(position).getValueNumber());
            holder.tvPosition.setText(list.get(position).getValuePosition());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvName, tvNumber, tvPosition;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvNumber = itemView.findViewById(R.id.number);
            tvPosition = itemView.findViewById(R.id.positions);
        }
    }
}
