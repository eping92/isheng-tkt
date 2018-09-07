package com.isheng.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * @author Administrator
 *
 */
public class DateUtil {
	
	public static String HHmm = "HH:mm";
	public static String HHmmss = "HH:mm:ss";
	public static String yyyyMM = "yyyy年MM月";
	public static String yyyyMMdd = "yyyy/MM/dd";
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyy_MM_ddHHmm = "yyyy-MM-dd HH:mm";
    public static String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	/**
	 * 获取当前时间
	 * @param pattern
	 * @return
	 */
	public static String getNowTime(String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(Calendar.getInstance().getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间戳转字符串格式时间
     * @param timestamp
     * @param pattern
     * @return
     */
	public static String toDateTime(long timestamp, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(new Date(timestamp));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 字符串时间格式转时间戳
	 * @param dateTime
	 * @return
	 */
	public static long toTimestamp(String dateTime) {
		Calendar c = new GregorianCalendar();
		try {
			DateFormat sdf = new SimpleDateFormat(yyyy_MM_ddHHmmss);
			Date date = sdf.parse(dateTime);
			c.setTime(date);
			return c.getTime().getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
	
	/**
	 * 字符串时间格式转Date类型时间
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String dateTime, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
	}
	
//	public static void main(String[] args) {
//		String dateTime = "2018-08-12 10:10:23";
//		System.out.println(toTimestamp(dateTime));
//	}

}
