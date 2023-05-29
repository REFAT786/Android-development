package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String NOTIFICATION_CHANNEL_ID = "My channel 1";
    private static final int NOTIFICATION_ID = 100;
    static final int ALARM_REQ_CODE =101;
    private EditText  editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton_id);
        Button stopButton = findViewById(R.id.stopButton_id);
        editText = findViewById(R.id.editText_id);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        (view1, hourOfDay, minute1) -> {

                            editText.setText(hourOfDay + ":" + minute1);
                        }, hour, minute, true);

                timePickerDialog.show();
            }
        });


        startButton.setOnClickListener(v -> {


            @SuppressLint("CutPasteId") String time = ((EditText)(findViewById(R.id.editText_id))).getText().toString();

            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.bus, null);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            assert bitmapDrawable != null;
            Bitmap largeIcon = bitmapDrawable.getBitmap();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Notification notification;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                notification = new Notification.Builder(this)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.drawable.bus)
                        .setContentText("Alarm : "+time)
                        .setSubText("New massage from ...")
                        .setChannelId(NOTIFICATION_CHANNEL_ID)
                        .build();
                notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, "New channel", NotificationManager.IMPORTANCE_HIGH));
            }
            else{
                notification = new Notification.Builder(this)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.drawable.bus)
                        .setContentText("New massage")
                        .setSubText("New massage from ...")
                        .build();
            }
            notificationManager.notify(NOTIFICATION_ID, notification);

             });


    }
}