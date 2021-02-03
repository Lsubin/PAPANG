package com.example.perfume.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Type1_Adapter extends RecyclerView.Adapter<Holder> {
    ArrayList<String> name;
    ArrayList<String> des;

    RecyclerView_Type1_Adapter(ArrayList<String> name, ArrayList<String> des){
        this.name = name;
        this.des = des;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_type1, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override public void onBindViewHolder(@NonNull Holder holder, int position)
    {
        holder.theme_name.setText(name.get(position));
        holder.theme_des.setText(des.get(position));
    }


    @Override
    public int getItemCount() {
        return name.size();
    }
}
class Holder extends RecyclerView.ViewHolder {
    TextView theme_name;
    TextView theme_des;
    ImageView theme_image;

    public Holder(@NonNull View itemView) {
        super(itemView);
        theme_name = itemView.findViewById(R.id.theme_name);
        theme_des = itemView.findViewById(R.id.theme_des);
        theme_image = itemView.findViewById(R.id.theme_image);
    }
}

