package com.itamadersomaj.islamiclifehelper.Adapter.ViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.itamadersomaj.islamiclifehelper.Model.HajjModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.R;
import com.itamadersomaj.islamiclifehelper.Util.Util;
import java.util.List;


public class HajjJourneyAdapter extends PagerAdapter {
    Context context;
    List<HajjModel> hajjModelList;
    OnItemClickListener onItemClickListener;

    @Override 
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public HajjJourneyAdapter(Context context, List<HajjModel> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.hajjModelList = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override 
    public int getCount() {
        return this.hajjModelList.size();
    }

    @Override 
    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.context).inflate(R.layout.item_hajj_guide, viewGroup, false);
        ((TextView) viewGroup2.findViewById(R.id.tvTitle)).setText(this.hajjModelList.get(i).getTitle());
        ((TextView) viewGroup2.findViewById(R.id.tvSubTitle)).setText(this.hajjModelList.get(i).getSubTitle());
        ((TextView) viewGroup2.findViewById(R.id.tvSubDescription)).setText(this.hajjModelList.get(i).getSubDescription());
        ((ImageView) viewGroup2.findViewById(R.id.ivThumbnail)).setImageDrawable(Util.imgToDraw(this.context, this.hajjModelList.get(i).getImgName()));
        ((LinearLayout) viewGroup2.findViewById(R.id.btnView)).setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                HajjJourneyAdapter.this.onItemClickListener.OnClick(view, i);
            }
        });
        viewGroup.addView(viewGroup2);
        return viewGroup2;
    }

    @Override 
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
