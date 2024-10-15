package com.example.appchamcong.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.activity.PersonalTimekeepActivity;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person_Timekeeping> list;
    Context context;
    public PersonAdapter(ArrayList<Person_Timekeeping> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.date.setText(list.get(position).getDate());
        final int index = position;
        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PersonalTimekeepActivity.class);
                intent.putExtra("personName", list.get(index).getName());
                intent.putExtra("personDate", list.get(index).getDate());
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, date;
        ConstraintLayout group;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameGr);
            date = itemView.findViewById(R.id.textDateGr);
            group = itemView.findViewById(R.id.group_item_person);
        }
    }
}
