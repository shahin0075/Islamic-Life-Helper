package com.itamadersomaj.islamiclifehelper.PreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;


public class PrayerSharedPreference {
    SharedPreferences.Editor myEdit;
    SharedPreferences sharedPreferences;

    public PrayerSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PrayerSharedPref", 0);
        this.sharedPreferences = sharedPreferences;
        this.myEdit = sharedPreferences.edit();
    }

    public void setIsFirstTimeRunPref(boolean z) {
        this.myEdit.putBoolean("IsFirstTimeRunPref", z);
        this.myEdit.commit();
    }

    public boolean getIsFirstTimeRunPref() {
        return this.sharedPreferences.getBoolean("IsFirstTimeRunPref", true);
    }

    public void setIsPrayerLoadData(boolean z) {
        this.myEdit.putBoolean("IsDataLoad", z);
        this.myEdit.commit();
    }

    public boolean isPrayerLoadData() {
        return this.sharedPreferences.getBoolean("IsDataLoad", false);
    }

    public void setLatitude(String str) {
        this.myEdit.putString("Latitude", str);
        this.myEdit.commit();
    }

    public String getLatitude() {
        return this.sharedPreferences.getString("Latitude", "0.0");
    }

    public void setLongitude(String str) {
        this.myEdit.putString("Longitude", str);
        this.myEdit.commit();
    }

    public String getLongitude() {
        return this.sharedPreferences.getString("Longitude", "0.0");
    }

    public void setPrayerTimeFormatPref(int i) {
        this.myEdit.putInt("PrayerTimeFormatPref", i);
        this.myEdit.commit();
    }

    public int getPrayerTimePref() {
        return this.sharedPreferences.getInt("PrayerTimeFormatPref", 1);
    }

    public void setCalMethodPref(int i) {
        this.myEdit.putInt("CalMethod", i);
        this.myEdit.commit();
    }

    public int getCalMethodPref() {
        return this.sharedPreferences.getInt("CalMethod", 4);
    }

    public void setAsarMethodPref(int i) {
        this.myEdit.putInt("AsarMethod", i);
        this.myEdit.commit();
    }

    public int getAsarMethodPref() {
        return this.sharedPreferences.getInt("AsarMethod", 0);
    }

    public void setLocation(String str) {
        this.myEdit.putString("LocationData", str);
        this.myEdit.commit();
    }

    public String getLocation() {
        return this.sharedPreferences.getString("LocationData", "");
    }

    public void setDhikrId(int i) {
        this.myEdit.putInt("DhikrId", i);
        this.myEdit.commit();
    }

    public int getDhikrId() {
        return this.sharedPreferences.getInt("DhikrId", 1);
    }

    public void setFajrRing(boolean z) {
        this.myEdit.putBoolean("FajrRing", z);
        this.myEdit.commit();
    }

    public boolean getFajrRing() {
        return this.sharedPreferences.getBoolean("FajrRing", true);
    }

    public void setSunriseRing(boolean z) {
        this.myEdit.putBoolean("SunriseRing", z);
        this.myEdit.commit();
    }

    public boolean getSunriseRing() {
        return this.sharedPreferences.getBoolean("SunriseRing", true);
    }

    public void setDururRing(boolean z) {
        this.myEdit.putBoolean("DururRing", z);
        this.myEdit.commit();
    }

    public boolean getDururRing() {
        return this.sharedPreferences.getBoolean("DururRing", true);
    }

    public void setAsrRing(boolean z) {
        this.myEdit.putBoolean("AsrRing", z);
        this.myEdit.commit();
    }

    public boolean getAsrRing() {
        return this.sharedPreferences.getBoolean("AsrRing", true);
    }

    public void setMaghribRing(boolean z) {
        this.myEdit.putBoolean("MaghribRing", z);
        this.myEdit.commit();
    }

    public boolean getMaghribRing() {
        return this.sharedPreferences.getBoolean("MaghribRing", true);
    }

    public void setIshaRing(boolean z) {
        this.myEdit.putBoolean("IshaRing", z);
        this.myEdit.commit();
    }

    public boolean getIshaRing() {
        return this.sharedPreferences.getBoolean("IshaRing", true);
    }
}
