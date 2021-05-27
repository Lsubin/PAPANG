package com.example.perfume.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

import java.util.ArrayList;

public class RecommendationSearchAdapter extends RecyclerView.Adapter<RecommendationSearchAdapter.ViewHolder> {

    Context context;
    ArrayList<String> search_words;

    public RecommendationSearchAdapter(Context context) {
        this.context = context;

        search_words = new ArrayList<>();
        search_words.add("샤넬");
        search_words.add("산티마리아노벨레");
        search_words.add("이솝 휠");
        search_words.add("조말론");
        search_words.add("조말론 블랙베리앤베이");
        search_words.add("마크제이콥스 레인");
        search_words.add("러쉬 플럼레인");
        search_words.add("조말론 피오니 앤 블러쉬 스웨이드");
    }

    @NonNull
    @Override
    public RecommendationSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendation_search_word, parent, false);
        RecommendationSearchAdapter.ViewHolder holder = new RecommendationSearchAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationSearchAdapter.ViewHolder holder, int position) {

        holder.recommendation_search_word.setText(search_words.get(position));
    }

    @Override
    public int getItemCount() {
        return search_words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recommendation_search_word;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendation_search_word = (TextView) itemView.findViewById(R.id.recommendation_search_word);
        }
    }

}
