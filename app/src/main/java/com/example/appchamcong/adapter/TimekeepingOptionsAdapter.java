package com.example.appchamcong.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.TimekeepingForm;
import com.example.appchamcong.domain.TimekeepingOptions;

import java.util.List;

public class TimekeepingOptionsAdapter extends RecyclerView.Adapter<TimekeepingOptionsAdapter.TimekeepingOptionsViewHolder> {
    private List<TimekeepingOptions> list;
    private int selectedPosition = 0;
    private Context context;

    public TimekeepingOptionsAdapter(List<TimekeepingOptions> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TimekeepingOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timekeeping_options, parent, false);
        return new TimekeepingOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingOptionsViewHolder holder, int position) {
        TimekeepingOptions tf = list.get(position);
        holder.tvName.setText(tf.getName());
        holder.tvDescribe.setText(tf.getDescribe());


        holder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                holder.btn.setBackgroundResource(R.drawable.custom_btn_blue);
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



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TimekeepingOptionsViewHolder extends RecyclerView.ViewHolder{
        Button btn;
        ImageView img;
        TextView tvName, tvDescribe;
        RadioButton radio;
        LinearLayout lnl;
        public TimekeepingOptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            lnl = itemView.findViewById(R.id.lnlTimekeepingOptions);
            img = itemView.findViewById(com.example.appchamcong.R.id.imgViewTimekeepingOptions);
            tvName = itemView.findViewById(R.id.nameTimekeepingOptions);
            tvDescribe = itemView.findViewById(R.id.describeTimekeepingOptions);
            radio = itemView.findViewById(R.id.radioBtnTimekeepingOptions);
        }
    }

}
