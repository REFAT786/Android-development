package com.example.watchapplication;

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

import java.util.Locale;


public class StopWatchFragment extends Fragment {
    View view;
    Button start,stop,reset;
    TextView textView;

    private long miliseconds = 0;
    private long currentTime, startTime=0;

    private boolean running;

    private boolean wasRunning;

    Handler handler;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stop_watch, container, false);

        start = view.findViewById(R.id.startButton_id);
        stop = view.findViewById(R.id.stopButton_id);
        reset = view.findViewById(R.id.resetButton_id);
        textView = view.findViewById(R.id.textView);

        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);


        sharedPreferences = getActivity().getSharedPreferences("com.example.watchapplication", Context.MODE_PRIVATE);

        miliseconds = sharedPreferences.getLong("miliseconds",0);
        running = sharedPreferences.getBoolean("running",true);
        wasRunning = sharedPreferences.getBoolean("wasRunning",true);


        runTimer();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running){

                    onClickStart(view);
                    start.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);

                }

            }
        });

        stop.setOnClickListener(view -> {

            if(running){
                onClickStop(view);
                start.setVisibility(View.VISIBLE);
                start.setText("Resume");
                stop.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }



        });

        reset.setOnClickListener(view -> {
            onClickReset(view);
            start.setVisibility(View.VISIBLE);
            start.setText("START");
            stop.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // lastState
        // lastCount
        // lastTime

        // if lastState == running


    }

    private void timeCalculate() {
        // currentTime
        // lastTime
        // totalDuration = currentTIme - lastTime
        // lastCount
        // totalMilliseconds = lastCount + totalDuration
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
    }

    @Override
    public void onStart() {
        super.onStart();
        //startTimer()
    }

    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart(View view)
    {
        running = true;
    }

    public void onClickStop(View view)
    {
        running = false;
    }

    public void onClickReset(View view)
    {
        running = false;
        miliseconds = 0;
    }

    private void runTimer()
    {

        handler = new Handler();

        handler.post(new Runnable() {

            @Override
            public void run()
            {

                sharedPreferences = getActivity().getSharedPreferences("com.example.watchapplication", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();


                long milisec = System.currentTimeMillis() - startTime;
                long secs = (miliseconds / 100) % 60;
                long minutes = (miliseconds / (100*60)) % 60;
                long hours = (miliseconds / (100*60*60)) % 60;

                String time = String.format(Locale.getDefault(),
                                "%d:%02d:%02d.%02d", hours,
                                minutes, secs, milisec);

                textView.setText(time);

                editor.putLong("miliseconds",milisec);
                editor.putLong("secs",secs);
                editor.putLong("minutes",minutes);
                editor.putLong("hours",hours);
                editor.commit();

                if (running) {
                    miliseconds = miliseconds+3;
                }
                handler.postDelayed(this, 1);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        // lastState
        // lastCount
        // currentTime
    }
}