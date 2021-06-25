package com.papang.perfume.main.wish;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.papang.perfume.R;
import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;
import com.papang.perfume.data.PerfumeWish;
import com.papang.perfume.data.Wish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class WishProductAdapter extends RecyclerView.Adapter<WishProductAdapter.ViewHolder> {

    Context context;
    String path;

    DataService dataService;
    DataApi dataApi;

    List<Wish> wishes;

    WishProudctFragment wishProudctFragment;

    WishProductAdapter(Context context, List<Wish> wishes, WishProudctFragment fragment) {
        this.context = context;
        this.wishes = wishes;
        this.wishProudctFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_wish_prodcut, parent, false);
        WishProductAdapter.ViewHolder holder = new WishProductAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.product_image.setImageResource(wishes.get(position));
        holder.getImage(wishes.get(position).getName());
        holder.product_name.setText(wishes.get(position).getName());
        holder.company_name.setText(wishes.get(position).getBrand());
        holder.wish_ok.setImageResource(R.mipmap.heart_full_icon);
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    public String getName(int position){
        return wishes.get(position).getName();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView company_name;
        TextView product_name;
        ImageView wish_ok;

        String email;

        int count = 0;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            dataService = new DataService();
            dataApi =  dataService.getRetrofitClient().create(DataApi.class);
            checkLogin();

            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            wish_ok = (ImageView) itemView.findViewById(R.id.wish_ok);

            wish_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    wish_ok.setImageResource(R.mipmap.icon_unfull_heart);
                    getWishCount(wishes.get(position).getName(), wishes.get(position).getBrand());
                    deleteWishList(email, wishes.get(position).getBrand(), wishes.get(position).getName());
                }
            });
        }

        private void checkLogin(){
            SharedPreferences sharedPreferences = context.getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
            email = sharedPreferences.getString("Email","");
        }

        public void getWishCount(final String name, final String brand){
            dataApi.getPerfumeWish(name, brand).enqueue(new Callback<PerfumeWish>() {
                @Override
                public void onResponse(Call<PerfumeWish> call, Response<PerfumeWish> response) {
                    PerfumeWish pw = response.body();
                    count = pw.getWish_count();
                    deleteWishCount(name, brand, count);
                }

                @Override
                public void onFailure(Call<PerfumeWish> call, Throwable t) {

                }
            });
        }

        private void deleteWishCount(String name, String brand, int wish_count){
            Map<String, String> map = new HashMap();
            map.put("wish_count", String.valueOf(wish_count-1));
            dataApi.deleteWishCount(name, brand, map).enqueue(new Callback<PerfumeWish>() {
                @Override
                public void onResponse(Call<PerfumeWish> call, Response<PerfumeWish> response) {

                }

                @Override
                public void onFailure(Call<PerfumeWish> call, Throwable t) {

                }
            });
        }

        private void deleteWishList(String email, String brand, String name){
            dataApi.deleteWish(email, brand, name).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    Toast.makeText(context, "찜목록 삭제!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }

        public void getImage(final String p_name){
            String url = "https://papang-bucket.s3.ap-northeast-2.amazonaws.com/resources/perfume_de/" + p_name.trim() + ".png";
            Glide.with(context).load(url).into(product_image);
            wishProudctFragment.setCheckedImg(true);
        }
    }
}
