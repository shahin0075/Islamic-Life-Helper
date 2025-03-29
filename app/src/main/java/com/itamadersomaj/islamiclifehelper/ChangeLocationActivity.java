package com.itamadersomaj.islamiclifehelper;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import com.itamadersomaj.islamiclifehelper.Service.FetchAddressIntentServices;
import com.itamadersomaj.islamiclifehelper.Util.Constants;
import java.io.PrintStream;
import java.text.DecimalFormat;


public class ChangeLocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {
    LinearLayout btnOkChange;
    double lat;
    String location;
    double lon;
    private GoogleMap mMap;
    Marker marker;
    MarkerOptions markerOptions;
    PrayerSharedPreference prayerSharedPreference;
    ResultReceiver resultReceiver;
    TextView tvLatitude;
    TextView tvLocation;
    TextView tvLongitude;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_change_location);

       // AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      // adAdmob.FullscreenAd_Counter(this);





        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.resultReceiver = new AddressResultReceiver(new Handler());
        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.lat = Double.parseDouble(getIntent().getStringExtra("lat"));
        this.lon = Double.parseDouble(getIntent().getStringExtra("lon"));
        this.location = getIntent().getStringExtra("location");
        PrintStream printStream = System.out;
        printStream.println(this.lat + "--" + this.lon);
        this.tvLocation = (TextView) findViewById(R.id.tvLocation);
        this.tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        this.tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        this.tvLocation.setText(this.location);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.btnOkChange);
        this.btnOkChange = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                ChangeLocationActivity.this.prayerSharedPreference.setLocation(ChangeLocationActivity.this.tvLocation.getText().toString());
                ChangeLocationActivity.this.prayerSharedPreference.setLatitude(ChangeLocationActivity.this.tvLatitude.getText().toString());
                ChangeLocationActivity.this.prayerSharedPreference.setLongitude(ChangeLocationActivity.this.tvLongitude.getText().toString());
                ChangeLocationActivity.this.setResult(-1, new Intent());
                ChangeLocationActivity.this.finish();
            }
        });
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override 
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        googleMap.setOnCameraIdleListener(this);
        LatLng latLng = new LatLng(this.lat, this.lon);
        this.mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        MarkerOptions position = new MarkerOptions().position(latLng);
        this.markerOptions = position;
        this.marker = this.mMap.addMarker(position);
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11.0f));
    }

    @Override 
    public void onCameraIdle() {
        Log.e("lllllllllllllll", this.mMap.getCameraPosition().target.latitude + "--" + this.mMap.getCameraPosition().target.longitude);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(3);
        TextView textView = this.tvLatitude;
        textView.setText(decimalFormat.format(this.mMap.getCameraPosition().target.latitude) + "");
        TextView textView2 = this.tvLongitude;
        textView2.setText(decimalFormat.format(this.mMap.getCameraPosition().target.longitude) + "");
        Location location = new Location("providerNA");
        location.setLatitude(this.mMap.getCameraPosition().target.latitude);
        location.setLongitude(this.mMap.getCameraPosition().target.longitude);
        fetchAddressFromLocation(location);
    }

    
    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override 
        protected void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            if (i == 1) {
                String string = bundle.getString(Constants.LOCAITY) != null ? bundle.getString(Constants.LOCAITY) : "Not Found";
                try {
                    TextView textView = ChangeLocationActivity.this.tvLocation;
                    textView.setText(string + ", " + bundle.getString(Constants.COUNTRY));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            Toast.makeText(ChangeLocationActivity.this, bundle.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchAddressFromLocation(Location location) {
        Intent intent = new Intent(this, FetchAddressIntentServices.class);
        intent.putExtra(Constants.RECEVIER, this.resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    @Override 
    public void onBackPressed() {
        setResult(0, new Intent());
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
