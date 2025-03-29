package com.itamadersomaj.islamiclifehelper.Appcompany;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Handler;

public class App extends Application {
    public static final String CHANNEL_ID = "PRAYER_TIME_SERVICE_CHANNEL";
    @Override 
    public void onCreate() {
        super.onCreate();

        new Handler().postDelayed(new Runnable() { 
            @Override 
            public void run() {

            }
        }, 2500L);
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Islamic Dua Running", NotificationManager.IMPORTANCE_HIGH));
        }
    }
}
