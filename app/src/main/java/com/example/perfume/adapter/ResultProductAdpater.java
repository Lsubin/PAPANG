package com.example.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

public class ResultProductAdpater extends RecyclerView.Adapter<ResultProductAdpater.ViewHolder> {

    Context context;
    ArrayList<Integer> product_drawables;          // 상품 이미지
    ArrayList<String> company_name;                 // 회사 이름
    ArrayList<String> product_name;                 // 상품 이름
    ArrayList<Integer> wish_drawable;              // 좋아요 표시

    public ResultProductAdpater(Context context, ArrayList<Integer> mProduct_drawalbes, ArrayList<String> mCompany_name,
                         ArrayList<String> mProduct_name, ArrayList<Integer> mWish_drawable){
        this.context = context;
        this.product_drawables = mProduct_drawalbes;
        this.company_name = mCompany_name;
        this.product_name = mProduct_name;
        this.wish_drawable = mWish_drawable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_result_product, parent, false);
        ResultProductAdpater.ViewHolder holder = new ResultProductAdpater.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product_image.setImageResource(product_drawables.get(position));
        holder.product_name.setText(product_name.get(position));
        holder.company_name.setText(company_name.get(position));
        holder.wish_ok.setImageResource(wish_drawable.get(position));
    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView company_name;
        TextView product_name;
        ImageView wish_ok;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = (ImageView)itemView.findViewById(R.id.product_image);
            company_name = (TextView)itemView.findViewById(R.id.company_name);
            product_name = (TextView)itemView.findViewById(R.id.product_name);
            wish_ok = (ImageView)itemView.findViewById(R.id.unWish_btn);

            wish_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wish_ok.setImageResource(R.mipmap.icon_unfull_heart);
                }
            });

        }
    }

}
