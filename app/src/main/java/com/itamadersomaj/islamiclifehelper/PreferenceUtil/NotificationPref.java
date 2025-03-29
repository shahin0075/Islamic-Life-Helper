package com.itamadersomaj.islamiclifehelper.PreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NotificationPref {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public NotificationPref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Notification", 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public long getTime() {
        Date date;
        try {
            date = new SimpleDateFormat("H:mm").parse("8:30");
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return this.sharedPreferences.getLong("time", date.getTime());
    }

    public void setTime(long j) {
        this.editor.putLong("time", j).apply();
    }
}
