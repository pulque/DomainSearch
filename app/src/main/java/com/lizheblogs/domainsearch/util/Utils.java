package com.lizheblogs.domainsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lizhe on 2017/10/05.
 * 工具类，字符转换，获取时间等等。
 */

public class Utils {

    /**
     * 将java Date格式转成Solr支持的UTC时间
     *
     * @param date 需要转换的时间，一般为当前时间
     * @return 格式化后的时间
     */
    public static String getTimestamp(Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        String result = sdf1.format(date) + "T" + sdf2.format(date) + "Z";
        return result;
    }

    /**
     * 将java Date格式转成Timestamp支持的UTC时间
     * 当前时间转换
     * 请求的时间戳。日期格式按照 ISO8601 标准表示，并需要使用 UTC 时间。
     *
     * @return 格式化后的时间
     */
    public static String getTimestamp() {
        return getTimestamp(new Date());
    }

    /**
     * 将java Date格式转成Solr支持的UTC时间
     * 当前时间转换
     *
     * @return 格式化后的时间
     */
    public static long getMillisFromTimestamp(String timestamp) {
        timestamp = timestamp.toUpperCase().replace("T", " ").replace("Z", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return sdf.parse(timestamp).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取前一天的时间点
     *
     * @return 格式化后的时间
     */
    public static long getDayBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取时间戳
     *
     * @return 格式化后的时间
     */
    public static long getTime() {
        return new Date().getTime();
    }


    /**
     * 获取20位的随机数
     *
     * @return 字符串形式展示数字
     */
    public static String getRandomNum() {
        StringBuffer strRand = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            strRand.append(String.valueOf((int) (Math.random() * 10)));
        }
        return strRand.toString();
    }
}
