package com.example.perfume.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.perfume.R;
import com.example.perfume.object.Product;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExpandablePriceAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<Product>  mProduct;

    public ExpandablePriceAdapter(Context context, ArrayList<Product> product){
        mContext = context;
        mProduct = product;
    }

    @Override
    public int getGroupCount() {
        return mProduct.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mProduct.get(groupPosition).shops.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mProduct.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mProduct.get(groupPosition).shops.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = getParentGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(R.id.text);
        ImageButton arrow = (ImageButton)view.findViewById(R.id.arrow);
        text.setText("최저가 비교하기");
        arrow.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.group_indicator));
        return view;
    }

    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_parent, null);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }
        TextView price_shop_name = (TextView)view.findViewById(R.id.price_shop_name);
        price_shop_name.setText(mProduct.get(groupPosition).shops.get(childPosition));
        TextView price_name = (TextView)view.findViewById(R.id.price_name);
        price_name.setText(mProduct.get(groupPosition).prices.get(childPosition));
        return view;
    }

    private View getChildGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_child, null);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }
}
