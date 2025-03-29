package com.itamadersomaj.islamiclifehelper;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itamadersomaj.islamiclifehelper.Adapter.QuranCategoryAdapter;
import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.AlQuranCategoryModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class QuranCategoryActivity extends AppCompatActivity implements OnItemClickListener {
    QuranCategoryAdapter alQuranCategoryAdapter;
    List<AlQuranCategoryModel> alQuranCategoryModelList = new ArrayList();
    LinearLayout btnFavorite;
    DatabaseAccess databaseAccess;
    RelativeLayout layout;
    RecyclerView rvAlQuranCategory;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        setContentView(R.layout.activity_quran_category);

     //   AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
       // adAdmob.FullscreenAd_Counter(this);




        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.btnFavorite = (LinearLayout) findViewById(R.id.btnFavorite);
        this.rvAlQuranCategory = (RecyclerView) findViewById(R.id.rvAlQuranCategory);
        this.databaseAccess.open();
        this.alQuranCategoryModelList = this.databaseAccess.getAlQuranCategory();
        this.databaseAccess.close();
        this.rvAlQuranCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvAlQuranCategory.setItemAnimator(new DefaultItemAnimator());
        QuranCategoryAdapter quranCategoryAdapter = new QuranCategoryAdapter(this, this.alQuranCategoryModelList, this);
        this.alQuranCategoryAdapter = quranCategoryAdapter;
        this.rvAlQuranCategory.setAdapter(quranCategoryAdapter);
        this.btnFavorite.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                QuranCategoryActivity.this.startActivity(new Intent(QuranCategoryActivity.this, QuranFavoriteActivity.class));

            }
        });
    }

    @Override 
    public void OnClick(View view, int i) {
        Intent intent = new Intent(this, QuranActivity.class);
        intent.putExtra("quranId", this.alQuranCategoryModelList.get(i).getId());
        intent.putExtra("quranName", this.alQuranCategoryModelList.get(i).getArabicTranslation());
        startActivity(intent);
     
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
