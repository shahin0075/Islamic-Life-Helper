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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.Util.Util;


public class ZakatActivity extends AppCompatActivity {
    LinearLayout btnCalculate;
    double businessCash;
    double cash;
    EditText etBusinessCash;
    EditText etCash;
    EditText etGS;
    EditText etOtherAsset;
    EditText etOtherSaving;
    EditText etStock;
    double gs;
    double otherAsset;
    double otherSaving;
    double stock;
    TextView tvTotalAssets;
    TextView tvTotalZakat;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_zakat);

     //   AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
       // adAdmob.FullscreenAd_Counter(this);



        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.tvTotalZakat = (TextView) findViewById(R.id.tvTotalZakat);
        this.tvTotalAssets = (TextView) findViewById(R.id.tvTotalAssets);
        this.etGS = (EditText) findViewById(R.id.etGS);
        this.etCash = (EditText) findViewById(R.id.etCash);
        this.etOtherSaving = (EditText) findViewById(R.id.etOtherSaving);
        this.etBusinessCash = (EditText) findViewById(R.id.etBusinessCash);
        this.etStock = (EditText) findViewById(R.id.etStock);
        this.etOtherAsset = (EditText) findViewById(R.id.etOtherAsset);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.btnCalculate);
        this.btnCalculate = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (ZakatActivity.this.etGS.getText().toString().equals("")) {
                    ZakatActivity.this.etGS.setText("0");
                }
                if (ZakatActivity.this.etCash.getText().toString().equals("")) {
                    ZakatActivity.this.etCash.setText("0");
                }
                if (ZakatActivity.this.etOtherSaving.getText().toString().equals("")) {
                    ZakatActivity.this.etOtherSaving.setText("0");
                }
                if (ZakatActivity.this.etBusinessCash.getText().toString().equals("")) {
                    ZakatActivity.this.etBusinessCash.setText("0");
                }
                if (ZakatActivity.this.etStock.getText().toString().equals("")) {
                    ZakatActivity.this.etStock.setText("0");
                }
                if (ZakatActivity.this.etOtherAsset.getText().toString().equals("")) {
                    ZakatActivity.this.etOtherAsset.setText("0");
                }
                ZakatActivity zakatActivity = ZakatActivity.this;
                zakatActivity.gs = Double.parseDouble(zakatActivity.etGS.getText().toString());
                ZakatActivity zakatActivity2 = ZakatActivity.this;
                zakatActivity2.cash = Double.parseDouble(zakatActivity2.etCash.getText().toString());
                ZakatActivity zakatActivity3 = ZakatActivity.this;
                zakatActivity3.otherSaving = Double.parseDouble(zakatActivity3.etOtherSaving.getText().toString());
                ZakatActivity zakatActivity4 = ZakatActivity.this;
                zakatActivity4.businessCash = Double.parseDouble(zakatActivity4.etBusinessCash.getText().toString());
                ZakatActivity zakatActivity5 = ZakatActivity.this;
                zakatActivity5.stock = Double.parseDouble(zakatActivity5.etStock.getText().toString());
                ZakatActivity zakatActivity6 = ZakatActivity.this;
                zakatActivity6.otherAsset = Double.parseDouble(zakatActivity6.etOtherAsset.getText().toString());
                double d = ZakatActivity.this.gs + ZakatActivity.this.cash + ZakatActivity.this.otherSaving + ZakatActivity.this.businessCash + ZakatActivity.this.stock + ZakatActivity.this.otherAsset;
                TextView textView = ZakatActivity.this.tvTotalAssets;
                textView.setText("" + Util.format(d));
                TextView textView2 = ZakatActivity.this.tvTotalZakat;
                textView2.setText("" + Util.format(d * 0.025d));
                Util.dismissKeyboard(ZakatActivity.this);
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
