package com.example.perfume.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.object.Review;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {
    Context context;

    Review item;
    private ArrayList<Review> review_items = new ArrayList<Review>();
    private ArrayList<String> review = new ArrayList<String>();

    /*
    public ReviewListAdapter(Context context, Review item){
        this.context = context;
        this.item = item;
        review_items.add(item);
    }
    */

    public ReviewListAdapter(Context context, String name){
        this.context = context;
        review.add(name);
    }

    @NonNull
    @Override
    public ReviewListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_list, parent, false);
        ReviewListAdapter.ViewHolder holder = new ReviewListAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListAdapter.ViewHolder holder, int position) {
        holder.user_name.setText(review.get(position));
        //holder.review_type.setText(item.getReview_type());
        //holder.review_tag.setText(item.getReview_tag());
        //holder.review_text.setText(item.getReview_text());
        //holder.review_date.setText(item.getReview_date());
    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_image;
        TextView user_name;
        TextView review_type;
        RatingBar review_rating;
        TextView review_tag;
        TextView review_text;
        TextView review_heart_count;
        TextView review_date;

        ImageButton review_btn_heart;
        ImageButton review_btn_detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name = (TextView)itemView.findViewById(R.id.review_user_name);
            review_type = (TextView)itemView.findViewById(R.id.review_type);
            review_tag = (TextView)itemView.findViewById(R.id.review_tag);
            review_text = (TextView)itemView.findViewById(R.id.review_text);
            review_date = (TextView)itemView.findViewById(R.id.review_date);
            review_heart_count = (TextView)itemView.findViewById(R.id.review_heart_count);

            user_image = (ImageView)itemView.findViewById(R.id.review_user_img);
            review_rating = (RatingBar)itemView.findViewById(R.id.review_rating);

            review_btn_heart = (ImageButton)itemView.findViewById(R.id.review_btn_heart);
            review_btn_detail = (ImageButton)itemView.findViewById(R.id.review_btn_detail);
        }
    }

}
