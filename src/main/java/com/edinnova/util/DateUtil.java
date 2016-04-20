package com.edinnova.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	public enum datePatern {
		ddMMyyyy("dd/MM/yyyy"),
		yyyyMMdd("yyyy/MM/dd"),
		HHmm("HH:mm"),
		ddMMyyyyHHmm("dd/MM/yyyy HH:mm"),
		ddMMyyyyHHmmss("dd/MM/yyyy HH:mm:ss");
		private String value;
		private datePatern(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	};
	
	public static Date cloneDateWithoutTime(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date addDate(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	public static Date now() {
		Calendar cal = GregorianCalendar.getInstance();
		return cal.getTime();
	}
	
	public static Integer nowYear() {
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	public static Integer nowMonth() {
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.MONTH);
	}
	
	public static Integer nowDay() {
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getYear(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static Integer getMonth(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	public static Integer getDay(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getHour(String time) {
		if(StringUtil.isNullOrEmpty(time)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			Date __time = format.parse(time);
			Calendar cal = Calendar.getInstance();
			cal.setTime(__time);
			return cal.get(Calendar.HOUR_OF_DAY);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Integer getMinute(String time) {
		if(StringUtil.isNullOrEmpty(time)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			Date __time = format.parse(time);
			Calendar cal = Calendar.getInstance();
			cal.setTime(__time);
			return cal.get(Calendar.MINUTE);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseDate(String date) {
		if(StringUtil.isNullOrEmpty(date)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date __date = format.parse(date);
			return __date;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseDate(String date, String patern) {
		if(StringUtil.isNullOrEmpty(date)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(patern);
		try {
			Date __date = format.parse(date);
			return __date;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
	
	public static String formatDate(Date date, String patern) {
		SimpleDateFormat format = new SimpleDateFormat(patern);
		return format.format(date);
	}
	
	/**
	 * get all dates between range date
	 * @param startdate
	 * @param enddate
	 * @return list all dates between startdate and enddate
	 */
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);
	    while (calendar.getTime().before(enddate)) {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	/**
	 * compare date
	 * @param d1
	 * @param d2
	 * @return -1 if d1 > d2; 0 if d1 = d2; 1 if d1 < d2
	 */
	public static Integer compareDate(Date d1, Date d2) {
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		if(t1 > t2) {
			return -1;
		} else if(t1 == t2) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * compare date without time
	 * @param d1
	 * @param d2
	 * @return -1 if d1 > d2; 0 if d1 = d2; 1 if d1 < d2
	 */
	public static Integer compareDateWithoutTime(Date d1, Date d2) {
		Calendar cal1 = GregorianCalendar.getInstance();
		cal1.setTime(d1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		
		Calendar cal2 = GregorianCalendar.getInstance();
		cal2.setTime(d2);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		
		long t1 = cal1.getTimeInMillis();
		long t2 = cal2.getTimeInMillis();
		if(t1 > t2) {
			return -1;
		} else if(t1 == t2) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public static Date getDateWithowTime(Date d) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String getPostfixString() {
		Calendar cal = GregorianCalendar.getInstance();
		String result = cal.get(Calendar.YEAR) + "" + cal.get(Calendar.MONTH) + "" + cal.get(Calendar.DATE) + "" + cal.get(Calendar.HOUR_OF_DAY) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND) + "" + cal.get(Calendar.MILLISECOND);
		return result;
	}
	
	public static Date getDate(Long time) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.getTime();
	}
	
	public static Integer getMinutesInDates(Date fromDate, Date toDate) {
		if(fromDate == null || toDate == null) {
			return 0;
		}
		Long time = toDate.getTime() - fromDate.getTime();
		if(time <= 0) {
			return 0;
		}
		Integer __time = (int) (time / 60000);
		return __time;
	}
	
	public static Date getLastOfDay(Date d) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
}
