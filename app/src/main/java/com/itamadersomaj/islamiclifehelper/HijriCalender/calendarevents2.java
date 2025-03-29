package com.itamadersomaj.islamiclifehelper.HijriCalender;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Preconditions;

import com.itamadersomaj.islamiclifehelper.AdAdmob;
import com.itamadersomaj.islamiclifehelper.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.chrono.IslamicChronology;


public class calendarevents2 extends AppCompatActivity {
    RelativeLayout layout;
    DatabaseHelper myDb;
    ListView resultsListView;
    private String[] subitem;
    private String[] subsubitem;
    private String[] subsubsubitem;
    private String[] subsubsubsubitem;
    TextView title;
    private String[] title2;
    String islamicmonth_val = "";
    String islamicyr_val = "";
    String islamicday_val = "";
    String gregday_val = "";
    String gregmonth_val = "";
    String gregmonth_numberval = "";
    String gregyr_val = "";
    public String eventval = "";
    Calendar c_calendar = Calendar.getInstance();
    int checkcounter = 0;
    Calendar start_calendar = Calendar.getInstance();
    String db_event = "";
    Calendar end_calendar = Calendar.getInstance();
    Calendar startdate = Calendar.getInstance();
    Calendar enddate = Calendar.getInstance();

    
    @Override 
    public void onCreate(Bundle bundle) {
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        super.onCreate(bundle);
        setContentView(R.layout.activity_calendarevents2);



//        AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
       // adAdmob.FullscreenAd_Counter(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.resultsListView = (ListView) findViewById(R.id.listview0002);
        this.title = (TextView) findViewById(R.id.maintitle005);
        this.myDb = new DatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.islamicmonth_val = extras.getString("islamic_month");
            this.islamicyr_val = extras.getString("islamic_year");
            this.islamicday_val = extras.getString("islamic_day");
            this.gregday_val = extras.getString("greg_day");
            this.gregmonth_val = extras.getString("greg_month");
            this.gregmonth_numberval = extras.getString("greg_monthnumber");
            this.gregyr_val = extras.getString("greg_yr");
            this.eventval = extras.getString("m_event");
        }
        this.c_calendar.set(5, Integer.parseInt(this.gregday_val));
        this.c_calendar.set(2, Integer.parseInt(this.gregmonth_numberval));
        this.c_calendar.set(1, Integer.parseInt(this.gregyr_val));
        Calendar calendar = this.c_calendar;
        calendar.set(11, calendar.get(11));
        Calendar calendar2 = this.c_calendar;
        calendar2.set(12, calendar2.get(12) + 10);
        this.title.setText(getDayOfMonthSuffix(Integer.parseInt(this.islamicday_val)) + " " + this.islamicmonth_val + " " + this.islamicyr_val);
        Cursor searchReminder = this.myDb.searchReminder(Integer.toString(this.c_calendar.get(5)), Integer.toString(this.c_calendar.get(2)), Integer.toString(this.c_calendar.get(1)));
        Cursor searchStartDate = this.myDb.searchStartDate(Integer.toString(this.c_calendar.get(5)), Integer.toString(this.c_calendar.get(2)), Integer.toString(this.c_calendar.get(1)));
        Cursor searchEndDate = this.myDb.searchEndDate(Integer.toString(this.c_calendar.get(5)), Integer.toString(this.c_calendar.get(2)), Integer.toString(this.c_calendar.get(1)));
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        ArrayList arrayList12 = new ArrayList();
        ArrayList arrayList13 = new ArrayList();
        ArrayList arrayList14 = new ArrayList();
        if (searchReminder.moveToFirst()) {
            str = " ";
            do {
                arrayList4.add(searchReminder.getString(searchReminder.getColumnIndex("EVENT")));
            } while (searchReminder.moveToNext());
        } else {
            str = " ";
        }
        searchReminder.close();
        if (searchStartDate.moveToFirst()) {
            while (true) {
                String string = searchStartDate.getString(searchStartDate.getColumnIndex("DAY"));
                String string2 = searchStartDate.getString(searchStartDate.getColumnIndex("MONTH"));
                String string3 = searchStartDate.getString(searchStartDate.getColumnIndex("YEAR"));
                arrayList2 = arrayList4;
                String string4 = searchStartDate.getString(searchStartDate.getColumnIndex("HOUR"));
                arrayList = arrayList14;
                String string5 = searchStartDate.getString(searchStartDate.getColumnIndex("MINUTE"));
                arrayList5.add(string);
                arrayList6.add(string2);
                arrayList7.add(string3);
                arrayList8.add(string4);
                arrayList9.add(string5);
                if (!searchStartDate.moveToNext()) {
                    break;
                }
                arrayList4 = arrayList2;
                arrayList14 = arrayList;
            }
        } else {
            arrayList = arrayList14;
            arrayList2 = arrayList4;
        }
        searchStartDate.close();
        if (searchEndDate.moveToFirst()) {
            while (true) {
                String string6 = searchEndDate.getString(searchEndDate.getColumnIndex("ENDDAY"));
                String string7 = searchEndDate.getString(searchEndDate.getColumnIndex("ENDMONTH"));
                String string8 = searchEndDate.getString(searchEndDate.getColumnIndex("ENDYEAR"));
                String string9 = searchEndDate.getString(searchEndDate.getColumnIndex("ENDHOUR"));
                String string10 = searchEndDate.getString(searchEndDate.getColumnIndex("ENDMINUTE"));
                arrayList10.add(string6);
                arrayList11.add(string7);
                arrayList12.add(string8);
                arrayList13.add(string9);
                arrayList3 = arrayList;
                arrayList3.add(string10);
                if (!searchEndDate.moveToNext()) {
                    break;
                }
                arrayList = arrayList3;
            }
        } else {
            arrayList3 = arrayList;
        }
        searchEndDate.close();
        String[] strArr = new String[arrayList2.size()];
        this.title2 = strArr;
        this.subitem = new String[strArr.length];
        this.subsubitem = new String[strArr.length];
        this.subsubsubitem = new String[strArr.length];
        this.subsubsubsubitem = new String[strArr.length];
        int i = 0;
        this.startdate.set(13, 0);
        this.enddate.set(13, 0);
        int i2 = 0;
        while (i < arrayList2.size()) {
            this.startdate.set(5, Integer.parseInt((String) arrayList5.get(i)));
            this.startdate.set(2, Integer.parseInt((String) arrayList6.get(i)));
            this.startdate.set(1, Integer.parseInt((String) arrayList7.get(i)));
            this.startdate.set(11, Integer.parseInt((String) arrayList8.get(i)));
            this.startdate.set(12, Integer.parseInt((String) arrayList9.get(i)));
            this.enddate.set(5, Integer.parseInt((String) arrayList10.get(i)));
            this.enddate.set(2, Integer.parseInt((String) arrayList11.get(i)));
            this.enddate.set(1, Integer.parseInt((String) arrayList12.get(i)));
            this.enddate.set(11, Integer.parseInt((String) arrayList13.get(i)));
            this.enddate.set(12, Integer.parseInt((String) arrayList3.get(i)));
            ArrayList arrayList15 = arrayList2;
            this.title2[i] = (String) arrayList15.get(i2);
            int i3 = i2 + 1;
            ArrayList arrayList16 = arrayList3;
            ArrayList arrayList17 = arrayList11;
            DateTime withChronology = new DateTime(this.startdate.get(1), this.startdate.get(2) + 1, this.startdate.get(5), this.startdate.get(11), this.startdate.get(12)).withChronology(IslamicChronology.getInstance());
            ArrayList arrayList18 = arrayList10;
            DateTime withChronology2 = new DateTime(this.enddate.get(1), this.enddate.get(2) + 1, this.enddate.get(5), this.enddate.get(11), this.enddate.get(12)).withChronology(IslamicChronology.getInstance());
            String num = Integer.toString(withChronology.getDayOfMonth());
            String num2 = Integer.toString(withChronology.getMonthOfYear());
            String num3 = Integer.toString(withChronology.getYear());
            String num4 = Integer.toString(withChronology.getHourOfDay());
            String num5 = Integer.toString(withChronology.getMinuteOfHour());
            String num6 = Integer.toString(withChronology2.getDayOfMonth());
            String num7 = Integer.toString(withChronology2.getMonthOfYear());
            ArrayList arrayList19 = arrayList13;
            String num8 = Integer.toString(withChronology2.getYear());
            ArrayList arrayList20 = arrayList12;
            String num9 = Integer.toString(withChronology2.getHourOfDay());
            String num10 = Integer.toString(withChronology2.getMinuteOfHour());
            ArrayList arrayList21 = arrayList9;
            StringBuilder sb = new StringBuilder();
            sb.append("From: ");
            sb.append(getDayOfMonthSuffix(Integer.parseInt(num)));
            String str2 = str;
            sb.append(str2);
            ArrayList arrayList22 = arrayList5;
            ArrayList arrayList23 = arrayList6;
            sb.append(HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(num2))));
            sb.append(str2);
            sb.append(num3);
            sb.append(str2);
            sb.append(num4);
            sb.append(":");
            sb.append(num5);
            String sb2 = sb.toString();
            ArrayList arrayList24 = arrayList7;
            if (checknumofdigits(Integer.parseInt(num5))) {
                sb2 = "From: " + getDayOfMonthSuffix(Integer.parseInt(num)) + str2 + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(num2))) + str2 + num3 + str2 + num4 + ":0" + num5;
            }
            String str3 = "To: " + getDayOfMonthSuffix(Integer.parseInt(num6)) + str2 + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(num7))) + str2 + num8 + str2 + num9 + ":" + num10;
            if (checknumofdigits(Integer.parseInt(num10))) {
                str3 = "To: " + getDayOfMonthSuffix(Integer.parseInt(num6)) + str2 + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(Integer.parseInt(num7))) + str2 + num8 + str2 + num9 + ":0" + num10;
            }
            String str4 = str3;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            try {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM d, yyyy hh:mm a");
                Date parse = simpleDateFormat.parse(this.startdate.getTime().toString());
                this.subitem[i] = "From: " + simpleDateFormat2.format(parse);
                Date parse2 = simpleDateFormat.parse(this.enddate.getTime().toString());
                this.subsubitem[i] = "To: " + simpleDateFormat2.format(parse2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.subsubsubitem[i] = sb2;
            this.subsubsubsubitem[i] = str4;
            i++;
            arrayList3 = arrayList16;
            arrayList5 = arrayList22;
            arrayList9 = arrayList21;
            arrayList13 = arrayList19;
            arrayList12 = arrayList20;
            arrayList11 = arrayList17;
            arrayList10 = arrayList18;
            arrayList2 = arrayList15;
            i2 = i3;
            arrayList6 = arrayList23;
            arrayList7 = arrayList24;
            str = str2;
        }
        final CustomAdapter3 customAdapter3 = new CustomAdapter3(this, this.title2, this.subitem, this.subsubitem, this.subsubsubitem, this.subsubsubsubitem, this);
        this.resultsListView.setAdapter((ListAdapter) customAdapter3);
        this.resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
            @Override 
            public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j) {
                calendarevents2.this.checkcounter = 1;
                String replace = customAdapter3.getItem3(i4).replace("End date: ", "");
                String replace2 = customAdapter3.getItem2(i4).replace("Start date: ", "");
                calendarevents2.this.db_event = customAdapter3.getItem1(i4);
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("EEE MMM dd HH:mm  yyyy", Locale.getDefault());
                SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("EEE MMM dd HH:mm  yyyy", Locale.getDefault());
                try {
                    simpleDateFormat3.parse(replace);
                    simpleDateFormat4.parse(replace2);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                calendarevents2.this.end_calendar = simpleDateFormat3.getCalendar();
                calendarevents2.this.start_calendar = simpleDateFormat4.getCalendar();
            }
        });
    }

    public static int daysBetween(Calendar calendar, Calendar calendar2) {
        Calendar calendar3 = (Calendar) calendar.clone();
        Calendar calendar4 = (Calendar) calendar2.clone();
        if (calendar3.get(1) == calendar4.get(1)) {
            return Math.abs(calendar3.get(6) - calendar4.get(6));
        }
        if (calendar4.get(1) > calendar3.get(1)) {
            calendar4 = calendar3;
            calendar3 = calendar4;
        }
        int i = 0;
        int i2 = calendar3.get(6);
        while (calendar3.get(1) > calendar4.get(1)) {
            calendar3.add(1, -1);
            i += calendar3.getActualMaximum(6);
        }
        return (i - calendar4.get(6)) + i2;
    }

    
    @Override 
    public void onStart() {
        super.onStart();
        this.resultsListView.setChoiceMode(1);
    }

    private int DeleteCalendarEntry(int i) {
        return getContentResolver().delete(ContentUris.withAppendedId(getCalendarUriBase(), i), null, null);
    }

    private Uri getCalendarUriBase() {
        if (Build.VERSION.SDK_INT <= 7) {
            return Uri.parse("content://calendar/events");
        }
        return Uri.parse("content://com.android.calendar/events");
    }

    String getDayOfMonthSuffix(int i) {
        boolean z = i >= 1 && i <= 31;
        Preconditions.checkArgument(z, "illegal day of month: " + i);
        if (i >= 11 && i <= 13) {
            return i + "th";
        }
        int i2 = i % 10;
        if (i2 == 1) {
            return i + "st";
        } else if (i2 == 2) {
            return i + "nd";
        } else if (i2 == 3) {
            return i + "rd";
        } else {
            return i + "th";
        }
    }

    private static boolean checknumofdigits(int i) {
        return String.valueOf(i).length() == 1;
    }

    @Override 
    public void onBackPressed() {
        finish();
    }

    @Override 
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
