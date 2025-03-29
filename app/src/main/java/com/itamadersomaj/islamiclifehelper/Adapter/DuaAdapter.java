package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.DatabaseHelper.DatabaseAccess;
import com.itamadersomaj.islamiclifehelper.Model.DuaModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.R;
import java.util.List;


public class DuaAdapter extends RecyclerView.Adapter<DuaAdapter.AlbumViewHolder> {
    private Context context;
    DatabaseAccess databaseAccess;
    private List<DuaModel> list;
    OnItemClickListener onItemClickListener;

    public DuaAdapter(Context context, List<DuaModel> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        this.databaseAccess = databaseAccess;
        databaseAccess.open();
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dua, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(final AlbumViewHolder albumViewHolder, final int i) {
        albumViewHolder.tvDua.setText(this.list.get(i).getDua());
        albumViewHolder.tvTranslation.setText(this.list.get(i).getTranslation());
        albumViewHolder.tvEnglishMeaning.setText(this.list.get(i).getEnMeaning());
        albumViewHolder.tvReference.setText(this.list.get(i).getEnReference());
        if (this.list.get(i).isFavorite()) {
            albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
        albumViewHolder.ivFavorite.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (((DuaModel) DuaAdapter.this.list.get(i)).isFavorite()) {
                    ((DuaModel) DuaAdapter.this.list.get(i)).setFavorite(false);
                    albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                } else {
                    ((DuaModel) DuaAdapter.this.list.get(i)).setFavorite(true);
                    albumViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                }
                DuaAdapter.this.databaseAccess.updateFavoriteDua((DuaModel) DuaAdapter.this.list.get(i));
            }
        });
        albumViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                DuaAdapter.this.onItemClickListener.OnClick(view, i);
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFavorite;
        TextView tvDua;
        TextView tvEnglishMeaning;
        TextView tvReference;
        TextView tvTranslation;

        AlbumViewHolder(View view) {
            super(view);
            this.tvDua = (TextView) view.findViewById(R.id.tvDua);
            this.tvTranslation = (TextView) view.findViewById(R.id.tvTranslation);
            this.tvEnglishMeaning = (TextView) view.findViewById(R.id.tvEnglishMeaning);
            this.tvReference = (TextView) view.findViewById(R.id.tvReference);
            this.ivFavorite = (ImageView) view.findViewById(R.id.ivFavorite);
        }
    }
}
