package com.example.notification;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServiceTest extends android.app.Service {
    NotificationCompat.Builder builder;
    private static final String CHANNEL_ID = "id";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.pic)
                .setContentTitle("new msg")
                .setContentText("new msg too")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
