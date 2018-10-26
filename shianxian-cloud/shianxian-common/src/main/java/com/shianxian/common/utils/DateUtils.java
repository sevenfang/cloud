package com.shianxian.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期Util类
 */
@Slf4j
public class DateUtils {


    public static SimpleDateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    public static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 获取时间戳
     */
    public static String getTimestamp() {
        return yyyyMMddHHmmss.format(new Date());
    }


    /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static String yyyyMMddHHmmssFormat(Date date) {
        return yyyyMMddHHmmssFormat.format(date);
    }


    /**
     * 获取时间字符串
     *
     * @return
     */
    public static Date yyyyMMddHHmmssParse(String date) {
        try {
            return yyyyMMddHHmmssFormat.parse(date);
        } catch (ParseException e) {
            log.error("格式化时间失败！格式：yyyyMMddHHmmss", e);
        }
        return null;
    }


    /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static String yyyyMMddFormat(Date date) {
        return yyyyMMddFormat.format(date);
    }


   /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static Date yyyyMMddParse(String date) {
        try {
            return yyyyMMddFormat.parse(date);
        } catch (ParseException e) {
            log.error("格式化时间失败！格式：yyyyMMdd", e);
        }
        return null;
    }


}
