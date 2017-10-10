package com.lizheblogs.domainsearch.common;

import com.lizheblogs.domainsearch.data.remote.RequestApi;
import com.lizheblogs.domainsearch.util.SubXStream;

/**
 * Created by lizhe on 2017/10/09.
 * 获取指定资源基类，每一个实现类对应一个接口。
 * 实现类在请求的时候要带上tag
 */

public abstract class BaseRepository {

    private String tag = "BaseRepository";

    protected SubXStream xStream;

    public BaseRepository(String tag) {
        setTag(tag);
        xStream = new SubXStream();
    }

    public void cancelRequest() {
        RequestApi.cancelAllByTag(tag);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
