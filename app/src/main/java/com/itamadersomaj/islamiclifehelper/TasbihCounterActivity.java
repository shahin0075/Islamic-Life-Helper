package com.itamadersomaj.islamiclifehelper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.DhikrModel;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;


public class TasbihCounterActivity extends AppCompatActivity {
    LinearLayout btnChangeCounter;
    LinearLayout btnCount;
    LinearLayout btnDhikar;
    LinearLayout btnReset;
    CircularProgressIndicator circular_progress;
    DatabaseAccess databaseAccess;
    RelativeLayout layout;
    int loopCount = 0;
    int maxCount = 33;
    PrayerSharedPreference prayerSharedPreference;
    TextView tvArabicName;
    TextView tvEnglishMeaning;
    TextView tvEnglishName;
    TextView tvLoop;
    TextView tvTasbihCount;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        setContentView(R.layout.activity_tasbih_counter);

       // AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      //  adAdmob.FullscreenAd_Counter(this);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.btnReset = (LinearLayout) findViewById(R.id.btnReset);
        this.btnCount = (LinearLayout) findViewById(R.id.btnCount);
        this.btnChangeCounter = (LinearLayout) findViewById(R.id.btnChangeCounter);
        this.btnDhikar = (LinearLayout) findViewById(R.id.btnDhikar);
        this.tvEnglishName = (TextView) findViewById(R.id.tvEnglishName);
        this.tvArabicName = (TextView) findViewById(R.id.tvArabicName);
        this.tvEnglishMeaning = (TextView) findViewById(R.id.tvEnglishMeaning);
        this.tvTasbihCount = (TextView) findViewById(R.id.tvTasbihCount);
        this.tvLoop = (TextView) findViewById(R.id.tvLoop);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) findViewById(R.id.circular_progress);
        this.circular_progress = circularProgressIndicator;
        circularProgressIndicator.setMaxProgress(33.0d);
        TextView textView = this.tvLoop;
        textView.setText(this.loopCount + "");
        this.tvTasbihCount.setText("Count : 33");
        this.btnReset.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                TasbihCounterActivity.this.circular_progress.setCurrentProgress(0.0d);
                TasbihCounterActivity.this.loopCount = 0;
                TextView textView2 = TasbihCounterActivity.this.tvLoop;
                textView2.setText(TasbihCounterActivity.this.loopCount + "");
            }
        });
        this.btnCount.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (((int) TasbihCounterActivity.this.circular_progress.getProgress()) == TasbihCounterActivity.this.maxCount) {
                    TasbihCounterActivity.this.circular_progress.setCurrentProgress(1.0d);
                    TasbihCounterActivity.this.loopCount++;
                    TasbihCounterActivity.this.tvLoop.setText(TasbihCounterActivity.this.loopCount + "");
                    return;
                }
                TasbihCounterActivity.this.circular_progress.setCurrentProgress((int)TasbihCounterActivity.this.circular_progress.getProgress() + 1);
            }
        });
        this.btnChangeCounter.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                TasbihCounterActivity.this.changeCounterDialog();
            }
        });
        this.btnDhikar.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                TasbihCounterActivity.this.startActivity(new Intent(TasbihCounterActivity.this.getApplicationContext(), SelectDhikrActivity.class));
            }
        });
    }

    public void changeCounterDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_change_counter);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        dialog.show();
        final EditText editText = (EditText) dialog.findViewById(R.id.edCounter);
        editText.setText(this.maxCount + "");
        ((CardView) dialog.findViewById(R.id.btnYes)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (!editText.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    int parseInt = Integer.parseInt(editText.getText().toString());
                    TasbihCounterActivity.this.maxCount = parseInt;
                    TasbihCounterActivity.this.circular_progress.setMaxProgress(parseInt);
                    TextView textView = TasbihCounterActivity.this.tvTasbihCount;
                    textView.setText("Count : " + TasbihCounterActivity.this.maxCount);
                    return;
                }
                Toast.makeText(TasbihCounterActivity.this, "Enter number", Toast.LENGTH_SHORT).show();
            }
        });
        ((CardView) dialog.findViewById(R.id.btnNo)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    
    @Override 
    public void onResume() {
        super.onResume();
        this.databaseAccess.open();
        DhikrModel selectedDhikr = this.databaseAccess.getSelectedDhikr(this.prayerSharedPreference.getDhikrId());
        this.databaseAccess.close();
        this.tvEnglishName.setText(selectedDhikr.getDhikrEnglishName());
        this.tvArabicName.setText(selectedDhikr.getDhikrArabicName());
        this.tvEnglishMeaning.setText(selectedDhikr.getDhikrEnglishMeaning());
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
