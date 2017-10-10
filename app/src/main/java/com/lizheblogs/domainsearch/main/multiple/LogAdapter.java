package com.lizheblogs.domainsearch.main.multiple;

import android.content.Context;

import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.common.adapter.BaseAdapter;
import com.lizheblogs.domainsearch.common.adapter.BaseHolder;

import java.util.List;

/**
 * Created by LiZhe on 2017-09-30.
 * com.lizheblogs.domainsearch
 */

public class LogAdapter extends BaseAdapter {

    private List<LogBean> logBeans;

    public LogAdapter(Context context, List<LogBean> logBeans) {
        super(context, logBeans);
        this.logBeans = logBeans;
    }

    @Override
    public BaseHolder initHolder() {
        return new LogHolder(context, logBeans);
    }
}
