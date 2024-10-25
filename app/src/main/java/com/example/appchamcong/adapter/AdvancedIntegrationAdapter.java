package com.example.appchamcong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.AdvancedIntegration;
import com.example.appchamcong.domain.TimekeepingForm;

import java.util.List;

public class AdvancedIntegrationAdapter extends RecyclerView.Adapter<AdvancedIntegrationAdapter.AdvancedIntegrationViewHolder> {
    private List<AdvancedIntegration>list;
    private int selectedPosition = 0;
    private Context context;

    public AdvancedIntegrationAdapter(List<AdvancedIntegration> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdvancedIntegrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advanced_integration, parent, false);
        return new AdvancedIntegrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvancedIntegrationViewHolder holder, int position) {
        AdvancedIntegration advanced = list.get(position);
        holder.tvName.setText(advanced.getName());
        holder.tvDescribe.setText(advanced.getDescribe());
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

    public class AdvancedIntegrationViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvDescribe;
        RadioButton radio;
        LinearLayout lnl;
        View bar;
        public AdvancedIntegrationViewHolder(@NonNull View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.barAdvancedIntegration);
            lnl = itemView.findViewById(R.id.lnlAdvancedIntegration);
            tvName = itemView.findViewById(R.id.tvNameAdvancedIntegration);
            tvDescribe = itemView.findViewById(R.id.tvDescribeAdvancedIntegration);
            radio = itemView.findViewById(R.id.radioAdvancedIntegration);



        }
    }
}
