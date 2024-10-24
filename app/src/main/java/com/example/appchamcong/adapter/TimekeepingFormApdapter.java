package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.TimekeepingForm;

import java.util.List;


public class TimekeepingFormApdapter extends RecyclerView.Adapter<TimekeepingFormApdapter.TimekeepingFormViewHolder> {
    private List<TimekeepingForm> list;
    private int selectedPosition = 0;
    private Context context;

    public TimekeepingFormApdapter(List<TimekeepingForm> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TimekeepingFormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timekeeping_form, parent, false);
        return new TimekeepingFormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingFormViewHolder holder, int position) {
        TimekeepingForm tf = list.get(position);
        holder.txtName.setText(tf.getName());
        holder.txtDescribe.setText(tf.getDescribe());
        holder.radio.setChecked(position == selectedPosition);



        holder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        // Xử lý sự kiện khi nhấn vào TextView
        holder.lnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        //An thanh bar cua item cuoi
        if (position == list.size() - 1) {
            holder.bar.setVisibility(View.GONE);
        } else {
            holder.bar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class  TimekeepingFormViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescribe;
        RadioButton radio;
        LinearLayout lnl;
        View bar;

        public TimekeepingFormViewHolder(@NonNull View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.bar);
            lnl = itemView.findViewById(R.id.layoutTpkF);
            txtName = itemView.findViewById(R.id.txtNameTimepeekingForm);
            txtDescribe = itemView.findViewById(R.id.txtDescribe);
            radio = itemView.findViewById(R.id.radioTimekeepingForm);

        }


    }
}
