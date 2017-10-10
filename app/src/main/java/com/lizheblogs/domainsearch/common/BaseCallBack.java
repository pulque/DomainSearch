package com.lizheblogs.domainsearch.common;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.data
 */

public interface BaseCallBack<T> {

    void onSuccess(T mEntity);

    void onFailure(String httpRsp);
}
