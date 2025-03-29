package com.itamadersomaj.islamiclifehelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

public class AdAdmob {

    // SharedPreferences methods for Ad Counter (Optional)
    private static final String Count_Ads = "Count_Ads";

    // Constructor
    public AdAdmob(Activity activity) {
        // Constructor remains empty now as we're no longer initializing ads
    }

    // Method to save ad count in SharedPreferences
    public static void setCount_Ads(Context mContext, int count) {
        mContext.getSharedPreferences(mContext.getPackageName(), 0).edit()
                .putInt(Count_Ads, count).apply();  // Using apply instead of commit for better performance
    }

    // Method to retrieve ad count from SharedPreferences
    public static int getCount_Ads(Context mContext) {
        return mContext.getSharedPreferences(mContext.getPackageName(), 0)
                .getInt(Count_Ads, 1);  // Default count is 1
    }

    // Optional: ProgressDialog method (if you still want to show loading)
    private static ProgressDialog ProgressDialog;

    public static void showProgressDialog(Context mContext) {
        ProgressDialog = ProgressDialog.show(mContext, "", "Loading...", true);
        ProgressDialog.setCancelable(true);
    }

    // Optional: Dismiss ProgressDialog method (if you are using loading dialogs)
    public static void dismissProgressDialog() {
        if (ProgressDialog != null && ProgressDialog.isShowing()) {
            ProgressDialog.dismiss();
        }
    }

    // Optional: Method to show fullscreen ad (this will no longer work with AdMob code removed)
    public static void showFullscreenAd(Activity activity) {
        // Custom logic if needed for fullscreen ads, now it's empty because we're removing AdMob.
    }

    // Optional: Method to handle ad counter before showing a fullscreen ad (no longer needs AdMob-related logic)
    public static void handleFullscreenAdCounter(Activity activity) {
        int counter_ads = getCount_Ads(activity);

        if (counter_ads >= 3) {
            setCount_Ads(activity, 1);  // Reset count
            // Show fullscreen ad (or another ad type if you add a custom solution)
        } else {
            counter_ads = counter_ads + 1;
            setCount_Ads(activity, counter_ads);
        }
    }
}
