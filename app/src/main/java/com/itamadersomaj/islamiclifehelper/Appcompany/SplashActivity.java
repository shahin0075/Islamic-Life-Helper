package com.itamadersomaj.islamiclifehelper.Appcompany;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;


import com.itamadersomaj.islamiclifehelper.R;

import com.itamadersomaj.islamiclifehelper.MainActivity;


public class SplashActivity extends Activity {
    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        setContentView(R.layout.splashactivity);

            ContinueWithoutAdsProcess();

    }
    private void ContinueWithoutAdsProcess() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, 2000L);
    }
    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        ExitApp();
    }
    public void ExitApp() {
        moveTaskToBack(true);
        finish();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
