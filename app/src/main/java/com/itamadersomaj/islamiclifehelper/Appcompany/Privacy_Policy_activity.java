package com.itamadersomaj.islamiclifehelper.Appcompany;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.itamadersomaj.islamiclifehelper.R;


public class Privacy_Policy_activity extends AppCompatActivity {
    private static final String TAG = "Main";
    Context context;

    private ProgressDialog progress;
    Toolbar toolbar;
    private WebView webvw;
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.privacy_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        this.toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setTitle("Permission");
        this.context = this;
        this.webvw = (WebView) findViewById(R.id.webview);
        this.webvw.getSettings().setJavaScriptEnabled(true);
        this.webvw.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog create = new AlertDialog.Builder(this).create();
        this.progress = ProgressDialog.show(this, "Please Wait...", "Loading...");
        this.webvw.setWebViewClient(new WebViewClient() { 
            @Override 
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.i(Privacy_Policy_activity.TAG, "Processing webview url click...");
                webView.loadUrl(str);
                return true;
            }

            @Override 
            public void onPageFinished(WebView webView, String str) {
                Log.i(Privacy_Policy_activity.TAG, "Finished loading URL: " + str);
                if (Privacy_Policy_activity.this.progress.isShowing()) {
                    Privacy_Policy_activity.this.progress.dismiss();
                }
            }

            @Override 
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Log.e(Privacy_Policy_activity.TAG, "Error: " + str);
                create.setTitle("Error");
                create.setMessage(str);
                create.setButton("OK", new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialogInterface, int i2) {
                    }
                });
                create.show();
            }
        });
        this.webvw.loadUrl("https://sites.google.com/view/islamic-life-helper/home");
    }

    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case R.id.privacy :
                Intent intent2 = new Intent(getApplicationContext(), Privacy_Policy_activity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                return true;
            case R.id.rate :
                if (isOnline()) {
                    Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName()));
                    intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent3);
                } else {
                    Toast makeText = Toast.makeText(getApplicationContext(), "No Internet Connection..", Toast.LENGTH_SHORT);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
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
