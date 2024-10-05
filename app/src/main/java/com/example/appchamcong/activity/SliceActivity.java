package com.example.appchamcong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.SlideViewPagerAdapter;

public class SliceActivity extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slice);

        viewPager = findViewById(R.id.viewpager);
        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        if(isOpenAlready()){
            Intent intent = new Intent(SliceActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences("slide", MODE_PRIVATE).edit();
            editor.putBoolean("slide", true);
            editor.commit();
        }
    }

    private boolean isOpenAlready() {
        SharedPreferences sharedPreferences = getSharedPreferences("slide", MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("slide", false);
        return result;
    }
}