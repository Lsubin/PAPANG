package com.papang.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.papang.perfume.R;

import java.util.ArrayList;

public class ReviewHashAdapter extends RecyclerView.Adapter<ReviewHashAdapter.ViewHolder> {

    Context context;
    ArrayList<String> search_words;

    public ReviewHashAdapter(Context context) {
        this.context = context;
        search_words = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReviewHashAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hash_tag, parent, false);
        ReviewHashAdapter.ViewHolder holder = new ReviewHashAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHashAdapter.ViewHolder holder, int position) {
        holder.rw_hash_tag_text.setText(search_words.get(position));
    }

    @Override
    public int getItemCount() {
        return search_words.size();
    }

    public void saveHashtag(String hash_tag) {
        search_words.add(hash_tag);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rw_hash_tag_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rw_hash_tag_text = (TextView) itemView.findViewById(R.id.rw_hash_tag_text);
        }
    }

}

