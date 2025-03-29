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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.DuaModel;
import com.itamadersomaj.islamiclifehelper.Service.PrayerTimeService;
import com.itamadersomaj.islamiclifehelper.Util.Util;


public class TodayDuaActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    DuaModel duaModel;
    int id;
    ImageView ivFavorite;
    ImageView ivShare;
    TextView tvDua;
    TextView tvEnglishMeaning;
    TextView tvReference;
    TextView tvTranslation;

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_today_dua);


      //  AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      //  adAdmob.FullscreenAd_Counter(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.tvDua = (TextView) findViewById(R.id.tvDua);
        this.tvTranslation = (TextView) findViewById(R.id.tvTranslation);
        this.tvEnglishMeaning = (TextView) findViewById(R.id.tvEnglishMeaning);
        this.tvReference = (TextView) findViewById(R.id.tvReference);
        this.ivFavorite = (ImageView) findViewById(R.id.ivFavorite);
        this.ivShare = (ImageView) findViewById(R.id.ivShare);
        this.id = PrayerTimeService.duaId;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        this.databaseAccess = databaseAccess;
        DuaModel dua = databaseAccess.getDua(this.id);
        this.duaModel = dua;
        this.tvDua.setText(dua.getDua());
        this.tvTranslation.setText(this.duaModel.getTranslation());
        this.tvEnglishMeaning.setText(this.duaModel.getEnMeaning());
        this.tvReference.setText(this.duaModel.getEnReference());
        if (this.duaModel.isFavorite()) {
            this.ivFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            this.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
        this.ivFavorite.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (TodayDuaActivity.this.duaModel.isFavorite()) {
                    TodayDuaActivity.this.duaModel.setFavorite(false);
                    TodayDuaActivity.this.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                } else {
                    TodayDuaActivity.this.duaModel.setFavorite(true);
                    TodayDuaActivity.this.ivFavorite.setImageResource(R.drawable.ic_favorite);
                }
                TodayDuaActivity.this.databaseAccess.updateFavoriteDua(TodayDuaActivity.this.duaModel);
            }
        });
        this.ivShare.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Util.shareText(TodayDuaActivity.this, TodayDuaActivity.this.duaModel.getDua() + "\n\n" + TodayDuaActivity.this.duaModel.getTranslation() + "\n\n" + TodayDuaActivity.this.duaModel.getEnMeaning());
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
