package com.papang.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.papang.perfume.R;

import java.util.ArrayList;

public class ReviewImageAdapter extends RecyclerView.Adapter<ReviewImageAdapter.ViewHolder> {

    Context context;
    ArrayList<Integer> review_drawables;          // 상품 이미지

    public ReviewImageAdapter(Context context) {
        this.context = context;

        review_drawables = new ArrayList<>();
        review_drawables.add(1);
        review_drawables.add(1);
        review_drawables.add(1);
    }

    @NonNull
    @Override
    public ReviewImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_image, parent, false);
        ReviewImageAdapter.ViewHolder holder = new ReviewImageAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewImageAdapter.ViewHolder holder, int position) {
        holder.dr_review_images.setBackgroundColor(context.getResources().getColor(R.color.cardViewBackground));
    }

    @Override
    public int getItemCount() {
        return review_drawables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dr_review_images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dr_review_images = (ImageView) itemView.findViewById(R.id.dr_review_images);
        }
    }


}
