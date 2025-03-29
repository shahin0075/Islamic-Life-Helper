package com.itamadersomaj.islamiclifehelper.Util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.text.DecimalFormat;


public class Util {
    public static ClipData clipData;
    public static ClipboardManager clipboardManager;

    public static void copyData(Context context, String str) {
        clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData newPlainText = ClipData.newPlainText("text", str);
        clipData = newPlainText;
        clipboardManager.setPrimaryClip(newPlainText);
        Toast.makeText(context, "Copy", Toast.LENGTH_SHORT).show();
    }

    public static void shareText(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, "Share"));
    }

    public static void dismissKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getApplicationWindowToken(), 0);
        }
    }

    public static Drawable imgToDraw(Context context, String str) {
        try {
            return Drawable.createFromStream(context.getAssets().open(str), null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable imgToDrawOnDrawable(Context context, String str) {
        Resources resources = context.getResources();
        Log.e("MYTAG", "ErrorNo: imgToDrawOnDrawable:" +str);
        return resources.getDrawable(resources.getIdentifier(str, "drawable", context.getPackageName()));
    }

    public static String format(double d) {
        return new DecimalFormat("0.0").format(d);
    }
}
