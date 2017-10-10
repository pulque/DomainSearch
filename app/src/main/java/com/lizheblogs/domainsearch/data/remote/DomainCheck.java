package com.lizheblogs.domainsearch.data.remote;

import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.common.BaseRepository;

/**
 * Created by lizhe on 2017/10/05.
 * 从网络上获取此域名是否可以注册
 */

public class DomainCheck extends BaseRepository {

    private static final String tag = "DomainCheckRepository";

    private static final String rootURL = "http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=";

    public static DomainCheck getInstance() {
        return new DomainCheck();
    }

    public DomainCheck() {
        super(tag);
        xStream.alias("property", Property.class);
    }

    public void searchDomain(String domain, final BaseCallBack mCallBack) {
        RequestApi.RequestString(rootURL + domain, tag, new RequestCallBack() {
            @Override
            public void onSuccess(String mEntity) {
                Property property = (Property) xStream.fromXML(mEntity);
                if (mCallBack != null)
                    mCallBack.onSuccess(property);
            }

            @Override
            public void onFailure(String httpRsp) {
                if (mCallBack != null)
                    mCallBack.onFailure(httpRsp);
            }
        });
    }
}
