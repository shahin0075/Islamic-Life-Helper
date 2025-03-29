package com.itamadersomaj.islamiclifehelper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.itamadersomaj.islamiclifehelper.Model.DuaCategoryModel;
import com.itamadersomaj.islamiclifehelper.OnItemClickListener.OnItemClickListener;
import com.itamadersomaj.islamiclifehelper.R;

import java.util.List;


public class DuaCategoryAdapter extends RecyclerView.Adapter<DuaCategoryAdapter.AlbumViewHolder> {
    private Context context;
    private List<DuaCategoryModel> list;
    OnItemClickListener onItemClickListener;

    public DuaCategoryAdapter(Context context, List<DuaCategoryModel> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override 
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dua_category, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.tvCategory.setText(this.list.get(i).getCategoryName());
        try {
            Log.e("MYTAG", "ErrorNo: onBindViewHolder:'" +this.list.get(i).getImageName()+"'");
            String s = this.list.get(i).getImageName();
            if(s.equals("ic_all_time")){
                Glide.with(this.context).load(R.drawable.ic_all_time).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_mosque")){
                Glide.with(this.context).load(R.drawable.ic_mosque).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_ablution")){
                Glide.with(this.context).load(R.drawable.ic_ablution).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_prayer")){
                Glide.with(this.context).load(R.drawable.ic_prayer).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_after_prayer")){
                Glide.with(this.context).load(R.drawable.ic_after_prayer).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_people")){
                Glide.with(this.context).load(R.drawable.ic_people).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_children")){
                Glide.with(this.context).load(R.drawable.ic_children).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_trust")){
                Glide.with(this.context).load(R.drawable.ic_trust).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_iman_ihsan")){
                Glide.with(this.context).load(R.drawable.ic_iman_ihsan).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_quran_duas")){
                Glide.with(this.context).load(R.drawable.ic_quran_duas).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_allahs_mercy_rahmath")){
                Glide.with(this.context).load(R.drawable.ic_allahs_mercy_rahmath).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_sick_people")){
                Glide.with(this.context).load(R.drawable.ic_sick_people).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_thanking_praising_allah")){
                Glide.with(this.context).load(R.drawable.ic_thanking_praising_allah).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_against_shirk_satan_evil")){
                Glide.with(this.context).load(R.drawable.ic_against_shirk_satan_evil).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_rain")){
                Glide.with(this.context).load(R.drawable.ic_rain).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_travel")){
                Glide.with(this.context).load(R.drawable.ic_travel).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_toilet")){
                Glide.with(this.context).load(R.drawable.ic_toilet).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_sleeping")){
                Glide.with(this.context).load(R.drawable.ic_sleeping).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_eating")){
                Glide.with(this.context).load(R.drawable.ic_eating).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_garment")){
                Glide.with(this.context).load(R.drawable.ic_garment).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_home")){
                Glide.with(this.context).load(R.drawable.ic_home).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_sorrow")){
                Glide.with(this.context).load(R.drawable.ic_sorrow).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_loss")){
                Glide.with(this.context).load(R.drawable.ic_loss).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_morning_evening")){
                Glide.with(this.context).load(R.drawable.ic_morning_evening).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_finance_wealth")){
                Glide.with(this.context).load(R.drawable.ic_finance_wealth).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_knowledge")){
                Glide.with(this.context).load(R.drawable.ic_knowledge).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_decision")){
                Glide.with(this.context).load(R.drawable.ic_decision).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_ramadan")){
                Glide.with(this.context).load(R.drawable.ic_ramadan).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_hajj_umrah")){
                Glide.with(this.context).load(R.drawable.ic_hajj_umrah).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_deceased")){
                Glide.with(this.context).load(R.drawable.ic_deceased).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_guidance")){
                Glide.with(this.context).load(R.drawable.ic_guidance).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_judgement")){
                Glide.with(this.context).load(R.drawable.ic_judgement).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_protection")){
                Glide.with(this.context).load(R.drawable.ic_protection).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_rizq")){
                Glide.with(this.context).load(R.drawable.ic_rizq).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_forgiveness")){
                Glide.with(this.context).load(R.drawable.ic_forgiveness).into(albumViewHolder.ivCategory);
            }else if(s.equals("ic_abuse")){
                Glide.with(this.context).load(R.drawable.ic_abuse).into(albumViewHolder.ivCategory);
            }else {
                Log.e("MYTAG", "ErrorNo: onBindViewHolder:else'" +this.list.get(i).getImageName()+"'");
                Glide.with(this.context).load(R.drawable.ic_all_time).into(albumViewHolder.ivCategory);
            }


        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYTAG", "ErrorNo: onBindViewHolder:" +e);
        }
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivCategory;
        LinearLayout llInfo;
        TextView tvCategory;

        AlbumViewHolder(View view) {
            super(view);
            this.tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            this.ivCategory = (ImageView) view.findViewById(R.id.ivCategory);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llInfo);
            this.llInfo = linearLayout;
            linearLayout.setOnClickListener(this);
        }

        @Override 
        public void onClick(View view) {
            DuaCategoryAdapter.this.onItemClickListener.OnClick(view, getLayoutPosition());
        }
    }
}
