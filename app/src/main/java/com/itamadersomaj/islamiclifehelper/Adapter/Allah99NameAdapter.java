package com.itamadersomaj.islamiclifehelper.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.Model.AllahNameModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.R;
import com.itamadersomaj.islamiclifehelper.Util.Util;
import java.util.List;


public class Allah99NameAdapter extends RecyclerView.Adapter<Allah99NameAdapter.AlbumViewHolder> {
    private Context context;
    private List<AllahNameModel> list;
    OnItemClickListener onItemClickListener;

    public Allah99NameAdapter(Context context, List<AllahNameModel> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_99_names, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, @SuppressLint("RecyclerView") final int i) {
        albumViewHolder.tvArabicName.setText(this.list.get(i).getNameArabic());
        albumViewHolder.tvEnglishName.setText(this.list.get(i).getNameEnglish());
        albumViewHolder.tvEnglishMeaning.setText(this.list.get(i).getMeaningEnglish());
        albumViewHolder.ivShare.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Context context = Allah99NameAdapter.this.context;
                Util.shareText(context, "Arabic : " + ((AllahNameModel) Allah99NameAdapter.this.list.get(i)).getNameArabic() + "\nEnglish : " + ((AllahNameModel) Allah99NameAdapter.this.list.get(i)).getNameEnglish() + "\nMeaning : " + ((AllahNameModel) Allah99NameAdapter.this.list.get(i)).getMeaningEnglish());
            }
        });
        albumViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Allah99NameAdapter.this.onItemClickListener.OnClick(view, i);
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShare;
        TextView tvArabicName;
        TextView tvEnglishMeaning;
        TextView tvEnglishName;

        AlbumViewHolder(View view) {
            super(view);
            this.tvArabicName = (TextView) view.findViewById(R.id.tvArabicName);
            this.tvEnglishName = (TextView) view.findViewById(R.id.tvEnglishName);
            this.tvEnglishMeaning = (TextView) view.findViewById(R.id.tvEnglishMeaning);
            this.ivShare = (ImageView) view.findViewById(R.id.ivShare);
        }
    }
}
