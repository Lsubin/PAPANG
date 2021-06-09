package com.example.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.search.RecentSearchAdapter;

import java.util.ArrayList;

public class PerfumeSizeAdapter extends RecyclerView.Adapter<PerfumeSizeAdapter.ViewHolder> {
    Context context;

    ArrayList<String> product_sizes;          // 상품 사이즈
    ArrayList<Integer> product_ids;          // 상품 ID
    ArrayList<String> product_checks;          // 상품 Check

    public PerfumeSizeAdapter(Context context, ArrayList<String> sizes, ArrayList<Integer> ids, ArrayList<String> checks) {
        this.context = context;

        product_sizes = new ArrayList<>();
        product_ids = new ArrayList<>();
        product_checks = new ArrayList<>();
        this.product_sizes = sizes;
        this.product_ids = ids;
        this.product_checks = checks;
    }

    @NonNull
    @Override
    public PerfumeSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_size, parent, false);
        PerfumeSizeAdapter.ViewHolder holder = new PerfumeSizeAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PerfumeSizeAdapter.ViewHolder holder, int position) {

        holder.product_size.setText(product_sizes.get(position));
    }

    @Override
    public int getItemCount() {
        return product_sizes.size();
    }

    public String getSize(int position){
        return product_sizes.get(position);
    }

    public String getCheck(int position){
        return product_checks.get(position);
    }

    public int getID(int position){
        return product_ids.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_size = (TextView) itemView.findViewById(R.id.product_size);
        }
    }

}