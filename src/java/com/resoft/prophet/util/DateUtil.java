/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.util;

import org.apache.log4j.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * 日期工具类。
 * 
 * @author 伍军军
 */
public class DateUtil {
	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static String DEFAULT_DATE_FORMAT = FORMAT_YYYY_MM_DD;

	private static Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * 当前时间转换为字符串。<br/>
	 * 
	 */
	public static String today2String() {
		return date2String(new Date(), DEFAULT_DATE_FORMAT);
	}

	/**
	 * 当天转换为字符串。<br/>
	 * 
	 * @param format 格式类型
	 * 
	 * @return String
	 */
	public static String today2String(String format) {
		return date2String(new Date(), format);
	}

	/**
	 * 日期转换为字符串。<br/>
	 * 
	 * @param date 日期
	 * 
	 * @return String
	 */
	public static String date2String(Date date) {
		return date2String(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 日期转换为字符串。<br/>
	 * 
	 * @param date 日期
	 * @param format 转换格式
	 * 
	 * @throws String
	 */
	public static String date2String(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 字符串转换为日期。<br/>
	 * 
	 * @param dateStr 日期字符串
	 * 
	 * @return Date
	 */
	public static Date string2Date(String dateStr) {
		return string2Date(dateStr, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 字符串转换为日期。<br/>
	 * 
	 * @param dateStr 日期字符串
	 * @param format 转换格式字符串
	 * 
	 * @return Date
	 */
	public static Date string2Date(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			log.error("转换日期发生错误：日期字符串(" + dateStr + ")和日期格式(" + format + ")不匹配！");
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 调整日期。<br/>
	 * 
	 * @param date 日期
	 * @param amountTime 调整时间
	 * 
	 * @return Date
	 */
	public static Date adjust(Date date, long amountTime) {
		if (date == null) {
			log.error("调整日期发生错误：传入日期为空！");
			return null;
		}
		long newTime = date.getTime() + amountTime;
		return new Date(newTime);
	}
}
