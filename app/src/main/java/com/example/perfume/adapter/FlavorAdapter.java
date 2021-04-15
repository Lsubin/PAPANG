package com.example.perfume.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;

import java.util.ArrayList;

public class FlavorAdapter extends RecyclerView.Adapter<Holder> {
    Context mContext;

    ArrayList<Drawable> drawables;          // 기본 이미지
    ArrayList<Integer> drawables_Num;       // 매칭 넘버

    public FlavorAdapter(Context context, ArrayList<Integer> drawables_Num){
        this.mContext = context;
        drawables = new ArrayList<>();

        // drawable 이미지들 추가
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aldehyde));    //알데하이드 1번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_animalic));    //애니멀릭 2번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aromatic));    //아로마틱 3번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_balsam));      //발삼 4번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_chypre));      //시프레 5번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_citrus));      //시트러스 6번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_green));       //그린 7번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_floral));      //플로럴 8번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_fruity));      //프루티 9번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_spicy));       //스파이시 10번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_woody));       //우디 11번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aquatic));     //아쿠아틱 12번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_nutty));       //너티 13번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_leather));     //레더 14번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_nope));        //없음 15번
        // click 이미지들 추가
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aldehyde_click));    //알데하이드 16번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_animalic_click));    //애니멀릭 17번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aromatic_click));    //아로마틱 18번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_balsam_click));      //발삼 19번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_chypre_click));      //시프레 20번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_citrus_click));      //시트러스 21번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_green_click));       //그린 22번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_floral_click));      //플로럴 23번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_fruity_click));      //프루티 24번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_spicy_click));       //스파이시 25번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_woody_click));       //우디 26번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_aquatic_click));     //아쿠아틱 27번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_nutty_click));       //너티 28번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_leather_click));     //레더 29번
        drawables.add(mContext.getResources().getDrawable(R.mipmap.flavor_nope_click));        //없음 30번

        this.drawables_Num = drawables_Num;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_flavor_type1, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override public void onBindViewHolder(@NonNull Holder holder, int position)
    {
        //Toast.makeText(mContext, String.valueOf(position), 0).show();
        holder.theme_image.setImageDrawable(drawables.get(drawables_Num.get(position)-1));
    }


    @Override
    public int getItemCount() {
        return drawables_Num.size();
    }

    // click으로 바꾸는거
    public void setDrawable(int position){
        int num = drawables_Num.get(position);
        drawables_Num.set(position, num+15);
        notifyDataSetChanged();
    }

    public void setBackDrawable(int position){
        int num = drawables_Num.get(position);
        drawables_Num.set(position, num-15);
        notifyDataSetChanged();
    }

    public int getNum(int position){
        return drawables_Num.get(position);
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

