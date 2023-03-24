package com.example.notificationapp;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int ALARM_REQ_CODE = 300;
    AlarmManager alarmManager;
    private EditText  editText;
    private int hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button startButton = findViewById(R.id.startButton_id);
        //Button stopButton = findViewById(R.id.stopButton_id);
        editText = findViewById(R.id.editText_id);

        final Calendar c = Calendar.getInstance();

        editText.setOnClickListener(v -> {

            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute1) -> {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute1);
                        editText.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()));
                    },hour,minute,true);


            timePickerDialog.show();
        });


        startButton.setOnClickListener(v -> {

            @SuppressLint("CutPasteId") String time = ((EditText)(findViewById(R.id.editText_id))).getText().toString();

            if(editText.getText().toString().isEmpty()){
                Toast.makeText(this, "Please choose a time", Toast.LENGTH_SHORT).show();
                return;
            }
            //notification
            Intent notificationIntent = new Intent(this, NotificationService.class);
            notificationIntent.putExtra(NotificationService.INPUT_TEXT_EXTRA,time);
            startService(notificationIntent);

            long timeInMillis;
            Date date;
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            try{
                date = format.parse(time);
                assert date != null;
                timeInMillis = date.getTime();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            //Alarm
            long triggerTime = System.currentTimeMillis() + timeInMillis ;
            Toast.makeText(this, "time matched", Toast.LENGTH_SHORT).show();
            Intent alarmIntent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, ALARM_REQ_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime,alarmPendingIntent);

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
       }
}