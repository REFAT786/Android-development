package com.example.notificationapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent alarmIntent = new Intent(context, AlarmService.class);
        context.startService(alarmIntent);
        Toast.makeText(context, "Time is Up", Toast.LENGTH_SHORT).show();
    }
}
