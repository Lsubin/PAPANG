package com.papang.perfume.main.home;

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

public class MainViewPagerAdapter extends PagerAdapter {

    private ArrayList<Drawable> magazine;
    private Context mContext;

    public MainViewPagerAdapter(Context context) {
        this.mContext = context;
        magazine = new ArrayList<>();
        magazine.add(mContext.getResources().getDrawable(R.mipmap.magazine_1));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_viewpager, null);

        ImageView imageView = view.findViewById(R.id.viewpager_image);
        imageView.setImageDrawable(magazine.get(position));

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return magazine.size();
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
