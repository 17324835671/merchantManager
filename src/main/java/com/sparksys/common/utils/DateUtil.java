package com.sparksys.common.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;

	public static Calendar calendar = Calendar.getInstance();

	/**
	 * 
	 * @return yyyy-mm-dd
	 *  2012-12-25
	 */
	public static String getDate() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

	/**
	 * @param format
	 * @return 
	 * yyyy年MM月dd HH:mm 
	 * MM-dd HH:mm 2012-12-25
	 * 
	 */
	public static String getDate(String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm 
	 * 2012-12-29 23:47
	 */
	public static String getDateAndMinute() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return
	 *  yyyy-MM-dd HH:mm:ss 
	 *  2012-12-29 23:47:36
	 */
	public static String getFullDate() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(calendar.getTime());
	}

	/**
	 * 距离今天多久
	 * @param date
	 * @return 
	 * 
	 */
	public static String fromToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
					+ "分钟前";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_DAY * 3)
			return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			return month + "个月" + day + "天前"
					+ calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		} else {
			long year = ago / ONE_YEAR;
			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
			return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
					+ "日";
		}

	}

	public static String getDateAsYYMMDD(Date date) {
		DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		return format.format(date);
	}

	/**
	 * 距离截止日期还有多长时间
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return "只剩下" + remain / ONE_MINUTE + "分钟";
		else if (remain <= ONE_DAY)
			return "只剩下" + remain / ONE_HOUR + "小时"
					+ (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
		}

	}

	/**
	 * 距离今天的绝对时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toToday(Date date) {
		long time = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
					% ONE_HOUR / ONE_MINUTE + "分";
		else if (ago <= ONE_DAY * 3) {
			long hour = ago - ONE_DAY * 2;
			return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
					+ "分";
		} else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			long hour = ago % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return day + "天前" + hour + "点" + minute + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return month + "个月" + day + "天" + hour + "点" + minute + "分前";
		} else {
			long year = ago / ONE_YEAR;
			long month = ago % ONE_YEAR / ONE_MONTH;
			long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
			return year + "年前" + month + "月" + day + "天";
		}

	}

	public static String getYear() {
		return calendar.get(Calendar.YEAR) + "";
	}

	public static String getMonth() {
		int month = calendar.get(Calendar.MONTH) + 1;
		return month + "";
	}

	public static String getDay() {
		return calendar.get(Calendar.DATE) + "";
	}

	public static String get24Hour() {
		return calendar.get(Calendar.HOUR_OF_DAY) + "";
	}

	public static String getMinute() {
		return calendar.get(Calendar.MINUTE) + "";
	}

	public static String getSecond() {
		return calendar.get(Calendar.SECOND) + "";
	}

	public static String dateToString(Date date, String dateFormat) {
        String str = null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        if (date == null) {
            return "";
        } else {
            str = format.format(date);
            return str;
        }
    }
    public static String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonProperties.YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(new Date(timeStmp));
	}
    public static String dateStringFromat(Date date, String dateFormat) {
        String str = null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        if (date == null) {
            return "";
        } else {
            str = format.format(date);
            return str;
        }
    }
    public static Date stringToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat(CommonProperties.YYYY_MM_DD);
        java.sql.Date date = null;

        try {
            format.parse(str);
        } catch (ParseException arg3) {
            arg3.printStackTrace();
        }

        date = java.sql.Date.valueOf(str);
        return date;
    }
    /**
     *  获取指定日期-星期基础信息
     * @param strDate
     * @return
     */
    public static int getWeekByDateStr(String strDate){
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(5, 7));
		int day = Integer.parseInt(strDate.substring(8, 10));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		int week = 0;
		int weekIndex = c.get(Calendar.DAY_OF_WEEK);
		switch (weekIndex){
		case 1://周日
			week = 7;
			break;
		case 2://周一
			week = 1;
			break;
		case 3://周二
			week = 2;
			break;
		case 4://周三
			week = 3;
			break;
		case 5://周四
			week = 4;
			break;
		case 6://周五
			week = 5;
			break;
		case 7://周六
			week = 6;
			break;
		}
		return week;
	}
	/**
	 * 获取当前日期以及星期基础信息
	 * 
	 * @return YYYY年MM月DD日 星期X
	 * 
	 * @author simon
	 * 
	 * @timer 2018年8月9日
	 */
	public static String getFormatDateByWeek() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		String result = year + "年";
		if (month < 10)
			result += "0" + month + "月";
		else
			result += month + "月";
		if (date < 10)
			result += "0" + date + "日";
		else
			result += date + "日";
		switch (week) {
		case 2: {
			result += " 星期一";
			break;
		}
		case 3: {
			result += " 星期二";
			break;
		}
		case 4: {
			result += " 星期三";
			break;
		}
		case 5: {
			result += " 星期四";
			break;
		}
		case 6: {
			result += " 星期五";
			break;
		}
		case 7: {
			result += " 星期六";
			break;
		}
		case 1: {
			result += " 星期天";
			break;
		}
		}
		return result;
	}
    /**
     * 得到两个时间相差的天数
     * @param startDate
     * @param endDate
     * @return
     * @throws IOException
     */
    public static Long getCalculatedDays(Date startDate,Date endDate) throws IOException {
		Long days = 0L;
		try {
			Long diff = startDate.getTime()-endDate.getTime();
			days = diff/(1000*60*60*24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}
    
}
