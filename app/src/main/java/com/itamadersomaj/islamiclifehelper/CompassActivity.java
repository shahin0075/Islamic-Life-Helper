package com.itamadersomaj.islamiclifehelper;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private float currentCompassDegree = 0.0f;
    private ImageView imgCompass;
    private ImageView imgCursor;
    private double latDouble;
    RelativeLayout layout;
    private double longDouble;
    private SensorManager mSensorManager;
    PrayerSharedPreference prayerSharedPreference;
    private TextView tvHeading;
    private TextView tvLocation;

    @Override 
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_compass);

       // AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      //  adAdmob.FullscreenAd_Counter(this);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.tvLocation = (TextView) findViewById(R.id.loc);
        this.imgCompass = (ImageView) findViewById(R.id.image_compass);
        this.imgCursor = (ImageView) findViewById(R.id.image_cursor);
        this.tvHeading = (TextView) findViewById(R.id.heading);
        this.tvLocation.setText(this.prayerSharedPreference.getLocation());
        this.longDouble = Double.parseDouble(this.prayerSharedPreference.getLongitude());
        this.latDouble = Double.parseDouble(this.prayerSharedPreference.getLatitude());
        this.mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override 
    public void onResume() {
        super.onResume();
        if (this.mSensorManager.getDefaultSensor(3) != null) {
            SensorManager sensorManager = this.mSensorManager;
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(3), 1);
            return;
        }
        Toast.makeText(getApplicationContext(), "Sorry the Qibla feature is not supported by your device", Toast.LENGTH_LONG).show();
    }

    @Override 
    public void onPause() {
        super.onPause();
        this.mSensorManager.unregisterListener(this);
    }

    @Override 
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = -Math.round(sensorEvent.values[0]);
        float calculateQibla = f + calculateQibla(this.latDouble, this.longDouble);
        TextView textView = this.tvHeading;
        textView.setText("Heading: " + calculateQibla + " degrees");
        RotateAnimation rotateAnimation = new RotateAnimation(this.currentCompassDegree, f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(210L);
        rotateAnimation.setFillAfter(true);
        this.imgCompass.startAnimation(rotateAnimation);
        this.imgCursor.setRotation(calculateQibla);
        this.currentCompassDegree = f;
    }

    public float calculateQibla(double d, double d2) {
        double d3 = (d * 3.141592653589793d) / 180.0d;
        double d4 = 0.6946410422937431d - ((d2 * 3.141592653589793d) / 180.0d);
        return (float) Math.round(Math.atan2(Math.sin(d4), (Math.cos(d3) * Math.tan(0.3735004599267865d)) - (Math.sin(d3) * Math.cos(d4))) * 57.29577951308232d);
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
