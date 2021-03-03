package com.example.perfume;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlavorAdapter extends RecyclerView.Adapter<com.example.perfume.Holder> {
    ArrayList<Drawable> drawables;
    ArrayList<String> des;
    Context context;

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

    public Drawable getDrawable(int position){
        return drawables.get(position);
    }

    public void setDrawable(int position, Drawable drawable){
        drawables.set(2, context.getResources().getDrawable(R.mipmap.flavor_woody));
    }
}


class Holder extends RecyclerView.ViewHolder {
    ImageView theme_image;

    public Holder(@NonNull final View itemView) {
        super(itemView);
        theme_image = itemView.findViewById(R.id.flavor2_item);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION)
                {
                    Toast.makeText(view.getContext(), "클릭된 아이템 번호 "+pos , Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

