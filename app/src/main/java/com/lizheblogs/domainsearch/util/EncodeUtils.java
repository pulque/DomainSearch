package com.lizheblogs.domainsearch.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lizhe on 2017/10/05.
 * 该编码方式和一般采用的“application/x-www-form-urlencoded”
 * MIME格式编码算法(比如Java标准库中的 java.net.URLEncoder的实现) 相似, 但又有所不同。
 * 实现时, 可以先用标准库的方式进行编码, 然后把编码后的字符串中加号（+）替换成 %20、星号（*）替换成 %2A、%7E 替换回波浪号（~）。
 */

public class EncodeUtils {

    private static final String ENCODING = "UTF-8";

    public static String percentEncode(String value) {
        try {
            return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
