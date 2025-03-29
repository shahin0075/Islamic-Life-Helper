package com.itamadersomaj.islamiclifehelper.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import com.itamadersomaj.islamiclifehelper.Appcompany.App;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.MainActivity;
import com.itamadersomaj.islamiclifehelper.Model.DuaModel;
import com.itamadersomaj.islamiclifehelper.PrayerAlarmActivity;
import com.itamadersomaj.islamiclifehelper.PrayerTimeCal.PrayerTime;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.NotificationPref;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import com.itamadersomaj.islamiclifehelper.R;
import com.itamadersomaj.islamiclifehelper.TodayDuaActivity;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class PrayerTimeService extends Service {
    public static int duaId = 1;
    NotificationPref notificationPref;
    PrayerSharedPreference prayerSharedPreference;
    PrayerTimeReceiver receiver;
    public final IBinder iBinder = new LocalBinder();
    String prayerTime = "";
    String prayerName = "";

    @Override 
    public int onStartCommand(Intent intent, int i, int i2) {
        return Service.START_STICKY;
    }

    
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public PrayerTimeService getService() {
            return PrayerTimeService.this;
        }
    }

    @Override 
    public IBinder onBind(Intent intent) {
        return this.iBinder;
    }

    @Override 
    public void onCreate() {
        super.onCreate();
        try {
            this.prayerSharedPreference = new PrayerSharedPreference(this);
            this.notificationPref = new NotificationPref(this);
            showNotification(getApplicationContext());
            this.receiver = new PrayerTimeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            registerReceiver(this.receiver, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override 
    public void onDestroy() {
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception unused) {
        }
        super.onDestroy();
    }

    public void showNotification(Context context) {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), Build.VERSION.SDK_INT >= 31 ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(2, new NotificationCompat.Builder(this, App.CHANNEL_ID).setContentTitle("Islamic Dua Prayer Time Running").setPriority(1).setContentText("Service Started").setSmallIcon(R.drawable.noti_icon).setContentIntent(activity).setCategory(Context.ALARM_SERVICE).build());
        } else {
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(2, new NotificationCompat.Builder(this).setContentTitle("Islamic Dua Prayer Time Running").setPriority(1).setContentText("Service Started").setSmallIcon(R.drawable.noti_icon).setContentIntent(activity).setCategory(Context.ALARM_SERVICE).build());
        }
    }

    
    public class PrayerTimeReceiver extends BroadcastReceiver {
        static final String TAG = "TickReceiver";

        public PrayerTimeReceiver() {
        }

        @Override 
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "tick...");
            if (PrayerTimeService.this.checkPrayerTime()) {
                Intent intent2 = new Intent(context, PrayerAlarmActivity.class);
                intent2.putExtra("prayerTime", PrayerTimeService.this.prayerTime);
                intent2.putExtra("prayerName", PrayerTimeService.this.prayerName);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
            if (checkNotification()) {
                duaNotification(context);
            }
        }

        private void duaNotification(Context context) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            DuaModel randomDua = databaseAccess.getRandomDua();
            PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent(context, TodayDuaActivity.class), PendingIntent.FLAG_MUTABLE);
            PrayerTimeService.duaId = randomDua.getId();
            createNotificationChannel();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.notification_dua);
            String dua = randomDua.getDua();
            String enMeaning = randomDua.getEnMeaning();
            if (dua.length() > 34) {
                dua = dua.substring(0, 30) + "....";
            }
            if (enMeaning.length() > 34) {
                enMeaning = enMeaning.substring(0, 30) + "....";
            }
            remoteViews.setTextViewText(R.id.tvDua, dua);
            remoteViews.setTextViewText(R.id.tvDuaTranslation, enMeaning);
            remoteViews.setOnClickPendingIntent(R.id.btnView, activity);
            ((NotificationManager) PrayerTimeService.this.getSystemService(Context.NOTIFICATION_SERVICE)).notify(112, new NotificationCompat.Builder(context, App.CHANNEL_ID).setSmallIcon(R.drawable.noti_icon).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setContentIntent(activity).setCustomContentView(remoteViews).build());
        }

        private boolean checkNotification() {
            Date date = new Date();
            Date date2 = new Date(PrayerTimeService.this.notificationPref.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            simpleDateFormat.format(date);
            System.out.println(simpleDateFormat.format(date));
            try {
                if (simpleDateFormat.parse(simpleDateFormat.format(date)).equals(simpleDateFormat.parse(simpleDateFormat.format(date2)))) {
                    Log.e("Time", "equal");
                    return true;
                }
                Log.e("Time", "Not equal");
                return false;
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

        private void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= 26) {
                ((NotificationManager) PrayerTimeService.this.getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(App.CHANNEL_ID, "Prayer Service Channel", NotificationManager.IMPORTANCE_DEFAULT));
            }
        }
    }

    public boolean checkPrayerTime() {
        double parseDouble = Double.parseDouble(this.prayerSharedPreference.getLatitude());
        double parseDouble2 = Double.parseDouble(this.prayerSharedPreference.getLongitude());
        double timeZone = getTimeZone();
        PrayerTime prayerTime = new PrayerTime();
        prayerTime.setTimeFormat(this.prayerSharedPreference.getPrayerTimePref());
        prayerTime.setCalcMethod(this.prayerSharedPreference.getCalMethodPref());
        prayerTime.setAsrJuristic(this.prayerSharedPreference.getAsarMethodPref());
        prayerTime.setAdjustHighLats(0);
        prayerTime.setOffsets(new int[]{0, 0, 0, 0, 0, 0, 0});
        ArrayList<String> prayerTimes = prayerTime.getPrayerTimes(Calendar.getInstance(), parseDouble, parseDouble2, timeZone);
        if (getUpcomingTime(prayerTimes.get(0))) {
            if (this.prayerSharedPreference.getFajrRing()) {
                this.prayerTime = prayerTimes.get(0);
                this.prayerName = "Fajr";
                return true;
            }
            return false;
        } else if (getUpcomingTime(prayerTimes.get(1))) {
            if (this.prayerSharedPreference.getSunriseRing()) {
                this.prayerTime = prayerTimes.get(1);
                this.prayerName = "Sunrise";
                return true;
            }
            return false;
        } else if (getUpcomingTime(prayerTimes.get(2))) {
            if (this.prayerSharedPreference.getDururRing()) {
                this.prayerTime = prayerTimes.get(2);
                this.prayerName = "Dhuhr";
                return true;
            }
            return false;
        } else if (getUpcomingTime(prayerTimes.get(3))) {
            if (this.prayerSharedPreference.getAsrRing()) {
                this.prayerTime = prayerTimes.get(3);
                this.prayerName = "Asr";
                return true;
            }
            return false;
        } else if (getUpcomingTime(prayerTimes.get(5))) {
            if (this.prayerSharedPreference.getMaghribRing()) {
                this.prayerTime = prayerTimes.get(5);
                this.prayerName = "Maghrib";
                return true;
            }
            return false;
        } else if (getUpcomingTime(prayerTimes.get(6)) && this.prayerSharedPreference.getIshaRing()) {
            this.prayerTime = prayerTimes.get(6);
            this.prayerName = "Isha";
            return true;
        } else {
            return false;
        }
    }

    public double getTimeZone() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 3600000.0d;
    }

    public boolean getUpcomingTime(String str) {
        if (this.prayerSharedPreference.getPrayerTimePref() == 1) {
            try {
                str = new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("hh:mm a").parse(str));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            simpleDateFormat.format(date);
            System.out.println(simpleDateFormat.format(date));
            if (simpleDateFormat.parse(simpleDateFormat.format(date)).equals(simpleDateFormat.parse(str))) {
                PrintStream printStream = System.out;
                printStream.println("Current time is equal " + str);
                return true;
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Current time is not  equal " + str);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
