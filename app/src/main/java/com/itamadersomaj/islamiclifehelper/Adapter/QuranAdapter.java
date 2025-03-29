package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.AlQuranDataModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.R;
import java.util.List;


public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.AlbumViewHolder> {
    private Context context;
    DatabaseAccess databaseAccess;
    private List<AlQuranDataModel> list;
    OnItemClickListener onItemClickListener;
    int type;

    public QuranAdapter(Context context, List<AlQuranDataModel> list, OnItemClickListener onItemClickListener, int i) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.type = i;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        this.databaseAccess = databaseAccess;
        databaseAccess.open();
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quran, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(final AlbumViewHolder albumViewHolder, final int i) {
        albumViewHolder.tvArabicTranslation.setText(this.list.get(i).getAyahTranslation());
        albumViewHolder.tvEnglishName.setText(this.list.get(i).getAyahEnglish());
        albumViewHolder.tvArabicName.setText(this.list.get(i).getAyahArabic());
        if (this.list.get(i).isFavorite()) {
            albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
        albumViewHolder.ivFavorite.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (((AlQuranDataModel) QuranAdapter.this.list.get(i)).isFavorite()) {
                    ((AlQuranDataModel) QuranAdapter.this.list.get(i)).setFavorite(false);
                    albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                } else {
                    ((AlQuranDataModel) QuranAdapter.this.list.get(i)).setFavorite(true);
                    albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                }
                QuranAdapter.this.databaseAccess.updateFavoriteQuran((AlQuranDataModel) QuranAdapter.this.list.get(i));
            }
        });
        albumViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                QuranAdapter.this.onItemClickListener.OnClick(view, i);
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFavorite;
        LinearLayout llInfo;
        TextView tvArabicName;
        TextView tvArabicTranslation;
        TextView tvEnglishName;

        AlbumViewHolder(View view) {
            super(view);
            this.tvArabicTranslation = (TextView) view.findViewById(R.id.tvArabicTranslation);
            this.tvEnglishName = (TextView) view.findViewById(R.id.tvEnglishName);
            this.tvArabicName = (TextView) view.findViewById(R.id.tvArabicName);
            this.ivFavorite = (ImageView) view.findViewById(R.id.ivFavorite);
        }
    }
}
