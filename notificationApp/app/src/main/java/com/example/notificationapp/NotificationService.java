package com.example.notificationapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

public class NotificationService extends Service {

    public static final String INPUT_TEXT_EXTRA = "input_text";
    public static final int NOTIFICATION_ID = 100;
    private static final String NOTIFICATION_CHANNEL_ID = "My channel 1";
    private static final int N_REQ_CODE = 200;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String time = intent.getStringExtra(INPUT_TEXT_EXTRA);

        //createNotificationChaneel();

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.bus, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        assert bitmapDrawable != null;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;

        Intent nIntent = new Intent(getApplicationContext(), NotificationResultActivity.class);
        nIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent nPendingIntent = PendingIntent.getActivity(this,N_REQ_CODE,nIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.bus)
                    .setContentText("Alarm : " + time)
                    .setSubText("New massage from ...")
                    .setChannelId(NOTIFICATION_CHANNEL_ID)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setContentIntent(nPendingIntent)
                    .build();

            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, "New channel", NotificationManager.IMPORTANCE_HIGH));

        }
        notificationManager.notify(NOTIFICATION_ID, notification);

        return START_STICKY;
    }

    /*
    private void createNotificationChaneel() {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
           CharSequence name = "My Chaneel Name";
           String description = "My Chaneel Description";
           int importance = NotificationManager.IMPORTANCE_HIGH;
           NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,name,importance);
           channel.setDescription(description);
           NotificationManager notificationManager = getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(channel);

        }
    }
    
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
       // stopForeground(true);
       // stopSelf();
    }
}
