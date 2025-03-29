package com.itamadersomaj.islamiclifehelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.PrayerTimeCal.PrayerTime;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class AllPrayerActivity extends AppCompatActivity {
    LinearLayout btnPrayerSetting;
    Integer[] dataTime = {0, 1, 2, 3, 5, 6};
    ImageView ivAsr;
    ImageView ivDurur;
    ImageView ivFajr;
    ImageView ivIsha;
    ImageView ivMaghrib;
    ImageView ivSunrise;
    RelativeLayout layout;
    LinearLayout llAsr;
    LinearLayout llBg;
    LinearLayout llDuhur;
    LinearLayout llFajr;
    LinearLayout llIsha;
    LinearLayout llMaghrib;
    PrayerSharedPreference prayerSharedPreference;
    TextView tvAsr;
    TextView tvCurrentLocation;
    TextView tvDurur;
    TextView tvENDate;
    TextView tvFajr;
    TextView tvISARDate;
    TextView tvISEnDate;
    TextView tvIsha;
    TextView tvMaghrib;
    TextView tvSunrise;
    TextView tvTimeLeftAsr;
    TextView tvTimeLeftDurur;
    TextView tvTimeLeftFajr;
    TextView tvTimeLeftIsha;
    TextView tvTimeLeftMaghrib;

    
    public String getPrayerName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 5 ? i != 6 ? "Unknown" : "Isha" : "Maghrib" : "Asr" : "Duhur" : "Sunrise" : "Fajr";
    }

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_all_prayer);

       // AdAdmob adAdmob = new AdAdmob( this);
        //adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        //adAdmob.FullscreenAd_Counter(this);



        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.tvCurrentLocation = (TextView) findViewById(R.id.tvCurrentLocation);
        this.tvENDate = (TextView) findViewById(R.id.tvENDate);
        this.tvISEnDate = (TextView) findViewById(R.id.tvISEnDate);
        this.tvISARDate = (TextView) findViewById(R.id.tvISARDate);
        this.tvFajr = (TextView) findViewById(R.id.tvFajr);
        this.tvSunrise = (TextView) findViewById(R.id.tvSunrise);
        this.tvDurur = (TextView) findViewById(R.id.tvDurur);
        this.tvAsr = (TextView) findViewById(R.id.tvAsr);
        this.tvMaghrib = (TextView) findViewById(R.id.tvMaghrib);
        this.tvIsha = (TextView) findViewById(R.id.tvIsha);
        this.tvTimeLeftFajr = (TextView) findViewById(R.id.tvTimeLeftFajr);
        this.tvTimeLeftDurur = (TextView) findViewById(R.id.tvTimeLeftDurur);
        this.tvTimeLeftAsr = (TextView) findViewById(R.id.tvTimeLeftAsr);
        this.tvTimeLeftMaghrib = (TextView) findViewById(R.id.tvTimeLeftMaghrib);
        this.tvTimeLeftIsha = (TextView) findViewById(R.id.tvTimeLeftIsha);
        this.ivFajr = (ImageView) findViewById(R.id.ivFajr);
        this.ivSunrise = (ImageView) findViewById(R.id.ivSunrise);
        this.ivDurur = (ImageView) findViewById(R.id.ivDurur);
        this.ivAsr = (ImageView) findViewById(R.id.ivAsr);
        this.ivMaghrib = (ImageView) findViewById(R.id.ivMaghrib);
        this.ivIsha = (ImageView) findViewById(R.id.ivIsha);
        this.llFajr = (LinearLayout) findViewById(R.id.llFajr);
        this.llDuhur = (LinearLayout) findViewById(R.id.llDuhur);
        this.llAsr = (LinearLayout) findViewById(R.id.llAsr);
        this.llMaghrib = (LinearLayout) findViewById(R.id.llMaghrib);
        this.llIsha = (LinearLayout) findViewById(R.id.llIsha);
        this.llBg = (LinearLayout) findViewById(R.id.llBg);
        this.btnPrayerSetting = (LinearLayout) findViewById(R.id.btnPrayerSetting);
        ImageView imageView = this.ivFajr;
        boolean fajrRing = this.prayerSharedPreference.getFajrRing();
        int i = R.drawable.ic_sound_on;
        imageView.setImageResource(fajrRing ? R.drawable.ic_sound_on : R.drawable.ic_sound_off);
        this.ivSunrise.setImageResource(this.prayerSharedPreference.getSunriseRing() ? R.drawable.ic_sound_on : R.drawable.ic_sound_off);
        this.ivDurur.setImageResource(this.prayerSharedPreference.getDururRing() ? R.drawable.ic_sound_on : R.drawable.ic_sound_off);
        this.ivAsr.setImageResource(this.prayerSharedPreference.getAsrRing() ? R.drawable.ic_sound_on : R.drawable.ic_sound_off);
        this.ivMaghrib.setImageResource(this.prayerSharedPreference.getMaghribRing() ? R.drawable.ic_sound_on : R.drawable.ic_sound_off);
        ImageView imageView2 = this.ivIsha;
        if (!this.prayerSharedPreference.getIshaRing()) {
            i = R.drawable.ic_sound_off;
        }
        imageView2.setImageResource(i);
        this.tvCurrentLocation.setText(this.prayerSharedPreference.getLocation());
        UmmalquraCalendar ummalquraCalendar = new UmmalquraCalendar();
        System.out.println();
        this.tvENDate.setText(currentDate());
        TextView textView = this.tvISEnDate;
        textView.setText(ummalquraCalendar.get(1) + " - " + ummalquraCalendar.getDisplayName(2, 2, Locale.ENGLISH) + " - " + ummalquraCalendar.get(5));
        TextView textView2 = this.tvISARDate;
        textView2.setText(ummalquraCalendar.get(1) + " - " + ummalquraCalendar.getDisplayName(2, 2, new Locale("ar")) + " - " + ummalquraCalendar.get(5));
        this.ivFajr.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getFajrRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setFajrRing(false);
                    AllPrayerActivity.this.ivFajr.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setFajrRing(true);
                AllPrayerActivity.this.ivFajr.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.ivSunrise.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getSunriseRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setSunriseRing(false);
                    AllPrayerActivity.this.ivSunrise.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setSunriseRing(true);
                AllPrayerActivity.this.ivSunrise.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.ivDurur.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getDururRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setDururRing(false);
                    AllPrayerActivity.this.ivDurur.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setDururRing(true);
                AllPrayerActivity.this.ivDurur.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.ivAsr.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getAsrRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setAsrRing(false);
                    AllPrayerActivity.this.ivAsr.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setAsrRing(true);
                AllPrayerActivity.this.ivAsr.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.ivMaghrib.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getMaghribRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setMaghribRing(false);
                    AllPrayerActivity.this.ivMaghrib.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setMaghribRing(true);
                AllPrayerActivity.this.ivMaghrib.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.ivIsha.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AllPrayerActivity.this.prayerSharedPreference.getIshaRing()) {
                    AllPrayerActivity.this.prayerSharedPreference.setIshaRing(false);
                    AllPrayerActivity.this.ivIsha.setImageResource(R.drawable.ic_sound_off);
                    return;
                }
                AllPrayerActivity.this.prayerSharedPreference.setIshaRing(true);
                AllPrayerActivity.this.ivIsha.setImageResource(R.drawable.ic_sound_on);
            }
        });
        this.btnPrayerSetting.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                AllPrayerActivity.this.startActivity(new Intent(AllPrayerActivity.this.getApplicationContext(), PrayerTimeConfigureActivity.class));

            }
        });
        getPrayerData();
    }

    public String currentDate() {
        Date time = Calendar.getInstance().getTime();
        PrintStream printStream = System.out;
        printStream.println("Current time => " + time);
        return new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(time);
    }

    public void getPrayerData() {
        double parseDouble = Double.parseDouble(this.prayerSharedPreference.getLatitude());
        double parseDouble2 = Double.parseDouble(this.prayerSharedPreference.getLongitude());
        double timeZone = getTimeZone();
        PrayerTime prayerTime = new PrayerTime();
        prayerTime.setTimeFormat(this.prayerSharedPreference.getPrayerTimePref());
        prayerTime.setCalcMethod(this.prayerSharedPreference.getCalMethodPref());
        prayerTime.setAsrJuristic(this.prayerSharedPreference.getAsarMethodPref());
        boolean z = false;
        prayerTime.setAdjustHighLats(0);
        prayerTime.setOffsets(new int[]{0, 0, 0, 0, 0, 0, 0});
        ArrayList<String> prayerTimes = prayerTime.getPrayerTimes(Calendar.getInstance(), parseDouble, parseDouble2, timeZone);
        System.out.println(prayerTimes.get(0));
        System.out.println(prayerTimes.get(1));
        System.out.println(prayerTimes.get(2));
        System.out.println(prayerTimes.get(3));
        System.out.println(prayerTimes.get(5));
        System.out.println(prayerTimes.get(6));
        this.tvFajr.setText(prayerTimes.get(0));
        this.tvSunrise.setText(prayerTimes.get(1));
        this.tvDurur.setText(prayerTimes.get(2));
        this.tvAsr.setText(prayerTimes.get(3));
        this.tvMaghrib.setText(prayerTimes.get(5));
        this.tvIsha.setText(prayerTimes.get(6));
        int i = Calendar.getInstance().get(11);
        if (Boolean.valueOf((i < 6 || i > 18) ? true : true).booleanValue()) {
            this.llBg.setBackgroundResource(R.drawable.bg_img_night);
        } else {
            this.llBg.setBackgroundResource(R.drawable.bg_img_day);
        }
        selectUpcomingTime(prayerTimes);
    }

    
    
    
    public void selectUpcomingTime(ArrayList<String> arrayList) {
        String str;
        String str2;
        String str3;
        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleDateFormat2;
        int i = 0;
        while (true) {
            Integer[] numArr = this.dataTime;
            str = null;
            if (i >= numArr.length) {
                str2 = null;
                str3 = null;
                break;
            } else if (getUpcomingTime(arrayList.get(numArr[i].intValue()))) {
                str3 = getPrayerName(this.dataTime[i].intValue());
                str2 = arrayList.get(this.dataTime[i].intValue());
                break;
            } else {
                i++;
            }
        }
        char c = 1;
        try {
            if (this.prayerSharedPreference.getPrayerTimePref() == 1) {
                simpleDateFormat = new SimpleDateFormat("HH:mm a");
                simpleDateFormat2 = new SimpleDateFormat("h:mm a");
            } else {
                simpleDateFormat = new SimpleDateFormat("H:mm");
                simpleDateFormat2 = new SimpleDateFormat("H:mm");
            }
            str = getLongToTime((simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(simpleDateFormat2.format(new Date(System.currentTimeMillis()))).getTime()) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.tvTimeLeftFajr.setVisibility(View.GONE);
        this.tvTimeLeftDurur.setVisibility(View.GONE);
        this.tvTimeLeftAsr.setVisibility(View.GONE);
        this.tvTimeLeftMaghrib.setVisibility(View.GONE);
        this.tvTimeLeftIsha.setVisibility(View.GONE);
        str3.hashCode();
        switch (str3.hashCode()) {
            case -1801299114:
                if (str3.equals("Maghrib")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 66144:
                break;
            case 2181987:
                if (str3.equals("Fajr")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2288579:
                if (str3.equals("Isha")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 66388660:
                if (str3.equals("Duhur")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.llMaghrib.setBackgroundColor(Color.parseColor("#F6F4F5"));
                this.tvTimeLeftMaghrib.setText("Upnext " + str);
                this.tvTimeLeftMaghrib.setVisibility(View.VISIBLE);
                return;
            case 1:
                this.llAsr.setBackgroundColor(Color.parseColor("#F6F4F5"));
                this.tvTimeLeftAsr.setText("Upnext " + str);
                this.tvTimeLeftAsr.setVisibility(View.VISIBLE);
                return;
            case 2:
                this.llFajr.setBackgroundColor(Color.parseColor("#F6F4F5"));
                this.tvTimeLeftFajr.setText("Upnext " + str);
                this.tvTimeLeftFajr.setVisibility(View.VISIBLE);
                return;
            case 3:
                this.llIsha.setBackgroundColor(Color.parseColor("#F6F4F5"));
                this.tvTimeLeftIsha.setText("Upnext " + str);
                this.tvTimeLeftIsha.setVisibility(View.VISIBLE);
                return;
            case 4:
                this.llDuhur.setBackgroundColor(Color.parseColor("#F6F4F5"));
                this.tvTimeLeftDurur.setText("Upnext " + str);
                this.tvTimeLeftDurur.setVisibility(View.VISIBLE);
                return;
            default:
                return;
        }
    }

    public static String getLongToTime(long j) {
        long j2 = j / 3600;
        long j3 = (j % 3600) / 60;
        long j4 = j % 60;
        return j2 == 0 ? String.format("%02d:%02d", Long.valueOf(j3), Long.valueOf(j4)) : String.format("%02d:%02d:%02d", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4));
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
            if (simpleDateFormat.parse(simpleDateFormat.format(date)).after(simpleDateFormat.parse(str))) {
                PrintStream printStream = System.out;
                printStream.println("Current time is greater than " + str);
                return false;
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Current time is less than " + str);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public double getTimeZone() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 3600000.0d;
    }

    
    @Override 
    public void onResume() {
        super.onResume();
        this.tvCurrentLocation.setText(this.prayerSharedPreference.getLocation());
        getPrayerData();
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        finish();
     
    }

    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;

            case R.id.privacy :
                Intent intent3 = new Intent(getApplicationContext(), Privacy_Policy_activity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                return true;
            case R.id.rate :
                if (isOnline()) {
                    Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName()));
                    intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent4);
                } else {
                    Toast makeText = Toast.makeText(getApplicationContext(), "No Internet Connection..", Toast.LENGTH_SHORT);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
                }
                return true;
            case R.id.share :
                if (isOnline()) {
                    Intent intent5 = new Intent("android.intent.action.SEND");
                    intent5.setType("text/plain");
                    intent5.putExtra("android.intent.extra.TEXT", "Hi! I'm using a great Islamic Dua - Quran Athan Prayer application. Check it out:http://play.google.com/store/apps/details?id=" + getPackageName());
                    intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Intent.createChooser(intent5, "Share with Friends"));
                } else {
                    Toast makeText2 = Toast.makeText(getApplicationContext(), "No Internet Connection..", Toast.LENGTH_SHORT);
                    makeText2.setGravity(17, 0, 0);
                    makeText2.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
