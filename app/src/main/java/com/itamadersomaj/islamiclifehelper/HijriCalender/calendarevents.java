package com.itamadersomaj.islamiclifehelper.HijriCalender;

import static androidx.core.util.Preconditions.checkArgument;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itamadersomaj.islamiclifehelper.AdAdmob;
import com.itamadersomaj.islamiclifehelper.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.chrono.IslamicChronology;



public class calendarevents extends AppCompatActivity {
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
    Calendar c_calendar = Calendar.getInstance();
    Calendar c_calendar_end = Calendar.getInstance();
    String gregmonth_numberval = "";
    String gregday_val = "";
    String gregmonth_val = "";
    String gregyr_val = "";
    public String eventval = "";

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_calendarevents);




      //  AdAdmob adAdmob = new AdAdmob( this);
       // adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
      //  adAdmob.FullscreenAd_Counter(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.resultsListView = (ListView) findViewById(R.id.listview);
        this.title = (TextView) findViewById(R.id.maintitle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.islamicmonth_val = extras.getString("islamic_month");
            this.islamicyr_val = extras.getString("islamic_year");
            this.islamicday_val = extras.getString("islamic_day");
            this.gregmonth_numberval = extras.getString("greg_monthnumber");
            this.gregday_val = extras.getString("greg_day");
            this.gregmonth_val = extras.getString("greg_month");
            this.gregyr_val = extras.getString("greg_yr");
            this.eventval = extras.getString("m_event");
        }
        this.c_calendar.set(5, Integer.parseInt(this.gregday_val));
        this.c_calendar.set(2, Integer.parseInt(this.gregmonth_numberval));
        this.c_calendar.set(1, Integer.parseInt(this.gregyr_val));
        this.c_calendar.set(11, 0);
        this.c_calendar.set(12, 0);
        this.c_calendar.set(13, 1);
        this.c_calendar_end.set(5, Integer.parseInt(this.gregday_val));
        this.c_calendar_end.set(2, Integer.parseInt(this.gregmonth_numberval));
        this.c_calendar_end.set(1, Integer.parseInt(this.gregyr_val));
        this.c_calendar_end.set(11, 23);
        this.c_calendar_end.set(12, 59);
        this.c_calendar_end.set(13, 59);
        this.title.setText(getDayOfMonthSuffix(Integer.parseInt(this.islamicday_val)) + " " + this.islamicmonth_val + " " + this.islamicyr_val);
        String[] strArr = new String[1];
        this.title2 = strArr;
        this.subitem = new String[strArr.length];
        this.subsubitem = new String[strArr.length];
        this.subsubsubitem = new String[strArr.length];
        this.subsubsubsubitem = new String[strArr.length];

        DateTime dtISO = new DateTime(c_calendar.get(Calendar.YEAR), c_calendar.get(Calendar.MONTH)+1, c_calendar.get(Calendar.DAY_OF_MONTH), c_calendar.get(Calendar.HOUR_OF_DAY), c_calendar.get(Calendar.MINUTE)); 
        DateTime dtIslamic = dtISO.withChronology(IslamicChronology.getInstance());

        String str = "From: " + getDayOfMonthSuffix(dtIslamic.getDayOfMonth()) + " " + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(dtIslamic.getMonthOfYear())) + " " + dtIslamic.getYear() + " " + dtIslamic.getHourOfDay() + ":" + dtIslamic.getMinuteOfHour();
        if (checknumofdigits(new DateTime(this.c_calendar.get(1), this.c_calendar.get(2) + 1, this.c_calendar.get(5), this.c_calendar.get(11), this.c_calendar.get(12)).withChronology(IslamicChronology.getInstance()).getMinuteOfHour())) {
            str = "From: " + getDayOfMonthSuffix(dtIslamic.getDayOfMonth()) + " " + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(dtIslamic.getMonthOfYear())) + " " + dtIslamic.getYear() + " " + dtIslamic.getHourOfDay() + ":0" + dtIslamic.getMinuteOfHour();
        }


        dtISO = new DateTime(c_calendar_end.get(Calendar.YEAR), c_calendar_end.get(Calendar.MONTH)+1, c_calendar_end.get(Calendar.DAY_OF_MONTH), c_calendar_end.get(Calendar.HOUR_OF_DAY), c_calendar_end.get(Calendar.MINUTE));
        dtIslamic = dtISO.withChronology(IslamicChronology.getInstance());

        String str2 = "To: " + getDayOfMonthSuffix(dtIslamic.getDayOfMonth()) + " " + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(dtIslamic.getMonthOfYear())) + " " + dtIslamic.getYear() + " " + dtIslamic.getHourOfDay() + ":" + dtIslamic.getMinuteOfHour();
        if (checknumofdigits(new DateTime(this.c_calendar_end.get(1), this.c_calendar_end.get(2) + 1, this.c_calendar_end.get(5), this.c_calendar_end.get(11), this.c_calendar_end.get(12)).withChronology(IslamicChronology.getInstance()).getMinuteOfHour())) {
            str2 = "To: " + getDayOfMonthSuffix(dtIslamic.getDayOfMonth()) + " " + HijriCalenderActivity.all_hijri_months.get(Integer.valueOf(dtIslamic.getMonthOfYear())) + " " + dtIslamic.getYear() + " " + dtIslamic.getHourOfDay() + ":0" + dtIslamic.getMinuteOfHour();
        }
        this.title2[0] = this.eventval;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM d, yyyy hh:mm a");
            Date parse = simpleDateFormat.parse(this.c_calendar.getTime().toString());
            this.subitem[0] = "From: " + simpleDateFormat2.format(parse);
            Date parse2 = simpleDateFormat.parse(this.c_calendar_end.getTime().toString());
            this.subsubitem[0] = "To: " + simpleDateFormat2.format(parse2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.subsubsubitem[0] = str;
        this.subsubsubsubitem[0] = str2;
        this.resultsListView.setAdapter((ListAdapter) new CustomAdapter3(this, this.title2, this.subitem, this.subsubitem, this.subsubsubitem, this.subsubsubsubitem, this));
    }

    @Override 
    public void onBackPressed() {
        finish();
    }


    @SuppressLint("RestrictedApi")
    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return n+"th";
        }
        switch (n % 10) {
            case 1:  return n+"st";
            case 2:  return n+"nd";
            case 3:  return n+"rd";
            default: return n+"th";
        }
    }

    private static boolean checknumofdigits(int i) {
        return String.valueOf(i).length() == 1;
    }

    @Override 
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}