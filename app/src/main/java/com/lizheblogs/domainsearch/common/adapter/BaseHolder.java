package com.lizheblogs.domainsearch.common.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.common.adapter
 */

public abstract class BaseHolder<T> {
    public View holderView;
    public List<T> mLists;

    public BaseHolder(Context context, List<T> mLists) {
        this.mLists = mLists;
        holderView = getInflateView(context);
    }

    public abstract View getInflateView(Context context);

    public abstract void bindData(int position);

}
