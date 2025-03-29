package com.itamadersomaj.islamiclifehelper.Receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.itamadersomaj.islamiclifehelper.Service.PrayerTimeService;


public class BootReceiver extends BroadcastReceiver {
    @Override 
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || isMyServiceRunning(PrayerTimeService.class, context)) {
            return;
        }
        Intent intent2 = new Intent(context, PrayerTimeService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent2);
        } else {
            context.startService(intent2);
        }
    }

    public boolean isMyServiceRunning(Class<?> cls, Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
