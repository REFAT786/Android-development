package com.example.clock.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class MyReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        // can add service
        mp = MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();

    }
}
