package com.example.perfume.main.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.perfume.R;
import com.example.perfume.main.wish.WishProudctFragment;
import com.example.perfume.main.wish.WishThemeFragment;

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
