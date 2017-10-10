package com.lizheblogs.domainsearch.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.bean
 * <p>
 * <?xml version='1.0' encoding='UTF-8'?>
 * <Error>
 * <RequestId>F0CE2377-AFFA-46ED-B83C-76C58FC62F04</RequestId>
 * <HostId>domain.aliyuncs.com</HostId>
 * <Code>WhoisInfoNotExist</Code>
 * <Message>The whois info of this domain name does not exist.</Message>
 * </Error>
 */

public class WhoisInfoError {

    @XStreamAlias("RequestId")
    private String RequestId;
    @XStreamAlias("HostId")
    private String HostId;
    @XStreamAlias("Code")
    private String Code;
    @XStreamAlias("Message")
    private String Message;

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getHostId() {
        return HostId;
    }

    public void setHostId(String hostId) {
        HostId = hostId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
