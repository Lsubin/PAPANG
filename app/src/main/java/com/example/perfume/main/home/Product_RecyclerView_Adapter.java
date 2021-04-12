package com.example.perfume.main.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.main.wish.WishProductAdapter;

import java.util.ArrayList;

public class Product_RecyclerView_Adapter extends RecyclerView.Adapter<Product_RecyclerView_Adapter.ViewHolder> {
    Context context;

    ArrayList<Drawable> product_drawables;          // 상품 이미지
    ArrayList<String> company_name;                 // 회사 이름
    ArrayList<String> product_name;                 // 상품 이름

    public Product_RecyclerView_Adapter(Context context, ArrayList<Drawable> mProduct_drawalbes, ArrayList<String> mCompany_name,
                       ArrayList<String> mProduct_name){
        this.context = context;
        this.product_drawables = mProduct_drawalbes;
        this.company_name = mCompany_name;
        this.product_name = mProduct_name;
    }

    @NonNull
    @Override
    public Product_RecyclerView_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_product, parent, false);
        Product_RecyclerView_Adapter.ViewHolder holder = new Product_RecyclerView_Adapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Product_RecyclerView_Adapter.ViewHolder holder, int position) {

        holder.product_image.setImageDrawable(product_drawables.get(position));
        holder.product_name.setText(product_name.get(position));
        holder.company_name.setText(company_name.get(position));
    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView company_name;
        TextView product_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = (ImageView)itemView.findViewById(R.id.product_image);
            company_name = (TextView)itemView.findViewById(R.id.company_name);
            product_name = (TextView)itemView.findViewById(R.id.product_name);
        }
    }
}
