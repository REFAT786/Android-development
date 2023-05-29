package com.example.clock.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clock.R;

import java.util.Date;
import java.util.Locale;

public class StopWatchFragment extends Fragment {
    private TextView textView;
    Handler handler;
    private long millisecond;
    private boolean running;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public StopWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_watch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences("com.example.watchapplication", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button startButton = view.findViewById(R.id.startButton_id);
        Button stopButton = view.findViewById(R.id.stopButton_id);
        Button resetButton = view.findViewById(R.id.resetButton_id);

        textView = view.findViewById(R.id.textView);

        millisecond = sharedPreferences.getLong("lastCount", 0);

        if (sharedPreferences.getBoolean("stopwatchStatus", false)) {
            running = true;
            timeCalculate();

            startButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.VISIBLE);
        }else{
            startButton.setVisibility(View.VISIBLE);
            stopButton.setVisibility(View.INVISIBLE);
            resetButton.setVisibility(View.INVISIBLE);
        }

        startButton.setOnClickListener(view13 -> {
            running = true;
            startButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.VISIBLE);

        });
        stopButton.setOnClickListener(view12 -> {
            running = false;
            startButton.setVisibility(View.VISIBLE);
            startButton.setText("RESUME");
            stopButton.setVisibility(View.INVISIBLE);
            resetButton.setVisibility(View.VISIBLE);

        });
        resetButton.setOnClickListener(view1 -> {
            running = false;
            millisecond = 0;

            startButton.setVisibility(View.VISIBLE);
            startButton.setText("START");
            stopButton.setVisibility(View.INVISIBLE);
            resetButton.setVisibility(View.INVISIBLE);

        });

        // lastState
        // lastCount
        // lastTime
        // if lastState == running

    }

    @Override
    public void onStart() {
        super.onStart();
        startTimer();
    }

    @Override
    public void onStop() {
        super.onStop();

        // lastState
        // lastCount
        // currentTime

        editor.putLong("lastCount", millisecond);
        editor.putBoolean("stopwatchStatus", running);
        Date date = new Date();
        editor.putLong("lastTime", date.getTime());
        editor.commit();

    }

    private void timeCalculate() {

        // currentTime
        // lastTime
        // totalDuration = currentTIme - lastTime
        // lastCount
        // totalMilliseconds = lastCount + totalDuration

        long oldTime = sharedPreferences.getLong("lastTime", 0);

        Date date = new Date();
        long currentTime = date.getTime();
        long totalDuration = currentTime - oldTime;
        millisecond+=totalDuration;

    }

    private void startTimer() {

        // Handler period 1
        // Runnable
        // ms10
        // s
        // m
        // h
        // String hh:mm:ss:ms
        // if running millisecond++;

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                long millisec = millisecond % 100;
                long sec = (millisecond / 60) % 60;
                long minute = (millisecond / 3600) /60;
                long hour = ((millisecond / 3600) / 60) % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d.%02d", hour, minute, sec, millisec );
                textView.setText(time);

                if(running){
                    millisecond++;
                }

                handler.postDelayed(this,1);

            }
        });

    }
}