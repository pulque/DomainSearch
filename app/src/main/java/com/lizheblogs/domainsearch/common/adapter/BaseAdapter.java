package com.lizheblogs.domainsearch.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.common.adapter
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    protected final Context context;
    private List<T> mList;

    public BaseAdapter(Context context, List<T> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            holder = initHolder();
            convertView = holder.holderView;
            convertView.setTag(holder);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.bindData(position);
        return convertView;
    }

    public abstract BaseHolder initHolder();
}