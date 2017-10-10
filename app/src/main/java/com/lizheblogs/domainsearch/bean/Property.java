package com.lizheblogs.domainsearch.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by LiZhe on 2017-09-29.
 * com.lizheblogs.domainsearch
 * 接口返回信息模板
 * <p>
 * <?xml version="1.0" encoding="gb2312"?>
 * <property>
 * <returncode>200</returncode>
 * <key>lizheblogs.com</key>
 * <original>211 : Domain name is not available</original>
 * </property>
 */

public class Property {

    @XStreamAlias("returncode")
    private int returncode;
    @XStreamAlias("key")
    private String key;
    @XStreamAlias("original")
    private String original;

    public Property() {
        returncode = 200;
        key = "";
        original = "";
    }

    public Property(String key, String original) {
        returncode = 200;
        this.key = key;
        this.original = original;
    }

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "Property{" +
                "returncode=" + returncode +
                ", key='" + key + '\'' +
                ", original='" + original + '\'' +
                '}';
    }
}
