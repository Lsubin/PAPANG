package com.example.perfume.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.main.home.Event_RecyclerView_Adapter;

import java.util.ArrayList;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.ViewHolder> {
    Context context;

    ArrayList<String> search_words;          // 상품 이미지

    RecentSearchAdapter(Context context) {
        this.context = context;

        search_words = new ArrayList<>();
        search_words.add("샤넬 넘버 5");
        search_words.add("딥티크");
    }

    public void allDelete() {
        this.search_words.clear();
        notifyDataSetChanged();
    }

    public void update() {
        search_words.add("샤넬 넘버 5");
        search_words.add("딥티크");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecentSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_search_word, parent, false);
        RecentSearchAdapter.ViewHolder holder = new RecentSearchAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSearchAdapter.ViewHolder holder, int position) {

        holder.recent_search_word.setText(search_words.get(position));
    }

    @Override
    public int getItemCount() {
        return search_words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recent_search_word;
        ImageButton delete_recent_search_word;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recent_search_word = (TextView) itemView.findViewById(R.id.recent_search_word);
            delete_recent_search_word = (ImageButton) itemView.findViewById(R.id.delete_recent_search_word);
        }
    }

}
