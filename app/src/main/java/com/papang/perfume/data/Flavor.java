package com.papang.perfume.data;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.papang.perfume.R;

import java.util.ArrayList;

public class Flavor {
    public ArrayList<Drawable> flavors;
    public ArrayList<Drawable> concentrations;

    public Flavor(Context mContext){
        flavors = new ArrayList<>();
        concentrations = new ArrayList<>();

        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_aldehyde));    //알데하이드 1번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_animal));    //애니멀릭 2번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_aromatic));    //아로마틱 3번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_balsam));      //발삼 4번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_chypre));      //시프레 5번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_citrus));      //시트러스 6번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_green));       //그린 7번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_floral));      //플로럴 8번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_fruity));      //프루티 9번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_spicy));       //스파이시 10번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_woody));       //우디 11번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_acquatic));     //아쿠아틱 12번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_nutty));       //너티 13번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_leather));     //레더 14번
        flavors.add(mContext.getResources().getDrawable(R.mipmap.product_smoky));     //스모키 15번


        concentrations.add(mContext.getResources().getDrawable(R.mipmap.product_ode_c));
        concentrations.add(mContext.getResources().getDrawable(R.mipmap.product_ode_d));
        concentrations.add(mContext.getResources().getDrawable(R.mipmap.product_ode_p));
        concentrations.add(mContext.getResources().getDrawable(R.mipmap.product_ode_pp));
    }
}
