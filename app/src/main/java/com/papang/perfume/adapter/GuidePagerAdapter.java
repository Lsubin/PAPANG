package com.papang.perfume.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.papang.perfume.R;

import java.util.ArrayList;

public class GuidePagerAdapter extends PagerAdapter {
    private ArrayList<Drawable> mData;
    private Context mContext;

    public GuidePagerAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        mData.add(mContext.getResources().getDrawable(R.mipmap.guide_book_1));
        mData.add(mContext.getResources().getDrawable(R.mipmap.guide_book_5));
        mData.add(mContext.getResources().getDrawable(R.mipmap.guide_book_6));
        mData.add(mContext.getResources().getDrawable(R.mipmap.guide_book_7));
        mData.add(mContext.getResources().getDrawable(R.mipmap.guide_book_8));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_guide_viewpager, null);

        ImageView imageView = view.findViewById(R.id.guide_image);
        imageView.setImageDrawable(mData.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View) o);
    }
}
