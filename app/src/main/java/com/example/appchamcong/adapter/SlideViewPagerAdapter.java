package com.example.appchamcong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appchamcong.activity.MainActivity;
import com.example.appchamcong.R;
import com.example.appchamcong.activity.SliceActivity;

public class SlideViewPagerAdapter extends PagerAdapter {
    Context ctx;


    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView logo = view.findViewById(R.id.imageView);
        ImageView ind1 = view.findViewById(R.id.dot1);
        ImageView ind2 = view.findViewById(R.id.dot2);
        ImageView ind3 = view.findViewById(R.id.dot3);
        ImageView ind4 = view.findViewById(R.id.dot4);

        TextView title = view.findViewById(R.id.title);
        TextView desc = view.findViewById(R.id.description);
        Button btn = view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SliceActivity.viewPager.setCurrentItem(position+1);
            }
        });

        switch (position)
        {
            case 0:
                logo.setImageResource(R.drawable.slide1);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);

                title.setText("Chấm công - Thông minh");
                desc.setText("Chỉ cần một cú chạm là bạn có thể hoàn thành việc chấm công");
                btn.setText("Tiếp tục");
                break;

            case 1:
                logo.setImageResource(R.drawable.slide2);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.unselected);

                title.setText("Quản lý - Ứng lương");
                desc.setText("Giúp bạn theo dõi được việc ứng lương \n" +
                        "và trả lương cho từng nhân viên");
                btn.setText("Tiếp tục");
                break;
            case 2:
                logo.setImageResource(R.drawable.slide3);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);
                ind4.setImageResource(R.drawable.unselected);

                title.setText("Báo cáo - Thống kê");
                desc.setText("Giúp bạn quản lý và theo dõi chi tiết công việc của từng nhân viên");
                btn.setText("Tiếp tục");
                break;
            case 3:
                logo.setImageResource(R.drawable.slide4);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                ind4.setImageResource(R.drawable.selected);

                title.setText("Thông báo - Nhắc nhở");
                desc.setText("Thông báo nhắc nhở bạn chấm công mỗi ngày thuận tiện và chính xác");
                btn.setText("Bắt đầu");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ctx, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(intent);
                    }
                });
                break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
