package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.HourlyTimekeepingStepsUsingWifiAdapter;

public class NewJobCreationProcess extends AppCompatActivity {
    private ViewPager2 viewPager;
    private HourlyTimekeepingStepsUsingWifiAdapter adapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_creation_process);

        // Thiết lập ViewPager2 và Adapter
        viewPager = findViewById(R.id.viewpager);
        adapter = new HourlyTimekeepingStepsUsingWifiAdapter(this);
        viewPager.setAdapter(adapter);

        // Tìm các nút Next và Previous trong layout
        TextView nextButton = findViewById(R.id.tvNext);
        TextView previousButton = findViewById(R.id.tvBack);
        progressBar = findViewById(R.id.progress);
        int totalsSteps = adapter.getItemCount();


        progressBar.setProgress(100/totalsSteps);
        // Thiết lập sự kiện OnClick cho nút Next
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int progressBarCurrent = progressBar.getProgress();

                progressBar.setProgress(progressBarCurrent + 100/totalsSteps);
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < adapter.getItemCount() - 1) {
                    viewPager.setCurrentItem(currentItem + 1);
                }
            }
        });


        // Thiết lập sự kiện OnClick cho nút Previous
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progressBarCurrent = progressBar.getProgress();

                progressBar.setProgress( progressBarCurrent  - 100/totalsSteps);

                int currentItem = viewPager.getCurrentItem();
                if (currentItem > 0) {
                    viewPager.setCurrentItem(currentItem - 1);
                }
            }
        });
    }
}