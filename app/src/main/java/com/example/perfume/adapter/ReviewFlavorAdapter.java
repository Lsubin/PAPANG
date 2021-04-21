package com.example.perfume.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReviewFlavorAdapter extends RecyclerView.Adapter<Holder> {
    Context mContext;

    ArrayList<Drawable> drawables;          // 기본 이미지
    ArrayList<Boolean> check;               // 체크 표시

    public ReviewFlavorAdapter(Context context, ArrayList<Boolean> isChecked) {
        this.mContext = context;
        drawables = new ArrayList<>();
        check = new ArrayList<>();

        // drawable 이미지들 추가
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_green));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_citrus));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_fruity));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_floral));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_nutty));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_aromatic));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_spicy));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_leather));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_aldehyde));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_woody));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_animalic));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_balam));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_chypre));
        drawables.add(mContext.getResources().getDrawable(R.mipmap.rw_aquatic));

        this.check = isChecked;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_rw_flavor, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //Toast.makeText(mContext, String.valueOf(position), 0).show();
        holder.theme_image.setImageDrawable(drawables.get(position));
        if(check.get(position) ==  true){
            holder.select_image.setVisibility(View.VISIBLE);
        }
        else{
            holder.select_image.setVisibility(View.INVISIBLE);
        }
    }

    public void setCheck(int position, boolean isChecked){
        check.set(position, isChecked);
        notifyDataSetChanged();
    }

    public Boolean getCheck(int position){
        return check.get(position);
    }

    public int getCheckCount(){
        int count = 0;
        for(int i = 0; i < check.size(); i++){
            if(check.get(i) == true)
                count++;
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return drawables.size();
    }
}


