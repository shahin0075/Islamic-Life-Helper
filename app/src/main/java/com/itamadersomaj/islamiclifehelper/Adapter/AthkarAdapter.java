package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.Model.AthkarModel;
import com.itamadersomaj.islamiclifehelper.R;
import com.itamadersomaj.islamiclifehelper.Util.Util;
import java.util.List;


public class AthkarAdapter extends RecyclerView.Adapter<AthkarAdapter.ViewHolder> {
    List<AthkarModel> athkarModelList;
    Context context;

    @Override 
    public int getItemViewType(int i) {
        return i;
    }

    public AthkarAdapter(Context context, List<AthkarModel> list) {
        this.context = context;
        this.athkarModelList = list;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_athkar, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final int readTime = this.athkarModelList.get(i).getReadTime();
        if (this.athkarModelList.get(i).getAthkarArabic().isEmpty()) {
            viewHolder.llArabic.setVisibility(View.GONE);
        } else {
            viewHolder.tvAthkarArabic.setText(this.athkarModelList.get(i).getAthkarArabic());
        }
        if (this.athkarModelList.get(i).getAthkarDescription().isEmpty()) {
            viewHolder.llEnglish.setVisibility(View.GONE);
        } else {
            viewHolder.tvAthkarEnglish.setText(this.athkarModelList.get(i).getAthkarDescription());
        }
        if (this.athkarModelList.get(i).getAthkarEnglish().isEmpty()) {
            viewHolder.llTranslation.setVisibility(View.GONE);
        } else {
            viewHolder.tvAthkarTranslation.setText(this.athkarModelList.get(i).getAthkarEnglish());
        }
        TextView textView = viewHolder.tvAthkarCount;
        textView.setText("" + (i + 1) + "-" + this.athkarModelList.size());
        if (this.athkarModelList.get(i).isReadTimeComplete()) {
            this.athkarModelList.get(i).setReadTimeCount(readTime);
        }
        TextView textView2 = viewHolder.tvAthkarCounting;
        textView2.setText("" + this.athkarModelList.get(i).getReadTimeCount() + "/" + readTime);
        viewHolder.btnAthkarReadTime.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (AthkarAdapter.this.athkarModelList.get(i).getReadTimeCount() != readTime) {
                    AthkarAdapter.this.athkarModelList.get(i).setReadTimeCount(AthkarAdapter.this.athkarModelList.get(i).getReadTimeCount() + 1);
                    TextView textView3 = viewHolder.tvAthkarCounting;
                    textView3.setText("" + AthkarAdapter.this.athkarModelList.get(i).getReadTimeCount() + "/" + readTime);
                    if (AthkarAdapter.this.athkarModelList.get(i).getReadTimeCount() == readTime) {
                        viewHolder.btnAthkarReadTime.setBackgroundResource(R.drawable.round_corner_square_7);
                        viewHolder.tvAthkarCounting.setTextColor(AthkarAdapter.this.context.getResources().getColor(R.color.white));
                        viewHolder.tvCounting.setTextColor(AthkarAdapter.this.context.getResources().getColor(R.color.white));
                        AthkarAdapter.this.athkarModelList.get(i).setReadTimeComplete(true);
                    }
                }
            }
        });
        viewHolder.ivShare.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Util.shareText(AthkarAdapter.this.context, AthkarAdapter.this.athkarModelList.get(i).getAthkarArabic() + "\n\n" + AthkarAdapter.this.athkarModelList.get(i).getAthkarEnglish());
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.athkarModelList.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnAthkarReadTime;
        ImageView ivShare;
        LinearLayout llArabic;
        LinearLayout llEnglish;
        LinearLayout llTranslation;
        TextView tvAthkarArabic;
        TextView tvAthkarCount;
        TextView tvAthkarCounting;
        TextView tvAthkarEnglish;
        TextView tvAthkarTranslation;
        TextView tvCounting;

        public ViewHolder(View view) {
            super(view);
            this.tvAthkarArabic = (TextView) view.findViewById(R.id.tvAthkarArabic);
            this.tvAthkarEnglish = (TextView) view.findViewById(R.id.tvAthkarEnglish);
            this.tvAthkarTranslation = (TextView) view.findViewById(R.id.tvAthkarTranslation);
            this.tvAthkarCount = (TextView) view.findViewById(R.id.tvAthkarCount);
            this.tvAthkarCounting = (TextView) view.findViewById(R.id.tvAthkarCounting);
            this.tvCounting = (TextView) view.findViewById(R.id.tvCounting);
            this.btnAthkarReadTime = (LinearLayout) view.findViewById(R.id.btnAthkarReadTime);
            this.ivShare = (ImageView) view.findViewById(R.id.ivShare);
            this.llArabic = (LinearLayout) view.findViewById(R.id.llArabic);
            this.llEnglish = (LinearLayout) view.findViewById(R.id.llEnglish);
            this.llTranslation = (LinearLayout) view.findViewById(R.id.llTranslation);
        }
    }
}
