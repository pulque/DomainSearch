package com.lizheblogs.domainsearch.data.remote;

import com.lizheblogs.domainsearch.bean.WhoisInfo;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.common.BaseRepository;
import com.lizheblogs.domainsearch.util.EncodeUtils;
import com.lizheblogs.domainsearch.util.ShaHmac1;
import com.lizheblogs.domainsearch.util.Utils;

import java.security.InvalidKeyException;

/**
 * Created by lizhe on 2017/10/05.
 * 从网络上获取域名信息
 */

public class DomainInfo extends BaseRepository {

    private static final String tag = "DomainInfoRepository";
    //在阿里云上申请的访问控制RAM，https://ram.console.aliyun.com/#/overview
    //请替换成你自己的
    private static final String AccessKey = "********";
    private static final String Signature = "********";
    private final String infoURL = "http://domain.aliyuncs.com/?";
    private final String SEPARATOR = "&";
    private final String EQUAL_SIGN = "=";
    private final String signature = SEPARATOR + "Signature" + EQUAL_SIGN + "%s";
    private final ShaHmac1 sh;

    public static DomainInfo getInstance() {
        return new DomainInfo();
    }

    public DomainInfo() {
        super(tag);
        xStream.alias("GetWhoisInfoResponse", WhoisInfo.class);
        sh = new ShaHmac1();
    }

    public void searchDomainInfo(String domain, final BaseCallBack mCallBack) {
        String CanonicalizedQueryString =
                EncodeUtils.percentEncode("AccessKeyId") + EQUAL_SIGN + EncodeUtils.percentEncode(AccessKey)
                        + SEPARATOR + EncodeUtils.percentEncode("Action") + EQUAL_SIGN + EncodeUtils.percentEncode("GetWhoisInfo")
                        + SEPARATOR + EncodeUtils.percentEncode("DomainName") + EQUAL_SIGN + EncodeUtils.percentEncode(domain)
                        + SEPARATOR + EncodeUtils.percentEncode("SignatureMethod") + EQUAL_SIGN + EncodeUtils.percentEncode("HMAC-SHA1")
                        + SEPARATOR + EncodeUtils.percentEncode("SignatureNonce") + EQUAL_SIGN + EncodeUtils.percentEncode(Utils.getRandomNum())
                        + SEPARATOR + EncodeUtils.percentEncode("SignatureVersion") + EQUAL_SIGN + EncodeUtils.percentEncode("1.0")
                        + SEPARATOR + EncodeUtils.percentEncode("Timestamp") + EQUAL_SIGN + EncodeUtils.percentEncode(Utils.getTimestamp())
                        + SEPARATOR + EncodeUtils.percentEncode("Version") + EQUAL_SIGN + EncodeUtils.percentEncode("2016-05-11");

        String StringToSign = "GET" + SEPARATOR + EncodeUtils.percentEncode("/") + SEPARATOR + EncodeUtils.percentEncode(CanonicalizedQueryString);

        try {
            String url = infoURL + CanonicalizedQueryString + String.format(signature, sh.signString(StringToSign, Signature + SEPARATOR));
            RequestApi.RequestString(url, tag, new RequestCallBack() {
                @Override
                public void onSuccess(String mEntity) {
                    WhoisInfo mWhoisInfo = (WhoisInfo) xStream.fromXML(mEntity);
                    if (mCallBack != null)
                        mCallBack.onSuccess(mWhoisInfo);
                }

                @Override
                public void onFailure(String httpRsp) {
                    if (mCallBack != null)
                        mCallBack.onFailure(httpRsp);
                }
            });
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            mCallBack.onFailure("Make up URL failed.");
        }
    }
}
