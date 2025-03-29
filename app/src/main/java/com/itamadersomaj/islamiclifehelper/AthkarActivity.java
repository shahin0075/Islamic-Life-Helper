package com.itamadersomaj.islamiclifehelper;

import android.content.Context;
import android.content.Intent;
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
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.itamadersomaj.islamiclifehelper.Adapter.ViewPager.ViewPagerAdapter;
import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DbHelper.AthkarDatabase;
import com.itamadersomaj.islamiclifehelper.Fragment.AthkarFragment;
import com.itamadersomaj.islamiclifehelper.Model.AthkarModel;
import java.util.List;


public class AthkarActivity extends AppCompatActivity {
    AthkarDatabase athkarDatabase;
    List<AthkarModel> athkarEveningModelList;
    List<AthkarModel> athkarMorningModelList;
    ImageView ivEveningDot;
    ImageView ivMorningDot;
    RelativeLayout layout;
    LinearLayout llEvening;
    LinearLayout llMorning;
    TextView tvEvening;
    TextView tvMorning;
    ViewPager vpAthkar;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_athkar);




        //AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        //adAdmob.FullscreenAd_Counter(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        
        this.tvMorning = (TextView) findViewById(R.id.tvMorning);
        this.tvEvening = (TextView) findViewById(R.id.tvEvening);
        this.llMorning = (LinearLayout) findViewById(R.id.llMorning);
        this.llEvening = (LinearLayout) findViewById(R.id.llEvening);
        this.ivMorningDot = (ImageView) findViewById(R.id.ivMorningDot);
        this.ivEveningDot = (ImageView) findViewById(R.id.ivEveningDot);
        this.vpAthkar = (ViewPager) findViewById(R.id.vpAthkar);
        AthkarDatabase athkarDatabase = new AthkarDatabase(this);
        this.athkarDatabase = athkarDatabase;
        athkarDatabase.openDB();
        this.athkarMorningModelList = this.athkarDatabase.getAthkarModelList("0");
        this.athkarEveningModelList = this.athkarDatabase.getAthkarModelList("1");
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new AthkarFragment(this, this.athkarMorningModelList), "Morning");
        viewPagerAdapter.add(new AthkarFragment(this, this.athkarEveningModelList), "Evening");
        this.vpAthkar.setAdapter(viewPagerAdapter);
        this.llMorning.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                AthkarActivity.this.vpAthkar.setCurrentItem(0, true);
            }
        });
        this.llEvening.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                AthkarActivity.this.vpAthkar.setCurrentItem(1, true);
            }
        });
        this.vpAthkar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { 
            @Override 
            public void onPageScrollStateChanged(int i) {
            }

            @Override 
            public void onPageSelected(int i) {
            }

            @Override 
            public void onPageScrolled(int i, float f, int i2) {
                if (i == 0) {
                    AthkarActivity.this.llMorning.setBackgroundResource(R.drawable.round_corner);
                    AthkarActivity.this.ivMorningDot.setVisibility(View.VISIBLE);
                    AthkarActivity.this.tvMorning.setTextColor(AthkarActivity.this.getResources().getColor(R.color.white));
                    AthkarActivity.this.tvMorning.setTypeface(ResourcesCompat.getFont(AthkarActivity.this, R.font.biko_bold_700));
                    AthkarActivity.this.llEvening.setBackgroundResource(R.drawable.whitebackground);
                    AthkarActivity.this.ivEveningDot.setVisibility(View.GONE);
                    AthkarActivity.this.tvEvening.setTextColor(AthkarActivity.this.getResources().getColor(R.color.green));
                    AthkarActivity.this.tvEvening.setTypeface(ResourcesCompat.getFont(AthkarActivity.this, R.font.biko_regular_400));
                    return;
                }
                AthkarActivity.this.llEvening.setBackgroundResource(R.drawable.round_corner);
                AthkarActivity.this.ivEveningDot.setVisibility(View.VISIBLE);
                AthkarActivity.this.tvEvening.setTextColor(AthkarActivity.this.getResources().getColor(R.color.white));
                AthkarActivity.this.tvEvening.setTypeface(ResourcesCompat.getFont(AthkarActivity.this, R.font.biko_bold_700));
                AthkarActivity.this.llMorning.setBackgroundResource(R.drawable.whitebackground);
                AthkarActivity.this.ivMorningDot.setVisibility(View.GONE);
                AthkarActivity.this.tvMorning.setTextColor(AthkarActivity.this.getResources().getColor(R.color.green));
                AthkarActivity.this.tvMorning.setTypeface(ResourcesCompat.getFont(AthkarActivity.this, R.font.biko_regular_400));
            }
        });
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
