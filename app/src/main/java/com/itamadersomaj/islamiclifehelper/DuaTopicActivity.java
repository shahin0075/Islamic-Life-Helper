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
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itamadersomaj.islamiclifehelper.Adapter.DuaTopicAdapter;
import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.DuaTopicModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class DuaTopicActivity extends AppCompatActivity implements OnItemClickListener {
    String categoryImage;
    DatabaseAccess databaseAccess;
    DuaTopicAdapter duaTopicAdapter;
    RelativeLayout layout;
    RecyclerView rvDuaTopic;
    AppCompatTextView toolbar_text;
    int categoryId = 0;
    List<Integer> getCategoryTopicIds = new ArrayList();
    List<DuaTopicModel> duaTopicModelList = new ArrayList();
    String categoryName = "";

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dua_topic);


     //   AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      //  adAdmob.FullscreenAd_Counter(this);



        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.categoryId = getIntent().getIntExtra("categoryId", 0);
        this.categoryName = getIntent().getStringExtra("categoryName");
        this.categoryImage = getIntent().getStringExtra("categoryImage");
        this.rvDuaTopic = (RecyclerView) findViewById(R.id.rvDuaTopic);
        AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.toolbar_text = appCompatTextView;
        appCompatTextView.setText(this.categoryName);
        this.databaseAccess.open();
        this.getCategoryTopicIds = this.databaseAccess.getDuaCategoryTopic(this.categoryId);
        this.databaseAccess.close();
        this.databaseAccess.open();
        for (int i = 0; i < this.getCategoryTopicIds.size(); i++) {
            DuaTopicModel duaTopic = this.databaseAccess.getDuaTopic(this.getCategoryTopicIds.get(i).intValue());
            DuaTopicModel duaTopicModel = new DuaTopicModel();
            duaTopicModel.setId(duaTopic.getId());
            duaTopicModel.setTopicName(duaTopic.getTopicName());
            duaTopicModel.setImageFilePath(this.categoryImage);
            this.duaTopicModelList.add(duaTopicModel);
        }
        this.databaseAccess.close();
        this.rvDuaTopic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvDuaTopic.setItemAnimator(new DefaultItemAnimator());
        DuaTopicAdapter duaTopicAdapter = new DuaTopicAdapter(this, this.duaTopicModelList, this);
        this.duaTopicAdapter = duaTopicAdapter;
        this.rvDuaTopic.setAdapter(duaTopicAdapter);
    }

    @Override 
    public void OnClick(View view, int i) {
        Intent intent = new Intent(getApplicationContext(), DuaActivity.class);
        intent.putExtra("topicId", this.duaTopicModelList.get(i).getId());
        intent.putExtra("topicName", this.duaTopicModelList.get(i).getTopicName());
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
