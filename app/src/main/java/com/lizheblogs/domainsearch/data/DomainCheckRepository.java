package com.lizheblogs.domainsearch.data;

import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.data.local.LocalDomain;
import com.lizheblogs.domainsearch.data.remote.DomainCheck;

/**
 * Created by lizhe on 2017/10/05.
 * 获取此域名是否可以注册
 */

public class DomainCheckRepository {

    public static DomainCheckRepository getInstance() {
        return new DomainCheckRepository();
    }

    public void searchDomain(String domain, final BaseCallBack mCallBack) {
        Property property = LocalDomain.getDomainProperty(domain);
        if (property != null) {
            mCallBack.onSuccess(property);
        } else {
            DomainCheck.getInstance().searchDomain(domain, new BaseCallBack<Property>() {
                @Override
                public void onSuccess(Property mEntity) {
                    mCallBack.onSuccess(mEntity);
                    LocalDomain.cacheLocal(mEntity);
                }

                @Override
                public void onFailure(String httpRsp) {
                    mCallBack.onFailure(httpRsp);
                }
            });
        }
    }

    public void cancelRequest() {
        DomainCheck.getInstance().cancelRequest();
    }
}
