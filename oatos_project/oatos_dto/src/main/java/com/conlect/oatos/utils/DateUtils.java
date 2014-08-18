package com.conlect.oatos.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * 日期处理类
 */

public class DateUtils {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final String DATE_FORMAT_STR = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT_STR2 = "yyyyMMddHHmmss";
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");
	public static final String TIME_FORMAT_STR = "HH:mm:ss";
	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final String TIMESTAMP_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public static final Date CURRENT_DATE = date2date(new Date(),
			DATE_FORMAT_STR);
	public static final Date CURRENT_TIMESTAMP = date2date(new Date(),
			TIMESTAMP_FORMAT_STR);

	private static long MILLIS_ONE_DATE = 1000 * 3600 * 24;

	/**
	 * 在给定的时间上加上指定的小时
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);

		return cal.getTime();
	}

	/**
	 * 日期增加-按月增加
	 * 
	 * @param date
	 * @param month
	 */
	public static Date addMonths(Date date, int month) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 日期增加-按年增加
	 * 
	 * @param date
	 * @param year
	 */
	public static Date addYears(Date date, int year) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	private static boolean check(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		if (date1.after(date2)) {
			return false;
		}
		return true;

	}

	/**
	 * 将给定的时间按照指定的模式转换成时间
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date date2date(Date date, String pattern) {
		if (date == null || !StringUtils.isValid(pattern)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = format.parse(format.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	/**
	 * 将时间转换成指定模式的时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2str(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 返回指定日期时间的详细字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date).trim();
	}

	/**
	 * 日期增加-按日增加
	 * 
	 * @param date
	 * @param days
	 * @return java.util.Date
	 */
	public static Date dayAdd(Date date, int days) {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	public static final int dayCountBetween(Date early, Date late) {
		// (24 * 60 * 60 * 1000 = 86400000)
		int diffDateNum = (int) ((late.getTime() - early.getTime()) / 86400000);
		return diffDateNum;
	}

	/**
	 * 返回该时间段的天数
	 * 
	 * @param early
	 * @param late
	 */
	public static final int daysBetween(Calendar early, Calendar late) {
		return (int) (toJulian(late) - toJulian(early));
	}

	/**
	 * 返回该时间段的天数
	 * 
	 * @param early
	 *            the "first date"
	 * @param late
	 *            the "second date"
	 * @return the days between the two dates
	 */
	public static final int daysBetween(Date early, Date late) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);
		return daysBetween(c1, c2);
	}

	// 格式化日期
	public final static Date decomposerDate(Date date, String deco)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(deco);
		String sDate = df.format(date);
		return df.parse(sDate);

	}

	/**
	 * 按星期增加,获取该周的第一天(周日)
	 * 
	 * @param date
	 * @return java.util.Date
	 */
	public static final Date firstDateAfterAddWeeks(Date early, int weeks) {
		Date firstDate = getThisweekFirst(early);
		firstDate = dayAdd(firstDate, weeks * 7);
		return firstDate;
	}

	/**
	 * <p>
	 * Formats a date/time into a specific pattern.
	 * </p>
	 * 
	 * @param date
	 *            the date to format
	 * @param pattern
	 *            the pattern to use to format the date
	 * @return the formatted date
	 */
	public static String format(Date date, String pattern) {
		return format(date, pattern, null);
	}

	/**
	 * <p>
	 * Formats a date/time into a specific pattern in a time zone and locale.
	 * </p>
	 * 
	 * @param date
	 *            the date to format
	 * @param pattern
	 *            the pattern to use to format the date
	 * @param locale
	 *            the locale to use, may be <code>null</code>
	 * @return the formatted date
	 */
	public static String format(Date date, String pattern, Locale locale) {
		if (locale == null) {
			locale = Locale.getDefault();
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
		return df.format(date);
	}

	/**
	 * <p>
	 * Formats a date/time into a specific pattern.
	 * </p>
	 * 
	 * @param millis
	 *            the date to format expressed in milliseconds
	 * @param pattern
	 *            the pattern to use to format the date
	 * @return the formatted date
	 */
	public static String format(long millis, String pattern) {
		return format(new Date(millis), pattern, null);
	}

	/**
	 * <p>
	 * Formats a date/time into a specific pattern in a time zone and locale.
	 * </p>
	 * 
	 * @param millis
	 *            the date to format expressed in milliseconds
	 * @param pattern
	 *            the pattern to use to format the date
	 * @param locale
	 *            the locale to use, may be <code>null</code>
	 * @return the formatted date
	 */
	public static String format(long millis, String pattern, Locale locale) {
		return format(new Date(millis), pattern, locale);
	}

	/**
	 * 返回本周，本月，本年时间范围的开始，结束日期，日期格式：yyyy-MM-dd
	 * 
	 * @param selType
	 *            1:本周 2：本月 3：本年
	 * @return 开始，结束日期数组
	 */
	public static final Date[] getDateRange(int selType) {
		String[] dateStrArray = getDateStrRange(selType);
		try {
			return new Date[] { DATE_FORMAT.parse(dateStrArray[0]),
					DATE_FORMAT.parse(dateStrArray[1]) };
		} catch (ParseException e) {
			e.printStackTrace();
			throw new AssertionError("date string array is error.");
		}
	}

	/**
	 * 返回YYYY-MM-DD模式的时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		return DATE_FORMAT.format(date).trim();
	}

	public static String getDateStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();
	}

	/**
	 * @return 返回日期格式为YYMMDD的字符串.
	 */
	public static final String getDateStrOfYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		return format.format(new Date());
	}

	/**
	 * 返回本周，本月，本年时间范围的开始，结束日期，日期格式：yyyy-MM-dd
	 * 
	 * @param selType
	 *            1:本周 2：本月 3：本年
	 * @return 开始，结束日期字符数组
	 */
	public static final String[] getDateStrRange(int selType) {
		String startDate = null, endDate = null;

		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);
		int month = cd.get(Calendar.MONTH) + 1;
		Calendar cdTmp = Calendar.getInstance();
		int i;
		switch (selType) {
		case 1: // 本周
			i = cd.get(Calendar.DAY_OF_WEEK) - 1;
			cdTmp.setTime(new Date(cd.getTime().getTime() - i * 3600 * 24
					* 1000));
			startDate = cdTmp.get(Calendar.YEAR) + "-"
					+ (cdTmp.get(Calendar.MONTH) + 1) + "-"
					+ cdTmp.get(Calendar.DAY_OF_MONTH);
			i = 7 - cd.get(Calendar.DAY_OF_WEEK);
			cdTmp.setTime(new Date(cd.getTime().getTime() + i * 3600 * 24
					* 1000));
			endDate = cdTmp.get(Calendar.YEAR) + "-"
					+ (cdTmp.get(Calendar.MONTH) + 1) + "-"
					+ cdTmp.get(Calendar.DAY_OF_MONTH);
			break;
		case 2: // 本月
			startDate = year + "-" + month + "-01";
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				endDate = year + "-" + month + "-31";
				break;
			case 2:
				if (isLeapYear(year)) {
					endDate = year + "-" + month + "-29";
				} else {
					endDate = year + "-" + month + "-28";
				}
				break;
			default:
				endDate = year + "-" + month + "-30";
			}
			break;
		case 3: // 本年
			startDate = year + "-01-01";
			endDate = year + "-12-31";
			break;
		default:
			startDate = "2000-01-01";
			endDate = "2100-01-01";
		} // switch
		return new String[] { startDate, endDate };
	}

	/**
	 * 从指定的时间中获得详细的日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date).trim();
	}

	/**
	 * 功能：根据当前日期构造形如:YYYYMMDD_HHSSMMNNN日期数据，供ESB报文封装使用
	 * 
	 * @return string[0] YYYYMMDD string[1] HHSSMMNNN
	 */
	public static final String[] getDateYYYYMMDD_HHSSMMNNN() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmssms");
		return sdf.format(new Date()).split("\\s+");
	}

	/**
	 * 从日期中取得日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		String str_date = getDateStr(date);
		String str[] = StringUtils.split(str_date, "-");
		String day = str[2];
		int d = Integer.parseInt(day);
		return d;
	}

	/**
	 * 两个日期的相差天数
	 */
	public static int getDiffDays(Date date1, Date date2) {
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		long time1 = c.getTimeInMillis();

		c.setTime(date2);
		long time2 = c.getTimeInMillis();
		long between_days = (time2 - time1) / MILLIS_ONE_DATE;

		return Integer.parseInt(String.valueOf(between_days));

	}

	/**
	 * 两个日期的相差月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDiffMonths(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		if (date2 == null) {
			c2.setTime(new java.util.Date());
		} else {
			c2.setTime(date2);
		}
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);

		return (year2 - year1) * 12 + month2 - month1 + 1;
	}

	/**
	 * 计算两个日期的相差年数
	 */
	public static int getDiffYears(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		if (date2 == null) {
			c2.setTime(new java.util.Date());
		} else {
			c2.setTime(date2);
		}
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);

		return year2 - year1 + 1;
	}

	/**
	 * 计算两个日期的相差多少年多少月
	 */
	public static String getDiffYearsAndMonths(Date date1, Date date2) {
		if (!check(date1, date2)) {

		}

		String yearmonthStr = "";
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		if (date2 == null) {
			c2.setTime(new java.util.Date());
		} else {
			c2.setTime(date2);
		}

		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);

		int yearmonth = (year2 - year1) * 12 + month2 - month1 + 1;
		int year = yearmonth / 12;
		int month = yearmonth % 12;

		if (year > 0) {
			yearmonthStr = year + "年";
		}
		if (month > 0) {
			yearmonthStr = yearmonthStr + month + "个月";
		}
		return yearmonthStr;
	}

	/**
	 * 获得指定日期的指定类型(年/月/日)间隔日期<br/>
	 * 如：指定日期2年后的日期,15个月后的日期,35天后的日期
	 * 
	 * @param appointDate
	 * 
	 * @param type
	 *            共三种类型年、月、日(Calendar.YEAR/Calendar.MONTH/Calendar.DATE)
	 * @param number
	 *            如果是负数则为向前间隔,如果是正数则为后
	 * @return
	 */
	public static Date getIntervalDate(Date appointDate, int type, int number) {
		Calendar intervalDate = Calendar.getInstance();
		intervalDate.setTime(appointDate);
		if (type == Calendar.YEAR) {
			intervalDate.add(Calendar.YEAR, number);
		} else if (type == Calendar.MONTH) {
			intervalDate.add(Calendar.MONTH, number);
		} else {
			intervalDate.add(Calendar.DATE, number);
		}
		return intervalDate.getTime();
	}

	/**
	 * 返回两个时间差,返回格式是指定的秒/分/时/天
	 * 
	 * @param flag
	 *            s/m/h/d表示秒/分/时/天
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static final long getLongBetween(String flag, String time1,
			String time2) {
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time1);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(time2);
			long ltime = date1.getTime() - date2.getTime() < 0 ? date2
					.getTime() - date1.getTime() : date1.getTime()
					- date2.getTime();
			if (flag.equals("s")) {
				return ltime / 1000;// 返回秒
			} else if (flag.equals("m")) {
				return ltime / 60000;// 返回分钟
			} else if (flag.equals("h")) {
				return ltime / 3600000;// 返回小时
			} else if (flag.equals("d")) {
				return ltime / 86400000;// 返回天数
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 从日期中取得月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		String str_date = getDateStr(date);
		String str[] = StringUtils.split(str_date, "-");
		String month = str[1];
		int m = Integer.parseInt(month);
		return m;
	}

	/**
	 * 获得指定日期最后一天的日期
	 * 
	 * @return
	 */
	public static Date getMonthEnd(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 减一个月
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH) + 1);// 减一个月
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));// 把日期设置为当月第一天
		lastDate.add(Calendar.DATE, -1);
		return lastDate.getTime();
	}

	/**
	 * 获得指定日期第一天的日期
	 * 
	 * @return
	 */
	public static Date getMonthFirst(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);//
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH));
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));
		return lastDate.getTime();
	}

	/**
	 * 获得指定日期下个月的日期
	 * 
	 * @return
	 */
	public static Date getNextMonthDate(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, appointCalendar.get(Calendar.DATE));//
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH) + 1);
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));
		return lastDate.getTime();
	}

	/**
	 * 获得指定日期上个月的日期
	 * 
	 * @return
	 */
	public static Date getPreviousMonthDate(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, appointCalendar.get(Calendar.DATE));//
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH) - 1);
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));
		return lastDate.getTime();
	}

	/**
	 * 获得指定日期的上月最后一天的日期
	 * 
	 * @return
	 */
	public static Date getPreviousMonthEnd(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 减一个月
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH));// 减一个月
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));// 把日期设置为当月第一天
		lastDate.add(Calendar.DATE, -1);
		return lastDate.getTime();
	}

	/**
	 * 获得指定日期的上月第一天的日期
	 * 
	 * @return
	 */
	public static Date getPreviousMonthFirst(Date appointDate) {
		Calendar appointCalendar = Calendar.getInstance();
		appointCalendar.setTime(appointDate);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);//
		lastDate.set(Calendar.MONTH, appointCalendar.get(Calendar.MONTH) - 1);
		lastDate.set(Calendar.YEAR, appointCalendar.get(Calendar.YEAR));
		return lastDate.getTime();
	}

	// 截取字符，从第一个开始，i为结束
	public static final String getString(int i, String str) {
		if (str.length() >= i) {
			return str.substring(0, i);
		} else {
			return str;
		}

	}

	/**
	 * 获取本周的第一天(周日)
	 * 
	 * @param date
	 * @return java.util.Date
	 */
	public static final Date getThisweekFirst(Date early) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(early);
		int week = c1.get(Calendar.DAY_OF_WEEK);
		int firstw = -1 * (week - 1);
		Date weekFirst = dayAdd(early, firstw);
		return weekFirst;
	}

	/**
	 * 返回指定时间戳的详细字符串
	 * 
	 * @param tsp
	 * @return
	 */
	public static String getTimeStr(Timestamp tsp) {
		if (tsp == null) {
			return "";
		}
		return dateToStr(new Date(tsp.getTime()));
	}

	/**
	 * 从日期中取得年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		String str_date = getDateStr(date);
		String str[] = StringUtils.split(str_date, "-");
		String year = str[0];
		int y = Integer.parseInt(year);
		return y;
	}

	/**
	 * 判断是否闰年
	 * 
	 * @param y
	 *            年份
	 * @return true or false
	 */
	public static final boolean isLeapYear(int y) {
		if (y % 4 == 0) {
			if (y % 100 == 0) {
				if (y % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查日期是否满足"yyyy-MM-dd"的格式，且toDate不小于fromDate. 如果满足,
	 * 则将格式化后的日期字符串保存到dates数组中.
	 * 
	 * @param fromDate
	 *            开始日期
	 * @param toDate
	 *            结束日期
	 * @param dates
	 *            用于返回处理后的开始，结束日期
	 * @return 日期格式正确，返回true，否则false
	 */
	public static boolean isValidDates(String fromDate, String toDate,
			String[] dates) {
		if (StringUtils.isEmpty(fromDate)) {
			return false;
		}
		if (StringUtils.isEmpty(toDate)) {
			return false;
		}
		Date begin = null;
		Date end = null;
		try {
			begin = DATE_FORMAT.parse(fromDate);
			end = DATE_FORMAT.parse(toDate);
			if (begin.after(end)) {
				return false;
			}
			dates[0] = DATE_FORMAT.format(begin);
			dates[1] = DATE_FORMAT.format(end);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取两个日期间的月份间隔
	 * 
	 * @param date
	 * @param date
	 * @return int
	 */
	public static final int monthsIndays(Date early, Date late) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);
		int earlyYear = c1.get(Calendar.YEAR);
		int earlyMonth = c1.get(Calendar.MONTH);
		int lateYear = c2.get(Calendar.YEAR);
		int lateMonth = c2.get(Calendar.MONTH);
		int months = (lateYear - earlyYear) * 12 + lateMonth - earlyMonth + 1;
		return months;
	}

	public static Date now() {
		return new Date();
	}

	/**
	 * 将格式格式化成日期
	 * 
	 * @param value
	 * @param pattern
	 *            yyyy-MM-dd,yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static Date parse(String value, String pattern) {
		if (value == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern).parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("The symbol " + value + "with format "
					+ pattern + " can't be converted to a Date", e);
		}
	}

	/**
	 * 将时间字符串转换成指定模式的时间
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date str2date(String dateStr, String pattern) {
		if (!StringUtils.isValid(dateStr) || !StringUtils.isValid(pattern)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	/**
	 * 从某个模式的时间字符串转换成目标模式的时间字符串
	 * 
	 * @param dateStr
	 * @param srcPattern
	 * @param destPattern
	 * @return
	 */
	public static String str2str(String dateStr, String srcPattern,
			String destPattern) {
		if (StringUtils.isEmpty(srcPattern) || StringUtils.isEmpty(destPattern)) {
			return dateStr;
		}
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		return date2str(str2date(dateStr, srcPattern), destPattern);
	}

	/**
	 * @return YYYY-MM-DD HH:mm:ss 格式的时间
	 */
	public static Date str2timestamp(String dateStr) {
		return str2date(dateStr, TIMESTAMP_FORMAT_STR);
	}

	public static String to_char(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();
	}

	public static String to_char(Timestamp tsp, String format) {
		if (tsp == null) {
			return "";
		}
		return to_char(new Date(tsp.getTime()), format);
	}

	public static Date to_date(String date) {
		Calendar cd = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(date, "-/ :");
		if (token.hasMoreTokens()) {
			cd.set(Calendar.YEAR, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.YEAR, 1970);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MONTH, Integer.parseInt(token.nextToken()) - 1);
		} else {
			cd.set(Calendar.MONTH, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MINUTE, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MINUTE, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.SECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.SECOND, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MILLISECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MILLISECOND, 0);
		}
		return cd.getTime();
	}

	public static Timestamp to_timestamp(String date) {
		return new Timestamp(to_date(date).getTime());
	}

	/**
	 * Return a Julian date based on the input parameter. This is based from
	 * calculations found at
	 * 
	 * <pre>
	 * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
	 * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
	 * </pre>
	 * 
	 * @param c
	 *            a calendar instance
	 * @return the julian day number
	 */
	public static final float toJulian(Calendar c) {
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = C + D + E + F - 1524.5f;

		return JD;
	}

	/**
	 * Returns the weeks between two dates.
	 * 
	 * @param early
	 *            the "first date"
	 * @param late
	 *            the "second date"
	 * @return the weeks between the two dates
	 */
	public static final int weeksBetween(Date early, Date late) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);
		int days = daysBetween(c1, c2) + 1;
		int earlyweek = c1.get(Calendar.DAY_OF_WEEK);
		int lateweek = c2.get(Calendar.DAY_OF_WEEK);
		int weeks = days / 7;
		int weekst = days % 7;
		if (weekst == 0) {
			return weeks;
		} else if (lateweek >= earlyweek) {
			return weeks + 1;
		} else {
			return weeks + 2;
		}
	}
}
