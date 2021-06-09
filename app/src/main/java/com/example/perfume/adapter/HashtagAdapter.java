package com.example.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

import java.util.ArrayList;

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.ViewHolder> {
    Context context;

    ArrayList<String> hashtag;          // 상품 사이즈

    public HashtagAdapter(Context context, ArrayList<String> hashtag) {
        this.context = context;
        this.hashtag = hashtag;
    }

    @NonNull
    @Override
    public HashtagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_size, parent, false);
        HashtagAdapter.ViewHolder holder = new HashtagAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HashtagAdapter.ViewHolder holder, int position) {

        holder.product_size.setText(hashtag.get(position));
    }

    @Override
    public int getItemCount() {
        return hashtag.size();
    }

    public String getSize(int position){
        return hashtag.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_size = (TextView) itemView.findViewById(R.id.product_size);
        }
    }

}
