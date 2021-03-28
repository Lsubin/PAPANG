package com.example.perfume.main.home;

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

public class Event_RecyclerView_Adapter extends RecyclerView.Adapter<Event_RecyclerView_Adapter.ViewHolder> {

    Context context;

    ArrayList<Drawable> event_drawables;          // 상품 이미지

    Event_RecyclerView_Adapter(Context context){
        this.context = context;

        event_drawables = new ArrayList<>();
        event_drawables.add(context.getResources().getDrawable(R.mipmap.ex_event_image));
    }

    @NonNull
    @Override
    public Event_RecyclerView_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        Event_RecyclerView_Adapter.ViewHolder holder = new Event_RecyclerView_Adapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Event_RecyclerView_Adapter.ViewHolder holder, int position) {

        holder.product_image.setImageDrawable(event_drawables.get(position));
    }

    @Override
    public int getItemCount() {
        return event_drawables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = (ImageView)itemView.findViewById(R.id.main_event_image);
        }
    }

}
