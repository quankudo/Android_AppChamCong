package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appchamcong.R;

public class HomeDataFragment extends Fragment {
    Button personBtn, groupBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_data, container, false);
        personBtn = v.findViewById(R.id.btnPerson);
        groupBtn = v.findViewById(R.id.btnGroup);
        replaceFragment(new PersonFragment());

        personBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                replaceFragment(new PersonFragment());
                personBtn.setBackgroundTintList(ColorStateList.valueOf(R.color.blue));
                groupBtn.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            }
        });
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                replaceFragment(new GroupFragment());
                groupBtn.setBackgroundTintList(ColorStateList.valueOf(R.color.blue));
                personBtn.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            }
        });
        return v;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}