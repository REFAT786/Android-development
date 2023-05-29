package com.example.clock.fragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.clock.R;
import com.example.clock.reciver.MyReceiver;

import java.util.Calendar;

public class AlarmFragment extends Fragment {

    private EditText alarmTime;

    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AlarmManager alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        alarmTime = view.findViewById(R.id.alarmTime_id);
        Button saveButton = view.findViewById(R.id.saveButton_id);

        alarmTime.setOnClickListener(view12 -> {

            final Calendar c = Calendar.getInstance();

            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    (view1, hourOfDay, minute1) -> {
                        alarmTime.setText(hourOfDay + ":" + minute1);
                    }, hour, minute, true);

            timePickerDialog.show();

        });

        saveButton.setOnClickListener(view13 -> {

            // user given time
            int time = Integer.parseInt(((EditText) (view13.findViewById(R.id.alarmTime_id))).getText().toString());
            //current time
            long currentTime = System.currentTimeMillis();

            if(time == currentTime){
                Intent intent = new Intent(getActivity(), MyReceiver.class);
                //long trigerTime = currentTime+(time* 1000L);

                @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

                 alarmManager.set(AlarmManager.RTC_WAKEUP,currentTime,pendingIntent);
            }

           // current time + user given time
           // long trigerTime = currentTime+(time* 1000L);

           // Intent intent = new Intent(AlarmFragment.this, MyReceiver.class);
            // PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmFragment.this,ALARM_REQ_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT);

            //alarmManager.set(AlarmManager.RTC_WAKEUP,trigerTime,pendingIntent);
        });
    }
}