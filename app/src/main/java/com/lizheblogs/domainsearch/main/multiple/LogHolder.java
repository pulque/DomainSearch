package com.lizheblogs.domainsearch.main.multiple;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.common.adapter.BaseHolder;

import java.util.List;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.main.multiple
 */

public class LogHolder extends BaseHolder<LogBean> {

    private TextView text1;
    private TextView text2;

    public LogHolder(Context context, List<LogBean> mLists) {
        super(context, mLists);
    }

    @Override
    public View getInflateView(Context context) {
        View view = View.inflate(context, android.R.layout.simple_list_item_2, null);
        text1 = (TextView) view.findViewById(android.R.id.text1);
        text2 = (TextView) view.findViewById(android.R.id.text2);
        text2.setTextSize(12);
        return view;
    }

    @Override
    public void bindData(int position) {
        LogBean logBean = mLists.get(position);
        text1.setText(logBean.key);
        text2.setText(logBean.value);
    }
}
