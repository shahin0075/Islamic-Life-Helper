package com.itamadersomaj.islamiclifehelper.HijriCalender;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.itamadersomaj.islamiclifehelper.R;


public class CustomAdapter3 extends BaseAdapter {
    private String[] Title;
    private Activity activity;
    LayoutInflater inflater;
    private Context mContext;
    private String[] subTitle1;
    private String[] subTitle2;
    private String[] subTitle3;
    private String[] subTitle4;
    private View view2;

    @Override 
    public Object getItem(int i) {
        return null;
    }

    @Override 
    public long getItemId(int i) {
        return i;
    }

    public CustomAdapter3(Context context, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, Activity activity) {
        this.mContext = context;
        this.Title = strArr;
        this.subTitle1 = strArr2;
        this.subTitle2 = strArr3;
        this.subTitle3 = strArr4;
        this.subTitle4 = strArr5;
        this.activity = activity;
    }

    @Override 
    public int getCount() {
        return this.Title.length;
    }

    public String getItem1(int i) {
        return this.Title[i];
    }

    public String getItem2(int i) {
        return this.subTitle1[i];
    }

    public String getItem3(int i) {
        return this.subTitle2[i];
    }

    public String getItem4(int i) {
        return this.subTitle3[i];
    }

    public String getItem5(int i) {
        return this.subTitle4[i];
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        this.inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            this.view2 = new View(this.mContext);
        } else {
            this.view2 = view;
        }
        View inflate = this.inflater.inflate(R.layout.list_items4, viewGroup, false);
        this.view2 = inflate;
        ((TextView) inflate.findViewById(R.id.item5000)).setText(this.Title[i]);
        String str = this.subTitle1[i];
        String str2 = this.subTitle2[i];
        String replace = str.replace("From: ", "");
        String replace2 = str2.replace("To: ", "");
        ((TextView) this.view2.findViewById(R.id.subitem5000)).setText(replace);
        ((TextView) this.view2.findViewById(R.id.subsubitem5000)).setText(replace2);
        String str3 = this.subTitle3[i];
        String str4 = this.subTitle4[i];
        String replace3 = str3.replace("From: ", "");
        String replace4 = str4.replace("To: ", "");
        ((TextView) this.view2.findViewById(R.id.subsubsubitem5000)).setText(replace3);
        ((TextView) this.view2.findViewById(R.id.subsubsubsubitem5000)).setText(replace4);
        return this.view2;
    }
}
