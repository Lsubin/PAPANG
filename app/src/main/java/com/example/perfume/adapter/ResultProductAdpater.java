package com.example.perfume.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bumptech.glide.Glide;
import com.example.perfume.ProductDetailsActivity;
import com.example.perfume.R;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.Perfume;
import com.example.perfume.data.PerfumeWish;
import com.example.perfume.data.Wish;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ResultProductAdpater extends RecyclerView.Adapter<ResultProductAdpater.ViewHolder> {

    Context context;
    ArrayList<String> p_name;
    ArrayList<String> p_brand;

    DataService dataService;
    DataApi dataApi;

    String path;
    File file;

    public ResultProductAdpater(Context context, ArrayList<String> name, ArrayList<String> brand) {
        this.context = context;
        p_name = new ArrayList<>();
        p_brand = new ArrayList<>();
        this.p_name = name;
        this.p_brand = brand;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_result_product, parent, false);
        ResultProductAdpater.ViewHolder holder = new ResultProductAdpater.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.product_image.setImageResource(perfumes.get(position).get);
        holder.getImage(p_name.get(position));
        holder.product_name.setText(p_name.get(position));
        holder.company_name.setText(p_brand.get(position));
        holder.checkWishList(p_name.get(position), p_brand.get(position));
    }

    @Override
    public int getItemCount() {
        return p_name.size();
    }

    public String getName(int position){
        return p_name.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView company_name;
        TextView product_name;
        ImageView wish_ok;

        String email;
        int count = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dataService = new DataService();
            dataApi =  dataService.getRetrofitClient().create(DataApi.class);
            checkLogin();
            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            wish_ok = (ImageView) itemView.findViewById(R.id.unWish_btn);

            wish_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (wish_ok.getDrawable().getConstantState().equals(context.getResources().getDrawable(R.mipmap.icon_unfull_heart).getConstantState())) {
                        wish_ok.setImageResource(R.mipmap.heart_full_icon);
                        getWishCount(p_name.get(position), p_brand.get(position), "ADD");
                        addWishList(p_brand.get(position), p_name.get(position));
                    }
                    else if(wish_ok.getDrawable().getConstantState().equals(context.getResources().getDrawable(R.mipmap.heart_full_icon).getConstantState())){
                        wish_ok.setImageResource(R.mipmap.icon_unfull_heart);
                        getWishCount(p_name.get(position), p_brand.get(position), "DELETE");
                        deleteWishList(email, p_brand.get(position), p_name.get(position));
                    }
                }
            });

        }

        private void checkLogin(){
            SharedPreferences sharedPreferences = context.getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
            email = sharedPreferences.getString("Email","");
        }

        private void checkWishList(String p_name, String p_brand){
            dataApi.getWisPerfume(email, p_brand, p_name).enqueue(new Callback<Wish>() {
                @Override
                public void onResponse(Call<Wish> call, Response<Wish> response) {
                    wish_ok.setImageDrawable(context.getResources().getDrawable(R.mipmap.heart_full_icon));
                }

                @Override
                public void onFailure(Call<Wish> call, Throwable t) {
                    wish_ok.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_unfull_heart));
                }
            });
        }

        public void getWishCount(final String name, final String brand, final String state){
            dataApi.getPerfumeWish(name, brand).enqueue(new Callback<PerfumeWish>() {
                @Override
                public void onResponse(Call<PerfumeWish> call, Response<PerfumeWish> response) {
                    PerfumeWish pw = response.body();
                    count = pw.getWish_count();

                    if(state.equals("ADD"))
                        addWishCount(count, name, brand);
                    else if(state.equals("DELETE"))
                        deleteWishCount(name, brand, count);
                }

                @Override
                public void onFailure(Call<PerfumeWish> call, Throwable t) {

                }
            });
        }

        private void addWishCount(int wish_count, String p_name, String brand){
            Map<String, String> map = new HashMap();
            final int count = wish_count + 1;
            map.put("wish_count", String.valueOf(count));
            dataApi.addWishCount(p_name, brand, map).enqueue(new Callback<PerfumeWish>() {
                @Override
                public void onResponse(Call<PerfumeWish> call, Response<PerfumeWish> response) {
                }

                @Override
                public void onFailure(Call<PerfumeWish> call, Throwable t) {

                }
            });
        }

        private void addWishList(String shop_name, String perfume_name){
            Map<String, String> map = new HashMap();
            map.put("email", email);
            map.put("brand", shop_name);
            map.put("name", perfume_name);
            dataApi.addWishList(map).enqueue(new Callback<Wish>() {
                @Override
                public void onResponse(Call<Wish> call, Response<Wish> response) {
                    wish_ok.setImageDrawable(context.getResources().getDrawable(R.mipmap.heart_full_icon));
                    Toast.makeText(context, "찜목록 추가!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Wish> call, Throwable t) {
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

            // Amazon Cognito 인증 공급자를 초기화합니다
            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    context,
                    "us-east-2:7241c5b2-3cf6-4a26-99d2-d08b31b32f8b", // 자격 증명 풀 ID
                    Regions.US_EAST_2 // 리전
            );

            TransferNetworkLossHandler.getInstance(context);
            AmazonS3Client amazonS3Client = new AmazonS3Client(credentialsProvider, Region.getRegion(Regions.AP_NORTHEAST_2));
            TransferUtility transferUtility = TransferUtility.builder()
                    .context(context)
                    .defaultBucket("papang-bucket")
                    .s3Client(amazonS3Client)
                    .build();

            String key = "resources/perfume_de/" + p_name + ".png";
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/papang_images").toString();
            file = new File(path);
            if(!file.exists())
                file.mkdirs();

            final File download_file = new File(file.getPath() + "/" + p_name + ".png");
            Log.e("이름", file.getPath());

            TransferObserver downloadObserver = transferUtility.download(key, download_file);
            // 다운로드 과정을 알 수 있도록 Listener를 추가할 수 있다.
            downloadObserver.setTransferListener(new TransferListener() {
                @Override
                public void onStateChanged(int id, TransferState state) {
                    if (TransferState.COMPLETED == state) {
                        // Handle a completed upload.
                        Bitmap bitmap = BitmapFactory.decodeFile(path + "/" + p_name + ".png");
                        if(bitmap != null)
                            Glide.with(context)
                                    .load(bitmap)
                                    .fitCenter()
                                    .into(product_image);
                    }
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                }

                @Override
                public void onError(int id, Exception ex) {
                    Log.e("실패", "Unable to download the file.", ex);
                }
            });

            file.delete();
        }
    }

}
