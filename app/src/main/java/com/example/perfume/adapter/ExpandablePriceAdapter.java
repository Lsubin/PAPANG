package com.example.perfume.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.perfume.R;
import com.example.perfume.object.Product;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExpandablePriceAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<Product> mProduct;

    public ExpandablePriceAdapter(Context context, ArrayList<Product> product) {
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
        if (convertView == null) {
            view = getParentGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.text);
        ImageView arrow = (ImageView) view.findViewById(R.id.arrow);
        text.setText("최저가 비교하기");
        arrow.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.group_indicator));
        return view;
    }

    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_parent, null);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }
        TextView price_shop_name = (TextView) view.findViewById(R.id.price_shop_name);
        price_shop_name.setText(mProduct.get(groupPosition).shops.get(childPosition));
        TextView price_name = (TextView) view.findViewById(R.id.price_name);
        price_name.setText(mProduct.get(groupPosition).prices.get(childPosition));

        // 맨 처음에만 최저가 태그 보이게
        TextView price_lower_tag = (TextView) view.findViewById(R.id.price_lower_tag);
        if (childPosition == 0) {
            price_lower_tag.setVisibility(View.VISIBLE);
        }

        // 마지막 아이템 구분선 사라지고 멘트 나오게 하기!
        View line = (View) view.findViewById(R.id.line);
        TextView text_comment = (TextView) view.findViewById(R.id.text_comment);
        if (isLastChild) {
            line.setVisibility(View.GONE);
            text_comment.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private View getChildGenericView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public void setListViewHeight(ExpandableListView listView,
                                  int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
