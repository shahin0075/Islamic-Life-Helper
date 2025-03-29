package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.itamadersomaj.islamiclifehelper.Model.DhikrModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.PreferenceUtil.PrayerSharedPreference;
import com.itamadersomaj.islamiclifehelper.R;
import java.util.List;


public class DhikrAdapter extends RecyclerView.Adapter<DhikrAdapter.AlbumViewHolder> {
    private Context context;
    private List<DhikrModel> list;
    OnItemClickListener onItemClickListener;
    PrayerSharedPreference prayerSharedPreference;

    @Override 
    public int getItemViewType(int i) {
        return i;
    }

    public DhikrAdapter(Context context, List<DhikrModel> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.prayerSharedPreference = new PrayerSharedPreference(context);
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dhikr, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, final int i) {
        albumViewHolder.tvDhikrArabic.setText(this.list.get(i).getDhikrArabicName());
        albumViewHolder.tvDhikrTranslation.setText(this.list.get(i).getDhikrEnglishName());
        albumViewHolder.tvDhikrEnglishMeaning.setText(this.list.get(i).getDhikrEnglishMeaning());
        if (this.prayerSharedPreference.getDhikrId() == this.list.get(i).getId()) {
            albumViewHolder.btnSelect.setStrokeWidth(0);
            albumViewHolder.btnSelect.setBackgroundTintList(ColorStateList.valueOf(this.context.getResources().getColor(R.color.grey)));
            albumViewHolder.tvSelect.setText("Selected");
            albumViewHolder.tvSelect.setTextColor(this.context.getResources().getColor(R.color.white));
            albumViewHolder.ivSelect.setImageResource(R.drawable.ic_select);
        }
        albumViewHolder.btnSelect.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                DhikrAdapter.this.onItemClickListener.OnClick(view, i);
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView btnSelect;
        ImageView ivSelect;
        TextView tvDhikrArabic;
        TextView tvDhikrEnglishMeaning;
        TextView tvDhikrTranslation;
        TextView tvSelect;

        AlbumViewHolder(View view) {
            super(view);
            this.tvDhikrArabic = (TextView) view.findViewById(R.id.tvDhikrArabic);
            this.tvDhikrTranslation = (TextView) view.findViewById(R.id.tvDhikrTranslation);
            this.tvDhikrEnglishMeaning = (TextView) view.findViewById(R.id.tvDhikrEnglishMeaning);
            this.btnSelect = (MaterialCardView) view.findViewById(R.id.btnSelect);
            this.tvSelect = (TextView) view.findViewById(R.id.tvSelect);
            this.ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
        }
    }
}
