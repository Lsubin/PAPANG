package com.papang.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.papang.perfume.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllMagazineAdaper extends RecyclerView.Adapter<AllMagazineAdaper.ViewHolder> {

    Context context;
    ArrayList<String> m_text1;
    ArrayList<String> m_text2;

    public AllMagazineAdaper(Context context, ArrayList<String> text1, ArrayList<String> text2){
        this.context = context;
        m_text1 = new ArrayList<>();
        m_text2 = new ArrayList<>();
        this.m_text1 = text1;
        this.m_text2 = text2;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_magazine, parent, false);
        AllMagazineAdaper.ViewHolder holder = new AllMagazineAdaper.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImage(m_text1.get(position));
        holder.magazine_text1.setText(m_text1.get(position));
        holder.magazine_text2.setText(m_text2.get(position));
    }

    @Override
    public int getItemCount() {
        return m_text1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView magazine_img;
        TextView magazine_text1;
        TextView magazine_text2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            magazine_img = (ImageView) itemView.findViewById(R.id.magazine_img);
            magazine_text1 = (TextView) itemView.findViewById(R.id.magazine_text1);
            magazine_text2 = (TextView) itemView.findViewById(R.id.magazine_text2);

        }


        public void getImage(final String m_text1){
            /*
            String url = "https://papang-bucket.s3.ap-northeast-2.amazonaws.com/resources/perfume_de/" + p_name.trim() + ".png";
            Glide.with(context).load(url).into(product_image); */
            magazine_img.setImageResource(R.mipmap.magazine1_img);
        }

    }
}
