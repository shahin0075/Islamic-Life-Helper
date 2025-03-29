package com.itamadersomaj.islamiclifehelper;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DbHelper.AthkarDatabase;
import com.itamadersomaj.islamiclifehelper.HijriCalender.HijriCalenderActivity;
import com.itamadersomaj.islamiclifehelper.PrayerTimeCal.PrayerTime;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import com.itamadersomaj.islamiclifehelper.Service.PrayerTimeService;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final int REQUEST_CODE = 1010101;

    LinearLayout AlQuran;
    LinearLayout btnAllPrayer;
    LinearLayout btnAthkar;
    LinearLayout btnDua;
    LinearLayout btnDua99AllahName;
    LinearLayout btnHajj;
    LinearLayout btnHijriCalender;
    RelativeLayout btnNotificationSetting;
    RelativeLayout btnQiblaCompass;
    LinearLayout btnTabish;
    RelativeLayout btnZakat;
    Integer[] dataTime = {0, 1, 2, 3, 5, 6};
    boolean isChangeConfigClick = true;
    RelativeLayout llLoadingData;
    LinearLayout llPrayerTime;
    PrayerSharedPreference prayerSharedPreference;
    
    TextView tvAfterPrayer;
    TextView tvNextPrayer;

    public String getPrayerName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 5 ? i != 6 ? "Unknown" : "Isha" : "Maghrib" : "Asr" : "Duhur" : "Sunrise" : "Fajr";
    }

    @Override 
    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(Color.parseColor("#E5DDD0"));
        setContentView(R.layout.activity_main);

     //   AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      // adAdmob.FullscreenAd_Counter(this);





        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.btnQiblaCompass = (RelativeLayout) findViewById(R.id.btnQiblaCompass);
        this.btnDua = (LinearLayout) findViewById(R.id.btnDua);
        this.btnDua99AllahName = (LinearLayout) findViewById(R.id.btnDua99AllahName);
        this.btnTabish = (LinearLayout) findViewById(R.id.btnTabish);
        this.AlQuran = (LinearLayout) findViewById(R.id.AlQuran);
        this.btnHijriCalender = (LinearLayout) findViewById(R.id.btnHijriCalender);
        this.btnAllPrayer = (LinearLayout) findViewById(R.id.btnAllPrayer);
        this.btnAthkar = (LinearLayout) findViewById(R.id.btnAthkar);
        this.btnZakat = (RelativeLayout) findViewById(R.id.btnZakat);
        this.btnHajj = (LinearLayout) findViewById(R.id.btnHajj);
        this.btnNotificationSetting = (RelativeLayout) findViewById(R.id.btnNotificationSetting);
        this.llLoadingData = (RelativeLayout) findViewById(R.id.llLoadingData);
        this.llPrayerTime = (LinearLayout) findViewById(R.id.llPrayerTime);
        this.tvNextPrayer = (TextView) findViewById(R.id.tvNextPrayer);
        this.tvAfterPrayer = (TextView) findViewById(R.id.tvAfterPrayer);
        new AthkarDatabase(this).createDb();
        this.btnQiblaCompass.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), CompassActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnDua.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), DuaCategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnDua99AllahName.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), AllahNameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.AlQuran.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), QuranCategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnHijriCalender.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), HijriCalenderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnTabish.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), TasbihCounterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnAllPrayer.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (!MainActivity.this.tvNextPrayer.getText().toString().equals("--:-- --")) {
                    Intent intent = new Intent(MainActivity.this.getApplicationContext(), AllPrayerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Not yet prayer time", Toast.LENGTH_SHORT).show();
            }
        });
        this.btnAthkar.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), AthkarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnZakat.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), ZakatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnHajj.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), HajjJourneyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnNotificationSetting.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);
            }
        });
        if (!isMyServiceRunning(PrayerTimeService.class, this)) {
            Intent intent = new Intent(this, PrayerTimeService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                showDialogPermission();
            } else if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
            } else if (isLocationEnabled(this)) {
                if (this.prayerSharedPreference.getIsFirstTimeRunPref()) {
                    this.prayerSharedPreference.setIsFirstTimeRunPref(false);
                    this.isChangeConfigClick = true;
                    startActivity(new Intent(getApplicationContext(), PrayerTimeConfigureActivity.class));
                }
            } else {
                showDialog();
            }
        }
    }

    public void showDialogPermission() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.show_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((RelativeLayout) dialog.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                if (Build.VERSION.SDK_INT >= 23) {
                    MainActivity.this.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + MainActivity.this.getApplicationContext().getPackageName())), MainActivity.REQUEST_CODE);
                }
            }
        });
        dialog.show();
    }

    @Override 
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.e("App", "OnActivity Result.");
        if (i == 1001) {
            if (isLocationEnabled(this)) {
                if (this.prayerSharedPreference.getIsFirstTimeRunPref()) {
                    this.prayerSharedPreference.setIsFirstTimeRunPref(false);
                    this.isChangeConfigClick = true;
                    startActivity(new Intent(getApplicationContext(), PrayerTimeConfigureActivity.class));
                }
            } else {
                showDialog();
            }
        }
        if (i != 1010101 || Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            showDialogPermission();
        } else if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else if (isLocationEnabled(this)) {
            if (this.prayerSharedPreference.getIsFirstTimeRunPref()) {
                this.prayerSharedPreference.setIsFirstTimeRunPref(false);
                this.isChangeConfigClick = true;
                startActivity(new Intent(getApplicationContext(), PrayerTimeConfigureActivity.class));
            }
        } else {
            showDialog();
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

    @Override 
    protected void onResume() {
        super.onResume();
        if (this.prayerSharedPreference.getIsFirstTimeRunPref() || !this.prayerSharedPreference.isPrayerLoadData()) {
            return;
        }
        this.tvNextPrayer.setText("--:-- --");
        this.tvAfterPrayer.setText("--:-- --");
        this.llLoadingData.setVisibility(View.VISIBLE);
        this.llPrayerTime.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() { 
            @Override 
            public void run() {
                MainActivity.this.isChangeConfigClick = false;
                MainActivity.this.getPrayerData();
            }
        }, 1000L);
    }

    public void getPrayerData() {
        this.llLoadingData.setVisibility(View.GONE);
        this.llPrayerTime.setVisibility(View.VISIBLE);
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
        System.out.println(prayerTimes.get(0));
        System.out.println(prayerTimes.get(1));
        System.out.println(prayerTimes.get(2));
        System.out.println(prayerTimes.get(3));
        System.out.println(prayerTimes.get(5));
        System.out.println(prayerTimes.get(6));
        selectUpcomingTime(prayerTimes);
    }

    public double getTimeZone() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 3600000.0d;
    }

    public void selectUpcomingTime(ArrayList<String> arrayList) {
        int i = 0;
        while (true) {
            Integer[] numArr = this.dataTime;
            if (i >= numArr.length) {
                return;
            }
            if (getUpcomingTime(arrayList.get(numArr[i].intValue()))) {
                TextView textView = this.tvNextPrayer;
                textView.setText(getPrayerName(this.dataTime[i].intValue()) + " " + arrayList.get(this.dataTime[i].intValue()));
                Integer[] numArr2 = this.dataTime;
                if (numArr2.length - 1 != i) {
                    if (numArr2[i].intValue() == 3) {
                        TextView textView2 = this.tvAfterPrayer;
                        textView2.setText(getPrayerName(this.dataTime[i].intValue() + 2) + " " + arrayList.get(this.dataTime[i].intValue() + 2));
                        return;
                    }
                    TextView textView3 = this.tvAfterPrayer;
                    textView3.setText(getPrayerName(this.dataTime[i].intValue() + 1) + " " + arrayList.get(this.dataTime[i].intValue() + 1));
                    return;
                }
                this.tvAfterPrayer.setText("No upcoming prayer");
                return;
            }
            i++;
        }
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

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.location_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((RelativeLayout) dialog.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                MainActivity.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1001);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override 
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1 || iArr.length <= 0) {
            return;
        }
        if (iArr[0] == 0) {
            if (isLocationEnabled(this)) {
                if (this.prayerSharedPreference.getIsFirstTimeRunPref()) {
                    this.prayerSharedPreference.setIsFirstTimeRunPref(false);
                    this.isChangeConfigClick = true;
                    startActivity(new Intent(getApplicationContext(), PrayerTimeConfigureActivity.class));
                    return;
                }
                return;
            }
            showDialog();
            return;
        }
        Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
    }

    public static boolean isLocationEnabled(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
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
                    intent5.putExtra("android.intent.extra.TEXT", "Hi! I'm using a great Islamic Life Helper application. Check it out:http://play.google.com/store/apps/details?id=" + getPackageName());
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
