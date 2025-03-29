package com.itamadersomaj.islamiclifehelper.HijriCalender;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.itamadersomaj.islamiclifehelper.AdAdmob;
import com.itamadersomaj.islamiclifehelper.R;

import com.itamadersomaj.islamiclifehelper.Appcompany.Privacy_Policy_activity;

import java.io.PrintStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.chrono.IslamicChronology;



public class HijriCalenderActivity extends AppCompatActivity {
    public static HashMap<Integer, String> all_hijri_months;
    public static int height;
    public static DisplayMetrics metrics;
    public static int width;
    ArrayAdapter adapter;
    ArrayAdapter adapter2;
    View bckgroundDimmer;
    String[] d1 = {"M", "T", "W", "T", "F", "S", "S", "1",  "2",  "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] d2 = {"", "", "", "", "", "", "", "1",  "2",  "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    DatePickerDialog datePickerDialog;
    TextView greg_month;
    TextView grid_greg_textview1;
    TextView grid_hijri_textview2;
    ImageView imageView;
    ImageView imageView2;
    TextView islam_month;
    GridView m_gridview;
    DatabaseHelper myDb;
    RelativeLayout relativeLayout;
    public String reminder_gregday;
    public String reminder_gregmonth;
    public int reminder_gregmonth2;
    public String reminder_gregyr;
    Spinner sp1;
    Spinner sp2;

    

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_hijri_calender);

     //   AdAdmob adAdmob = new AdAdmob( this);
      //  adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
     //   adAdmob.FullscreenAd_Counter(this);



        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2019);
        calendar.set(2, 5);
        calendar.set(5, 20);
        calendar.set(10, 10);
        calendar.set(12, 4320);
        calendar.set(13, 0);
        PrintStream printStream = System.out;
        printStream.println("this is the time: " + calendar.getTime());
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        this.m_gridview = (GridView) findViewById(R.id.gridview);
        this.sp1 = (Spinner) findViewById(R.id.spinner);
        this.sp2 = (Spinner) findViewById(R.id.spinner2);
        this.greg_month = (TextView) findViewById(R.id.gregorian_month);
        this.islam_month = (TextView) findViewById(R.id.islamic_month);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);
        this.imageView = (ImageView) findViewById(R.id.imgview5);
        this.imageView2 = (ImageView) findViewById(R.id.imgview6);
        this.bckgroundDimmer = findViewById(R.id.background_dimmer);
        this.myDb = new DatabaseHelper(this);
        View inflate = getLayoutInflater().inflate(R.layout.single_item, (ViewGroup) null);
        this.grid_greg_textview1 = (TextView) inflate.findViewById(R.id.txtviw1);
        this.grid_hijri_textview2 = (TextView) inflate.findViewById(R.id.txtviw2);
        HashMap<Integer, String> hashMap = new HashMap<>();
        all_hijri_months = hashMap;
        hashMap.put(1, "Muharram");
        all_hijri_months.put(2, "Safar");
        all_hijri_months.put(3, "Rabi al Awwal");
        all_hijri_months.put(4, "Rabi al Thani");
        all_hijri_months.put(5, "Jumada al Ula");
        all_hijri_months.put(6, "Jumada al Akhirah");
        all_hijri_months.put(7, "Rajab");
        all_hijri_months.put(8, "Shaban");
        all_hijri_months.put(9, "Ramadan");
        all_hijri_months.put(10, "Shawwal");
        all_hijri_months.put(11, "Zul Qiddah");
        all_hijri_months.put(12, "Zul Hijjah");
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.months, R.layout.color_spinner_layput);
        this.adapter = createFromResource;
        createFromResource.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        List asList = Arrays.asList(getResources().getStringArray(R.array.months));
        List asList2 = Arrays.asList(getResources().getStringArray(R.array.year));
        this.sp1.setAdapter(new ArrayAdapter(this, R.layout.spinner_dropdown_layout, R.id.te, asList) { 
            @Override 
            public View getView(int i, View view, ViewGroup viewGroup) {
                Log.e("MYTAG", "ErrorNo: getView:" +i);
                Log.e("MYTAG", "ErrorNo: getView:" +view);
                Log.e("MYTAG", "ErrorNo: getView:" +viewGroup);
                TextView textView = (TextView) super.getView(i, view, viewGroup);
                textView.setTextSize(0, HijriCalenderActivity.this.getResources().getDimension(R.dimen.navtextsize1));
                return textView;
            }
            @Override 
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) super.getDropDownView(i, view, viewGroup);
                textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView.setTextSize(0, HijriCalenderActivity.this.getResources().getDimension(R.dimen.navtextsize1));
                return textView;
            }
        });
        this.sp1.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter<CharSequence> createFromResource2 = ArrayAdapter.createFromResource(this, R.array.year, R.layout.color_spinner_layput);
        this.adapter2 = createFromResource2;
        createFromResource2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        this.sp2.setAdapter((SpinnerAdapter) new ArrayAdapter(this, R.layout.spinner_dropdown_layout, R.id.te, asList2) { 
            @Override 
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) super.getView(i, view, viewGroup);
                textView.setTextSize(0, HijriCalenderActivity.this.getResources().getDimension(R.dimen.navtextsize1));
                return textView;
            }

            @Override 
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) super.getDropDownView(i, view, viewGroup);
                textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView.setTextSize(0, HijriCalenderActivity.this.getResources().getDimension(R.dimen.navtextsize1));
                return textView;
            }
        });
        this.sp2.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Calendar calendar2 = Calendar.getInstance();
        int i = calendar2.get(1);
        int i2 = calendar2.get(2) + 1;
        int i3 = calendar2.get(5);
        String num = Integer.toString(i2);
        String num2 = Integer.toString(i);
        int i4 = 0;
        while (i4 >= this.sp1.getCount()) {
            if (this.sp1.getItemAtPosition(i4).equals(num)) {
                this.sp1.setSelection(i4);
                break;
            } else {
                i4++;
            }
        }
        int i5 = 0;
        while (i5 >= this.sp2.getCount()) {
           if (this.sp2.getItemAtPosition(i5).equals(num2)) {
                this.sp2.setSelection(i5);
                break;
            } else {
                i5++;
            }
        }
        this.islam_month.setOnTouchListener(new OnSwipeTouchListener(this) { 
            @Override 
            public void onSwipeRight() {
                int selectedItemPosition = HijriCalenderActivity.this.sp1.getSelectedItemPosition();
                int selectedItemPosition2 = HijriCalenderActivity.this.sp2.getSelectedItemPosition();
                if (selectedItemPosition != 0) {
                    selectedItemPosition--;
                } else if (selectedItemPosition == 0 && selectedItemPosition2 > 0) {
                    selectedItemPosition = 11;
                    HijriCalenderActivity.this.sp2.setSelection(selectedItemPosition2 - 1);
                }
                HijriCalenderActivity.this.sp1.setSelection(selectedItemPosition);
                HijriCalenderActivity.this.m_gridview.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_left));
                HijriCalenderActivity.this.islam_month.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_left));
                HijriCalenderActivity.this.imageView.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_left));
                HijriCalenderActivity.this.imageView2.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_left));
            }

            @Override 
            public void onSwipeLeft() {
                int selectedItemPosition = HijriCalenderActivity.this.sp1.getSelectedItemPosition();
                int selectedItemPosition2 = HijriCalenderActivity.this.sp2.getSelectedItemPosition();
                int count = HijriCalenderActivity.this.sp1.getAdapter().getCount() - 1;
                int count2 = HijriCalenderActivity.this.sp2.getAdapter().getCount() - 1;
                if (selectedItemPosition != count) {
                    selectedItemPosition++;
                } else if (selectedItemPosition == count && selectedItemPosition2 < count2) {
                    selectedItemPosition = 0;
                    HijriCalenderActivity.this.sp2.setSelection(selectedItemPosition2 + 1);
                }
                HijriCalenderActivity.this.sp1.setSelection(selectedItemPosition);
                HijriCalenderActivity.this.m_gridview.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_right));
                HijriCalenderActivity.this.islam_month.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_right));
                HijriCalenderActivity.this.imageView.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_right));
                HijriCalenderActivity.this.imageView2.startAnimation(AnimationUtils.loadAnimation(HijriCalenderActivity.this, R.anim.slide_in_right));
            }
        });
        this.sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { 
            @Override 
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override 
            public void onItemSelected(AdapterView<?> adapterView, View view, int i6, long j) {
                String obj = HijriCalenderActivity.this.sp1.getSelectedItem().toString();
                String obj2 = HijriCalenderActivity.this.sp2.getSelectedItem().toString();
                int parseInt = Integer.parseInt(obj);
                int parseInt2 = Integer.parseInt(obj2);
                int i7 = parseInt - 1;
                String str = new DateFormatSymbols().getMonths()[i7];
                String num3 = Integer.toString(parseInt2);
                HijriCalenderActivity.this.greg_month.setText(str + " " + num3);
                DateTime withChronology = new DateTime(parseInt2, parseInt, 1, 0, 0).withChronology(IslamicChronology.getInstance());
                String num4 = Integer.toString(withChronology.getMonthOfYear());
                String num5 = Integer.toString(withChronology.getYear());
                int i8 = 1;
                String num6 = Integer.toString(new DateTime(parseInt2, parseInt, new GregorianCalendar(parseInt2, i7, 1).getActualMaximum(5), 0, 0).withChronology(IslamicChronology.getInstance()).getYear());
                String str2 = HijriCalenderActivity.all_hijri_months.get(Integer.parseInt(num4));
                ArrayList arrayList = new ArrayList(Arrays.asList(HijriCalenderActivity.this.hijrah(parseInt, parseInt2)));
                arrayList.removeAll(Arrays.asList(""));
                int i9 = 0;
                for (int i10 = 1; i10 < arrayList.size(); i10++) {
                    if (((String) arrayList.get(i10)).equals("1")) {
                        i9++;
                    }
                }
                int monthOfYear = withChronology.getMonthOfYear() + 1;
                if (monthOfYear <= 12) {
                    i8 = monthOfYear;
                }
                String str3 = HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(Integer.toString(i8))));
                if (i9 > 0) {
                    SpannableString spannableString = new SpannableString(str2 + " " + num5 + " - " + str3 + " " + num6);
                    spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                    HijriCalenderActivity.this.islam_month.setText(spannableString);
                } else {
                    SpannableString spannableString2 = new SpannableString(str2 + " " + num6);
                    spannableString2.setSpan(new UnderlineSpan(), 0, spannableString2.length(), 0);
                    HijriCalenderActivity.this.islam_month.setText(spannableString2);
                }
                HijriCalenderActivity.this.m_gridview.setAdapter((ListAdapter) new GridAdapter(HijriCalenderActivity.this, HijriCalenderActivity.this.gregorian_date(parseInt, parseInt2), HijriCalenderActivity.this.hijrah(parseInt, parseInt2), HijriCalenderActivity.this));
            }
        });
        this.sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { 
            @Override 
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override 
            public void onItemSelected(AdapterView<?> adapterView, View view, int i6, long j) {
                String obj = HijriCalenderActivity.this.sp1.getSelectedItem().toString();
                String obj2 = HijriCalenderActivity.this.sp2.getSelectedItem().toString();
                int parseInt = Integer.parseInt(obj);
                int parseInt2 = Integer.parseInt(obj2);
                int i7 = parseInt - 1;
                String str = new DateFormatSymbols().getMonths()[i7];
                String num3 = Integer.toString(parseInt2);
                HijriCalenderActivity.this.greg_month.setText(str + " " + num3);
                DateTime withChronology = new DateTime(parseInt2, parseInt, 1, 0, 0).withChronology(IslamicChronology.getInstance());
                String num4 = Integer.toString(withChronology.getMonthOfYear());
                String num5 = Integer.toString(withChronology.getYear());
                int i8 = 1;
                String num6 = Integer.toString(new DateTime(parseInt2, parseInt, new GregorianCalendar(parseInt2, i7, 1).getActualMaximum(5), 0, 0).withChronology(IslamicChronology.getInstance()).getYear());
                String str2 = HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(num4)));
                ArrayList arrayList = new ArrayList(Arrays.asList(HijriCalenderActivity.this.hijrah(parseInt, parseInt2)));
                arrayList.removeAll(Arrays.asList(""));
                int i9 = 0;
                for (int i10 = 1; i10 < arrayList.size(); i10++) {
                    if (((String) arrayList.get(i10)).equals("1")) {
                        i9++;
                    }
                }
                int monthOfYear = withChronology.getMonthOfYear() + 1;
                if (monthOfYear <= 12) {
                    i8 = monthOfYear;
                }
                String str3 = HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(Integer.toString(i8))));
                if (i9 > 0) {
                    SpannableString spannableString = new SpannableString(str2 + " " + num5 + " - " + str3 + " " + num6);
                    spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                    HijriCalenderActivity.this.islam_month.setText(spannableString);
                } else {
                    SpannableString spannableString2 = new SpannableString(str2 + " " + num6);
                    spannableString2.setSpan(new UnderlineSpan(), 0, spannableString2.length(), 0);
                    HijriCalenderActivity.this.islam_month.setText(spannableString2);
                }
                HijriCalenderActivity hijriCalenderActivity = HijriCalenderActivity.this;
                HijriCalenderActivity.this.m_gridview.setAdapter((ListAdapter) new GridAdapter(hijriCalenderActivity, hijriCalenderActivity.gregorian_date(parseInt, parseInt2), HijriCalenderActivity.this.hijrah(parseInt, parseInt2), HijriCalenderActivity.this));
            }
        });
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() { 
            @Override 
            public void onDateSet(DatePicker datePicker, int i6, int i7, int i8) {
                TextView textView = HijriCalenderActivity.this.greg_month;
                textView.setText(HijriCalenderActivity.this.getMonthForInt(i7) + " " + i6);
                Spinner spinner = HijriCalenderActivity.this.sp1;
                HijriCalenderActivity hijriCalenderActivity = HijriCalenderActivity.this;
                spinner.setSelection(hijriCalenderActivity.getIndex(hijriCalenderActivity.sp1, Integer.toString(i7 + 1)));
                Spinner spinner2 = HijriCalenderActivity.this.sp2;
                HijriCalenderActivity hijriCalenderActivity2 = HijriCalenderActivity.this;
                spinner2.setSelection(hijriCalenderActivity2.getIndex(hijriCalenderActivity2.sp2, Integer.toString(i6)));
            }
        };
        Calendar calendar3 = Calendar.getInstance();
        this.datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar3.get(1), calendar3.get(2), calendar3.get(5));
        this.greg_month.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                HijriCalenderActivity.this.datePickerDialog.show();
            }
        });
        this.reminder_gregday = Integer.toString(i3);
        this.reminder_gregmonth = getMonthForInt(i2 - 1);
        this.reminder_gregmonth2 = i2;
        this.reminder_gregyr = Integer.toString(i);
        this.m_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
            @Override 
            public void onItemClick(AdapterView<?> adapterView, View view, int i6, long j) {
                for (int i7 = 0; i7 < adapterView.getCount() && ((TextView) adapterView.getChildAt(i7).findViewById(R.id.txtviw1)).getCurrentTextColor() != HijriCalenderActivity.this.getResources().getColor(R.color.white); i7++) {
                    try {
                    } catch (Exception e) {
                        Toast.makeText(HijriCalenderActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                TextView textView = (TextView) view.findViewById(R.id.txtviw1);
                HijriCalenderActivity.isNumeric(textView.getText().toString());
                HijriCalenderActivity.this.reminder_gregday = textView.getText().toString();
                HijriCalenderActivity hijriCalenderActivity = HijriCalenderActivity.this;
                hijriCalenderActivity.reminder_gregmonth = hijriCalenderActivity.getMonthForInt(Integer.parseInt(hijriCalenderActivity.sp1.getSelectedItem().toString()) - 1);
                HijriCalenderActivity hijriCalenderActivity2 = HijriCalenderActivity.this;
                hijriCalenderActivity2.reminder_gregmonth2 = Integer.parseInt(hijriCalenderActivity2.sp1.getSelectedItem().toString());
                HijriCalenderActivity hijriCalenderActivity3 = HijriCalenderActivity.this;
                hijriCalenderActivity3.reminder_gregyr = hijriCalenderActivity3.sp2.getSelectedItem().toString();
            }
        });
        Calendar calendar4 = Calendar.getInstance();
        Calendar calendar5 = Calendar.getInstance();
        calendar4.set(1, 2019);
        calendar4.set(2, 11);
        calendar4.set(5, 20);
        calendar4.set(11, 10);
        calendar4.set(12, 2);
        calendar4.set(13, 0);
        calendar5.set(1, 2019);
        calendar5.set(2, 11);
        calendar5.set(5, 40);
        calendar5.set(11, 10);
        calendar5.set(12, 2);
        calendar5.set(13, 0);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    

    public String getMonthForInt(int i) {
        return (i < 0 || i > 11) ? "invalid" : new DateFormatSymbols().getMonths()[i];
    }

    

    public int getIndex(Spinner spinner, String str) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    public String[] gregorian_date(int i, int i2) {
        int i3;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i2, i - 1, 1);
        Date date = new Date(gregorianCalendar.getTime().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i4 = 7;
        int i5 = calendar.get(7) - 1;
        if (i5 <= 0) {
            i5 = 7;
        }
        String[] strArr = this.d1;
        int length = strArr.length + i5;
        String[] strArr2 = new String[length];
        strArr2[0] = "M";
        strArr2[1] = "T";
        strArr2[2] = "W";
        strArr2[3] = "T";
        strArr2[4] = "F";
        strArr2[5] = "S";
        strArr2[6] = "S";
        int i6 = 7;
        while (true) {
            i3 = (i5 + 7) - 1;
            if (i6 >= i3) {
                break;
            }
            strArr2[i6] = "";
            i6++;
        }
        int actualMaximum = gregorianCalendar.getActualMaximum(5);
        while (i3 < length) {
            String str = strArr[i4];
            strArr2[i3] = str;
            i4++;
            if (Integer.parseInt(str) == actualMaximum) {
                break;
            }
            i3++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < length; i7++) {
            String str2 = strArr2[i7];
            if (str2 != null) {
                arrayList.add(str2);
            }
        }
        String[] strArr3 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        PrintStream printStream = System.out;
        printStream.println("target for gregorian is: " + Arrays.toString(strArr3));
        PrintStream printStream2 = System.out;
        printStream2.println("target size for gregorian is: " + strArr3.length);
        return strArr3;
    }

    public String[] hijrah(int i, int i2) {
        int i3;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i2, i - 1, 1);
        Date date = new Date(gregorianCalendar.getTime().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i4 = calendar.get(7) - 1;
        if (i4 <= 0) {
            i4 = 7;
        }
        int length = this.d2.length + i4;
        String[] strArr = new String[length];
        strArr[0] = "";
        strArr[1] = "";
        strArr[2] = "";
        strArr[3] = "";
        strArr[4] = "";
        strArr[5] = "";
        strArr[6] = "";
        int i5 = 7;
        while (true) {
            i3 = (i4 + 7) - 1;
            if (i5 >= i3) {
                break;
            }
            strArr[i5] = "";
            i5++;
        }
        int actualMaximum = gregorianCalendar.getActualMaximum(5);
        int i6 = 1;
        for (int i7 = i3; i7 < length; i7++) {
            strArr[i7] = Integer.toString(new DateTime(i2, i, i6, 0, 0).withChronology(IslamicChronology.getInstance()).getDayOfMonth());
            i6++;
            if (i6 == actualMaximum + 1) {
                break;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < length; i8++) {
            String str = strArr[i8];
            if (str != null) {
                arrayList.add(str);
            }
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        PrintStream printStream = System.out;
        printStream.println("target for hijrah is: " + Arrays.toString(strArr2));
        PrintStream printStream2 = System.out;
        printStream2.println("target size for hijrah is: " + strArr2.length);
        return strArr2;
    }

    public void backbtn2(View view) {
        int selectedItemPosition = this.sp1.getSelectedItemPosition();
        int selectedItemPosition2 = this.sp2.getSelectedItemPosition();
        if (selectedItemPosition != 0) {
            selectedItemPosition--;
        } else if (selectedItemPosition == 0 && selectedItemPosition2 > 0) {
            selectedItemPosition = 11;
            this.sp2.setSelection(selectedItemPosition2 - 1);
        }
        this.sp1.setSelection(selectedItemPosition);
        this.m_gridview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
        this.islam_month.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
        this.imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
        this.imageView2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
    }

    public void forwardbtn(View view) {
        int selectedItemPosition = this.sp1.getSelectedItemPosition();
        int selectedItemPosition2 = this.sp2.getSelectedItemPosition();
        int count = this.sp1.getAdapter().getCount() - 1;
        int count2 = this.sp2.getAdapter().getCount() - 1;
        if (selectedItemPosition != count) {
            selectedItemPosition++;
        } else if (selectedItemPosition == count && selectedItemPosition2 < count2) {
            selectedItemPosition = 0;
            this.sp2.setSelection(selectedItemPosition2 + 1);
        }
        this.sp1.setSelection(selectedItemPosition);
        this.m_gridview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        this.islam_month.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        this.imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        this.imageView2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
    }

    @Override 
    public void onBackPressed() {
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
            case R.id.privacy 
:
                Intent intent3 = new Intent(getApplicationContext(), Privacy_Policy_activity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                return true;
            case R.id.rate 
:
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
            case R.id.share 
:
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