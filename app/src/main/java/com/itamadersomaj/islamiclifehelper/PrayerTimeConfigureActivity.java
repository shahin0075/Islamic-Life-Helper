package com.itamadersomaj.islamiclifehelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
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
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import com.itamadersomaj.islamiclifehelper.Service.FetchAddressIntentServices;
import com.itamadersomaj.islamiclifehelper.Util.Constants;
import java.util.List;
import java.util.Locale;


public class PrayerTimeConfigureActivity extends AppCompatActivity {
    LinearLayout btnChangeLocation;
    LinearLayout btnCurrentLocation;
    ImageView iv12Format;
    ImageView iv24Format;
    ImageView ivAsarMethod0;
    ImageView ivAsarMethod1;
    ImageView ivMethod0;
    ImageView ivMethod1;
    ImageView ivMethod2;
    ImageView ivMethod3;
    ImageView ivMethod4;
    ImageView ivMethod5;
    ImageView ivMethod6;
    double lati;
    RelativeLayout layout;
    LinearLayout ll12Format;
    LinearLayout ll24Format;
    LinearLayout llAsarMethod0;
    LinearLayout llAsarMethod1;
    LinearLayout llMethod0;
    LinearLayout llMethod1;
    LinearLayout llMethod2;
    LinearLayout llMethod3;
    LinearLayout llMethod4;
    LinearLayout llMethod5;
    LinearLayout llMethod6;
    Dialog loadingDialog;
    double longi;
    PrayerSharedPreference prayerSharedPreference;
    ResultReceiver resultReceiver;
    TextView tvLatitude;
    TextView tvLocation;
    TextView tvLongitude;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_prayer_time_configure);


       // AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
       // adAdmob.FullscreenAd_Counter(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.resultReceiver = new AddressResultReceiver(new Handler());
        this.prayerSharedPreference = new PrayerSharedPreference(this);
        this.tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        this.tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        this.tvLocation = (TextView) findViewById(R.id.tvLocation);
        this.ll24Format = (LinearLayout) findViewById(R.id.ll24Format);
        this.ll12Format = (LinearLayout) findViewById(R.id.ll12Format);
        this.iv24Format = (ImageView) findViewById(R.id.iv24Format);
        this.iv12Format = (ImageView) findViewById(R.id.iv12Format);
        this.llMethod0 = (LinearLayout) findViewById(R.id.llMethod0);
        this.llMethod1 = (LinearLayout) findViewById(R.id.llMethod1);
        this.llMethod2 = (LinearLayout) findViewById(R.id.llMethod2);
        this.llMethod3 = (LinearLayout) findViewById(R.id.llMethod3);
        this.llMethod4 = (LinearLayout) findViewById(R.id.llMethod4);
        this.llMethod5 = (LinearLayout) findViewById(R.id.llMethod5);
        this.llMethod6 = (LinearLayout) findViewById(R.id.llMethod6);
        this.ivMethod0 = (ImageView) findViewById(R.id.ivMethod0);
        this.ivMethod1 = (ImageView) findViewById(R.id.ivMethod1);
        this.ivMethod2 = (ImageView) findViewById(R.id.ivMethod2);
        this.ivMethod3 = (ImageView) findViewById(R.id.ivMethod3);
        this.ivMethod4 = (ImageView) findViewById(R.id.ivMethod4);
        this.ivMethod5 = (ImageView) findViewById(R.id.ivMethod5);
        this.ivMethod6 = (ImageView) findViewById(R.id.ivMethod6);
        this.llAsarMethod0 = (LinearLayout) findViewById(R.id.llAsarMethod0);
        this.llAsarMethod1 = (LinearLayout) findViewById(R.id.llAsarMethod1);
        this.ivAsarMethod0 = (ImageView) findViewById(R.id.ivAsarMethod0);
        this.ivAsarMethod1 = (ImageView) findViewById(R.id.ivAsarMethod1);
        this.btnCurrentLocation = (LinearLayout) findViewById(R.id.btnCurrentLocation);
        this.btnChangeLocation = (LinearLayout) findViewById(R.id.btnChangeLocation);
        this.tvLatitude.setText(this.prayerSharedPreference.getLatitude());
        this.tvLongitude.setText(this.prayerSharedPreference.getLongitude());
        this.tvLocation.setText(this.prayerSharedPreference.getLocation());
        if (this.prayerSharedPreference.getLocation().equals("")) {
            getCurrentLocation();
        }
        setTimeFormat(this.prayerSharedPreference.getPrayerTimePref());
        setCalMethod(this.prayerSharedPreference.getCalMethodPref());
        setAsarMethod(this.prayerSharedPreference.getAsarMethodPref());
        this.ll24Format.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setTimeFormat(0);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setPrayerTimeFormatPref(0);
            }
        });
        this.ll12Format.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setTimeFormat(1);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setPrayerTimeFormatPref(1);
            }
        });
        this.btnCurrentLocation.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.getCurrentLocation();
            }
        });
        this.btnChangeLocation.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(PrayerTimeConfigureActivity.this.getApplicationContext(), ChangeLocationActivity.class);
                intent.putExtra("lat", PrayerTimeConfigureActivity.this.prayerSharedPreference.getLatitude());
                intent.putExtra("lon", PrayerTimeConfigureActivity.this.prayerSharedPreference.getLongitude());
                intent.putExtra("location", PrayerTimeConfigureActivity.this.prayerSharedPreference.getLocation());
                PrayerTimeConfigureActivity.this.startActivityForResult(intent, 10001);
            }
        });
        this.llMethod0.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(0);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(0);
            }
        });
        this.llMethod1.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(1);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(1);
            }
        });
        this.llMethod2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(2);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(2);
            }
        });
        this.llMethod3.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(3);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(3);
            }
        });
        this.llMethod4.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(4);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(4);
            }
        });
        this.llMethod5.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(5);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(5);
            }
        });
        this.llMethod6.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setCalMethod(6);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setCalMethodPref(6);
            }
        });
        this.llAsarMethod0.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setAsarMethod(0);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setAsarMethodPref(0);
            }
        });
        this.llAsarMethod1.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                PrayerTimeConfigureActivity.this.setAsarMethod(1);
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setAsarMethodPref(1);
            }
        });
    }

    
    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == -1) {
            this.tvLatitude.setText(this.prayerSharedPreference.getLatitude());
            this.tvLongitude.setText(this.prayerSharedPreference.getLongitude());
            this.tvLocation.setText(this.prayerSharedPreference.getLocation());
        }
    }

    public void setAsarMethod(int i) {
        if (i == 0) {
            this.ivAsarMethod0.setImageResource(R.drawable.ic_checked);
            this.ivAsarMethod1.setImageResource(R.drawable.ic_unchecked);
            return;
        }
        this.ivAsarMethod0.setImageResource(R.drawable.ic_unchecked);
        this.ivAsarMethod1.setImageResource(R.drawable.ic_checked);
    }

    public void setTimeFormat(int i) {
        if (i == 0) {
            this.iv24Format.setImageResource(R.drawable.ic_checked);
            this.iv12Format.setImageResource(R.drawable.ic_unchecked);
            return;
        }
        this.iv24Format.setImageResource(R.drawable.ic_unchecked);
        this.iv12Format.setImageResource(R.drawable.ic_checked);
    }

    public void setCalMethod(int i) {
        if (i == 0) {
            this.ivMethod0.setImageResource(R.drawable.ic_checked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 1) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_checked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 2) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_checked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 3) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_checked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 4) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_checked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 5) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_checked);
            this.ivMethod6.setImageResource(R.drawable.ic_unchecked);
        } else if (i == 6) {
            this.ivMethod0.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod1.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod2.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod3.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod4.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod5.setImageResource(R.drawable.ic_unchecked);
            this.ivMethod6.setImageResource(R.drawable.ic_checked);
        }
    }

    public void getCurrentLocation() {
        Dialog dialog = new Dialog(this);
        this.loadingDialog = dialog;
        dialog.requestWindowFeature(1);
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setContentView(R.layout.dialog_loading);
        this.loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.loadingDialog.show();
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000L);
        locationRequest.setPriority(100);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationServices.getFusedLocationProviderClient((Activity) this).requestLocationUpdates(locationRequest, new LocationCallback() { 
                @Override 
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LocationServices.getFusedLocationProviderClient(PrayerTimeConfigureActivity.this.getApplicationContext()).removeLocationUpdates(this);
                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                        int size = locationResult.getLocations().size() - 1;
                        PrayerTimeConfigureActivity.this.lati = locationResult.getLocations().get(size).getLatitude();
                        PrayerTimeConfigureActivity.this.longi = locationResult.getLocations().get(size).getLongitude();
                        TextView textView = PrayerTimeConfigureActivity.this.tvLatitude;
                        textView.setText(PrayerTimeConfigureActivity.this.lati + "");
                        TextView textView2 = PrayerTimeConfigureActivity.this.tvLongitude;
                        textView2.setText(PrayerTimeConfigureActivity.this.longi + "");
                        PrayerSharedPreference prayerSharedPreference = PrayerTimeConfigureActivity.this.prayerSharedPreference;
                        prayerSharedPreference.setLatitude(PrayerTimeConfigureActivity.this.lati + "");
                        PrayerSharedPreference prayerSharedPreference2 = PrayerTimeConfigureActivity.this.prayerSharedPreference;
                        prayerSharedPreference2.setLongitude(PrayerTimeConfigureActivity.this.longi + "");
                        PrayerTimeConfigureActivity.this.prayerSharedPreference.setIsPrayerLoadData(true);
                        System.out.println(String.format("Latitude : %s\n Longitude: %s", Double.valueOf(PrayerTimeConfigureActivity.this.lati), Double.valueOf(PrayerTimeConfigureActivity.this.longi)));
                        Location location = new Location("providerNA");
                        location.setLongitude(PrayerTimeConfigureActivity.this.longi);
                        location.setLatitude(PrayerTimeConfigureActivity.this.lati);
                        PrayerTimeConfigureActivity.this.fetchAddressFromLocation(location);
                        PrayerTimeConfigureActivity.this.getAddress(new LatLng(PrayerTimeConfigureActivity.this.lati, PrayerTimeConfigureActivity.this.longi));
                    } else if (PrayerTimeConfigureActivity.this.loadingDialog.isShowing()) {
                        PrayerTimeConfigureActivity.this.loadingDialog.dismiss();
                    }
                }
            }, Looper.getMainLooper());
        }
    }

    
    public void getAddress(LatLng latLng) {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (!fromLocation.isEmpty()) {
                TextView textView = this.tvLocation;
                textView.setText(fromLocation.get(0).getLocality() + ", " + fromLocation.get(0).getCountryName());
                PrayerSharedPreference prayerSharedPreference = this.prayerSharedPreference;
                prayerSharedPreference.setLocation(fromLocation.get(0).getLocality() + ", " + fromLocation.get(0).getCountryName());
                this.prayerSharedPreference.setIsPrayerLoadData(true);
                if (this.loadingDialog.isShowing()) {
                    this.loadingDialog.dismiss();
                }
            } else {
                Toast.makeText(this, "Not Address Found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    
    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override 
        protected void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            if (i == 1) {
                TextView textView = PrayerTimeConfigureActivity.this.tvLocation;
                textView.setText(bundle.getString(Constants.LOCAITY) + ", " + bundle.getString(Constants.COUNTRY));
                PrayerSharedPreference prayerSharedPreference = PrayerTimeConfigureActivity.this.prayerSharedPreference;
                prayerSharedPreference.setLocation(bundle.getString(Constants.LOCAITY) + ", " + bundle.getString(Constants.COUNTRY));
                PrayerTimeConfigureActivity.this.prayerSharedPreference.setIsPrayerLoadData(true);
                if (PrayerTimeConfigureActivity.this.loadingDialog.isShowing()) {
                    PrayerTimeConfigureActivity.this.loadingDialog.dismiss();
                    return;
                }
                return;
            }
            Toast.makeText(PrayerTimeConfigureActivity.this, bundle.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
        }
    }

    
    public void fetchAddressFromLocation(Location location) {
        Intent intent = new Intent(this, FetchAddressIntentServices.class);
        intent.putExtra(Constants.RECEVIER, this.resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
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
