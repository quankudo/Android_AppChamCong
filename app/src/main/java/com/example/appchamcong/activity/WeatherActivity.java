package com.example.appchamcong.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.DayWeatherAdapter;
import com.example.appchamcong.adapter.HourWeatherAdapter;
import com.example.appchamcong.adapter.NotifyAdapter;
import com.example.appchamcong.domain.Day_Weather;
import com.example.appchamcong.domain.Hour_Weather;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    ArrayList<Hour_Weather> hourWeathers;
    ArrayList<Day_Weather> dayWeathers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        init();
        RecyclerView r1 = findViewById(R.id.hourWeathers);
        RecyclerView r2 = findViewById(R.id.dayWeathers);
        HourWeatherAdapter hourWeatherAdapter = new HourWeatherAdapter(hourWeathers);
        DayWeatherAdapter dayWeatherAdapter = new DayWeatherAdapter(dayWeathers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        r1.setLayoutManager(linearLayoutManager);
        r1.setAdapter(hourWeatherAdapter);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        r2.setLayoutManager(linearLayoutManager2);
        r2.setAdapter(dayWeatherAdapter);
    }

    public void init(){
        hourWeathers = new ArrayList<>();
        Hour_Weather hourWeather1 = new Hour_Weather(16, R.drawable.crescent_moon, 31);
        Hour_Weather hourWeather2 = new Hour_Weather(17, R.drawable.black_cloud, 30);
        Hour_Weather hourWeather3 = new Hour_Weather(18, R.drawable.crescent_moon, 28);
        Hour_Weather hourWeather4 = new Hour_Weather(19, R.drawable.white_cloud_rain, 27);
        Hour_Weather hourWeather5 = new Hour_Weather(20, R.drawable.crescent_moon, 25);
        hourWeathers.add(hourWeather1);
        hourWeathers.add(hourWeather2);
        hourWeathers.add(hourWeather3);
        hourWeathers.add(hourWeather4);
        hourWeathers.add(hourWeather5);

        dayWeathers = new ArrayList<>();
        Day_Weather dayWeather1 = new Day_Weather("Thứ 2", R.drawable.crescent_moon, 20, 27);
        Day_Weather dayWeather2 = new Day_Weather("Thứ 3", R.drawable.black_cloud, 22, 29);
        Day_Weather dayWeather3 = new Day_Weather("Thứ 4", R.drawable.black_cloud, 20, 23);
        Day_Weather dayWeather4 = new Day_Weather("Thứ 5", R.drawable.crescent_moon, 30, 27);
        Day_Weather dayWeather5 = new Day_Weather("Thứ 6", R.drawable.white_cloud_rain, 24, 28);
        Day_Weather dayWeather6 = new Day_Weather("Thứ 7", R.drawable.crescent_moon, 25, 31);
        Day_Weather dayWeather7 = new Day_Weather("Chủ nhật", R.drawable.white_cloud_rain, 28, 32);
        dayWeathers.add(dayWeather1);
        dayWeathers.add(dayWeather2);
        dayWeathers.add(dayWeather3);
        dayWeathers.add(dayWeather4);
        dayWeathers.add(dayWeather5);
        dayWeathers.add(dayWeather6);
        dayWeathers.add(dayWeather7);
    }
}