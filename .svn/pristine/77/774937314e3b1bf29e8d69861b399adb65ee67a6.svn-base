package com.conlect.oatos.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.conlect.oatos.utils.DateUtils;

public class DateUtilsTest {

	@Test
	public void test() {
		Date date1 = DateUtils.parse("2011-03-30 22:22:22",
				"yyyy-MM-dd HH:mm:ss");
		Date date2 = DateUtils.parse("2011-10-12 22:22:22",
				"yyyy-MM-dd HH:mm:ss");
		System.out.println("相差："
				+ DateUtils.getDiffYearsAndMonths(date1, date2));
		// System.out.println("相差：" + getDiffDays(date1, date2));

		System.out.println(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		System.out.println("获得指定日期的上月最后一天的日期"
				+ DateUtils.format(DateUtils.getPreviousMonthEnd(date1),
						"yyyy-MM-dd"));
		System.out.println("获得指定日期的上月第一天的日期"
				+ DateUtils.format(DateUtils.getPreviousMonthFirst(date1),
						"yyyy-MM-dd"));

		System.out.println("获得指定日期第一天的日期"
				+ DateUtils.format(DateUtils.getMonthEnd(date1), "yyyy-MM-dd"));
		System.out
				.println("获得指定日期最后一天的日期"
						+ DateUtils.format(DateUtils.getMonthFirst(date1),
								"yyyy-MM-dd"));

		System.out.println("获得指定日期上个月的日期"
				+ DateUtils.format(DateUtils.getNextMonthDate(date1),
						"yyyy-MM-dd"));
		System.out.println("获得指定日期上个月的日期"
				+ DateUtils.format(DateUtils.getPreviousMonthDate(date1),
						"yyyy-MM-dd"));

		System.out.println("获得指定日期2年后的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.YEAR, 2),
						"yyyy-MM-dd"));
		System.out.println("获得指定日期2年前的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.YEAR, -2),
						"yyyy-MM-dd"));

		System.out.println("获得指定日期15月后的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.MONTH, 15),
						"yyyy-MM-dd"));
		System.out.println("获得指定日期6月前的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.MONTH, -6),
						"yyyy-MM-dd"));

		System.out.println("获得指定日期40天后的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.DATE, 40),
						"yyyy-MM-dd"));
		System.out.println("获得指定日期10天前的日期"
				+ DateUtils.format(
						DateUtils.getIntervalDate(date1, Calendar.DATE, -10),
						"yyyy-MM-dd"));
	}
}
