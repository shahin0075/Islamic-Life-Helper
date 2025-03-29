package com.itamadersomaj.islamiclifehelper.Service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;
import com.itamadersomaj.islamiclifehelper.Util.Constants;
import java.util.List;
import java.util.Locale;


public class FetchAddressIntentServices extends IntentService {
    ResultReceiver resultReceiver;

    public FetchAddressIntentServices() {
        super("FetchAddressIntentServices");
    }

    @Override 
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            this.resultReceiver = (ResultReceiver) intent.getParcelableExtra(Constants.RECEVIER);
            Location location = (Location) intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            if (location == null) {
                return;
            }
            List<Address> list = null;
            try {
                list = new Geocoder(this, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (Exception unused) {
                Log.e("", "Error in getting address for the location");
            }
            if (list == null || list.size() == 0) {
                Toast.makeText(this, "No address found for the location", Toast.LENGTH_SHORT).show();
                return;
            }
            Address address = list.get(0);
            String postalCode = address.getPostalCode();
            String countryName = address.getCountryName();
            String adminArea = address.getAdminArea();
            String subAdminArea = address.getSubAdminArea();
            devliverResultToRecevier(1, address.getFeatureName(), address.getLocality(), subAdminArea, adminArea, countryName, postalCode);
        }
    }

    private void devliverResultToRecevier(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ADDRESS, str);
        bundle.putString(Constants.LOCAITY, str2);
        bundle.putString(Constants.DISTRICT, str3);
        bundle.putString(Constants.STATE, str4);
        bundle.putString(Constants.COUNTRY, str5);
        bundle.putString(Constants.POST_CODE, str6);
        this.resultReceiver.send(i, bundle);
    }
}
