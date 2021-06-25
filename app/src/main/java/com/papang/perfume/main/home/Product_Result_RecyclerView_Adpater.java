package com.papang.perfume.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.papang.perfume.R;
import com.papang.perfume.data.Recommendation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Product_Result_RecyclerView_Adpater extends RecyclerView.Adapter<Product_Result_RecyclerView_Adpater.ViewHolder> {
    Context context;
    List<Recommendation> perfumes;
    String path;

    HomeFragment homeFragment;

    public Product_Result_RecyclerView_Adpater(Context context, List<Recommendation> perfumes, HomeFragment fragment) {
        this.context = context;
        this.perfumes = perfumes;
        this.homeFragment = fragment;
    }

    @NonNull
    @Override
    public Product_Result_RecyclerView_Adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_perfume_result, parent, false);
        Product_Result_RecyclerView_Adpater.ViewHolder holder = new Product_Result_RecyclerView_Adpater.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Product_Result_RecyclerView_Adpater.ViewHolder holder, int position) {

        //holder.product_image.setImageDrawable(perfumes.get(position).get);
        holder.product_name.setText(perfumes.get(position).getName());
        holder.company_name.setText(perfumes.get(position).getBrand());
        holder.getImage(perfumes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return perfumes.size();
    }

    public String getName(int position){
        return perfumes.get(position).getName();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView company_name;
        TextView product_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
        }

        public void getImage(final String p_name){
            String url = "https://papang-bucket.s3.ap-northeast-2.amazonaws.com/resources/perfume_de/" + p_name.trim() + ".png";
            Glide.with(context).load(url).into(product_image);
            homeFragment.setCheckedImg2(true);
        }

    }
}

