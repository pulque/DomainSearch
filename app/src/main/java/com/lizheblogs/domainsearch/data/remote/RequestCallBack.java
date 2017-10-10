package com.lizheblogs.domainsearch.data.remote;

import com.lizheblogs.domainsearch.common.BaseCallBack;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.data
 */

public interface RequestCallBack extends BaseCallBack<String> {
    @Override
    void onSuccess(String mEntity);

    @Override
    void onFailure(String httpRsp);
}
