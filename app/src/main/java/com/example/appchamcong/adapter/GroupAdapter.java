package com.example.appchamcong.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.activity.HomeDataFragment;
import com.example.appchamcong.activity.LeaderWorkActivity;
import com.example.appchamcong.activity.PersonalTimekeepActivity;
import com.example.appchamcong.domain.GroupTimeKeeping;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    List<Person_Timekeeping> list;
    Context context;
    public GroupAdapter(List<Person_Timekeeping> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getWorkName());
        holder.count.setText("Nhân viên: "+list.get(position).getAccountEmployee());
        holder.type.setText(list.get(position).getHtcc());
        holder.date.setText(list.get(position).getDateSalary());
        if("WIFI".equals(list.get(position).getHtcc())){
            holder.icon.setImageResource(R.drawable.baseline_wifi);
        }
        else{
            holder.icon.setImageResource(R.drawable.baseline_qr_code);
        }
        final int index = position;
        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(index).isChuNhom() && list.get(index).isGroup()){
                    Intent intent = new Intent(context, LeaderWorkActivity.class);
                    intent.putExtra("groupName", list.get(index).getWorkName());
                    intent.putExtra("groupDate", list.get(index).getDateSalary());
                    intent.putExtra("groupId", list.get(index).getId());
                    context.startActivity(intent);
                    if (context instanceof Activity) {
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    }
                }
                else{
                    Intent intent = new Intent(context, PersonalTimekeepActivity.class);
                    intent.putExtra("personName", list.get(index).getWorkName());
                    intent.putExtra("personDate", list.get(index).getDateSalary());
                    intent.putExtra("EmployeeId", list.get(index).getId()); //id nhom
                    context.startActivity(intent);
                    if (context instanceof Activity) {
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, date, count, type;
        ImageView icon;
        ConstraintLayout group;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameGr);
            date = itemView.findViewById(R.id.textDateGr);
            count = itemView.findViewById(R.id.textCountGr);
            type = itemView.findViewById(R.id.textTypeGr);
            icon = itemView.findViewById(R.id.iconType);
            group = itemView.findViewById(R.id.group_item_group);
        }
    }
}
