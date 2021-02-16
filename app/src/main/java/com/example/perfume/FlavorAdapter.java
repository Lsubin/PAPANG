package com.example.perfume;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlavorAdapter extends RecyclerView.Adapter<com.example.perfume.Holder> {
    ArrayList<Drawable> drawables;
    ArrayList<String> des;

    FlavorAdapter(ArrayList<Drawable> drawables){
        this.drawables = drawables;
    }

    @NonNull
    @Override
    public com.example.perfume.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_flavor_type1, parent, false);
        com.example.perfume.Holder holder = new com.example.perfume.Holder(view);
        return holder;
    }

    @Override public void onBindViewHolder(@NonNull com.example.perfume.Holder holder, int position)
    {
        holder.theme_image.setImageDrawable(drawables.get(position));
    }


    @Override
    public int getItemCount() {
        return drawables.size();
    }
}
class Holder extends RecyclerView.ViewHolder {
    ImageView theme_image;

    public Holder(@NonNull View itemView) {
        super(itemView);
        theme_image = itemView.findViewById(R.id.flavor2_item);
    }
}

