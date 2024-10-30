package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.IndividualCheckIn;
import com.example.appchamcong.adapter.SelfCheckIniAdapter;

public class NewJobCreationProcess extends AppCompatActivity {
    private ViewPager2 viewPager;
    FragmentStateAdapter adapter;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_creation_process);
        viewPager = findViewById(R.id.viewpager);

        Intent intent = getIntent();
        String nameSelected = intent.getStringExtra("Name");
        if(nameSelected.equals("Chấm cho cá nhân"))
        {
            adapter = new IndividualCheckIn(this);
        }else{
            adapter = new SelfCheckIniAdapter(this);

        }

        viewPager.setAdapter(adapter);

        TextView nextButton = findViewById(R.id.tvNext);
        TextView previousButton = findViewById(R.id.tvBack);
        progressBar = findViewById(R.id.progress);
        int totalsSteps = adapter.getItemCount();


        progressBar.setProgress(100/totalsSteps);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int progressBarCurrent = progressBar.getProgress();

                progressBar.setProgress(progressBarCurrent + 100/totalsSteps);
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < adapter.getItemCount() - 1) {
                    viewPager.setCurrentItem(currentItem + 1);
                }
                if(progressBarCurrent == 100){
                    Intent intent = new Intent(NewJobCreationProcess.this, QRCodeActivity.class);
                    if(nameSelected.equals("Chấm cho cá nhân"))
                    {
                        intent = new Intent(NewJobCreationProcess.this, HomeDataFragment.class);
                    }
                        startActivity(intent);
                }
            }
        });


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progressBarCurrent = progressBar.getProgress();

                progressBar.setProgress( progressBarCurrent  - 100/totalsSteps);

                int currentItem = viewPager.getCurrentItem();
                if (currentItem > 0) {
                    viewPager.setCurrentItem(currentItem - 1);
                }
                if(progressBarCurrent == 100/totalsSteps){
                    Intent intent = new Intent(NewJobCreationProcess.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
}