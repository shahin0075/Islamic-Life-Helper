package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.Model.ContentModel;
import com.itamadersomaj.islamiclifehelper.R;
import java.util.List;


public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    List<ContentModel> contentModelList;
    Context context;

    @Override 
    public int getItemViewType(int i) {
        return i;
    }

    public ContentAdapter(Context context, List<ContentModel> list) {
        this.context = context;
        this.contentModelList = list;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_content, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.contentModelList.get(i).getCategory().isEmpty()) {
            viewHolder.tvCategory.setVisibility(View.GONE);
        }
        viewHolder.tvCategory.setText(this.contentModelList.get(i).getCategory());
        viewHolder.tvContent.setText(this.contentModelList.get(i).getContent());
    }

    @Override 
    public int getItemCount() {
        return this.contentModelList.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            this.tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            this.tvContent = (TextView) view.findViewById(R.id.tvContent);
        }
    }
}
