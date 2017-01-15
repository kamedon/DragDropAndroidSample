package com.kamedon.dragdropandroidsample.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by h_kamei on 2015/11/18.
 */
public class DragItemAdapter extends BaseAdapter {
    private final List<Integer> mIconList;
    private final LayoutInflater mInflater;

    public DragItemAdapter(Context context, List<Integer> list) {
        mInflater = LayoutInflater.from(context);
        mIconList = list;
    }

    @Override
    public int getCount() {
        return mIconList.size();
    }

    @Override
    public Integer getItem(int position) {
        return mIconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView v = new ImageView(parent.getContext());
        Integer icon = getItem(position);
        v.setImageResource(icon);
        return v;
    }
}
