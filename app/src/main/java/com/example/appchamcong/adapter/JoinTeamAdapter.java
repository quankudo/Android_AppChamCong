package com.example.appchamcong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.ViewModel.WorkViewModel;
import com.example.appchamcong.domain.JoinTeam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JoinTeamAdapter extends RecyclerView.Adapter<JoinTeamAdapter.Viewholder> {
    @NonNull
    List<JoinTeam> list;
    WorkViewModel workViewModel;

    public JoinTeamAdapter(@NonNull List<JoinTeam> list, WorkViewModel workViewModel) {
        this.list = list;
        this.workViewModel = workViewModel;
    }

    public JoinTeamAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_join_team,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinTeamAdapter.Viewholder holder, int position) {
        holder.tvName.setText(list.get(position).getTen());
        holder.tvNumber.setText(list.get(position).getSdt());

        final int index = position;
        holder.btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workViewModel.AgreeJoinWork(list.get(index).getIdCongViec(), list.get(index).getIdNguoiDung(), Double.parseDouble(holder.etTienCong.getText().toString()));
            }
        });

        holder.btnTuChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workViewModel.RefuseJoinWork(list.get(index).getIdCongViec(), list.get(index).getIdNguoiDung());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvName, tvNumber;
        EditText etTienCong;
        Button btnDongY, btnTuChoi;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvNumber = itemView.findViewById(R.id.number);
            etTienCong = itemView.findViewById(R.id.positions);
            btnDongY = itemView.findViewById(R.id.btnDongY);
            btnTuChoi = itemView.findViewById(R.id.btnTuChoi);
        }
    }
}
