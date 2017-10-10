package com.lizheblogs.domainsearch.data;

import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.bean.WhoisInfo;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.data.local.LocalDomain;
import com.lizheblogs.domainsearch.data.local.LocalDomainInfo;
import com.lizheblogs.domainsearch.data.remote.DomainInfo;

/**
 * Created by lizhe on 2017/10/05.
 * 获取域名信息
 */

public class DomainInfoRepository {

    public static final String DOMAIN_NAME_IS_NOT_AVAILABLE = "211 : Domain name is not available";

    public static DomainInfoRepository getInstance() {
        return new DomainInfoRepository();
    }

    public void searchDomainInfo(String domain, final BaseCallBack mCallBack) {
        WhoisInfo whoisInfo = LocalDomainInfo.getDomainWhois(domain);
        if (whoisInfo != null) {
            mCallBack.onSuccess(whoisInfo);
        } else {
            DomainInfo.getInstance().searchDomainInfo(domain, new BaseCallBack<WhoisInfo>() {
                @Override
                public void onSuccess(WhoisInfo mEntity) {
                    mCallBack.onSuccess(mEntity);
                    LocalDomainInfo.cacheLocal(mEntity);
                    LocalDomain.cacheLocal(new Property(mEntity.getDomainName(), DOMAIN_NAME_IS_NOT_AVAILABLE));
                }

                @Override
                public void onFailure(String httpRsp) {
                    mCallBack.onFailure(httpRsp);
                }
            });
        }
    }

    public void cancelRequest() {
        DomainInfo.getInstance().cancelRequest();
    }
}
