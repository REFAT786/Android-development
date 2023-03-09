package com.example.watchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView clock,stopWatch;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clockText_id);
        stopWatch = findViewById(R.id.stopWatchText_id);

        loadFrag(new ClockFragment(),0);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new ClockFragment(),1);
            }
        });

        stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new StopWatchFragment(),1);
            }
        });

    }
    public void loadFrag(Fragment fragment, int flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("reverse");

        if(flag == 0){
            ft.add(R.id.bodyFrameLayout_id, fragment);
        }
        else {
            ft.replace(R.id.bodyFrameLayout_id, fragment);
        }

        ft.commit();
    }
}