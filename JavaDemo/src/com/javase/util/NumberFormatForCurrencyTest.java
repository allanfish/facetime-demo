package com.javase.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatForCurrencyTest {

	public static void main(String[] args) {
		NumberFormat format1 = NumberFormat.getCurrencyInstance(Locale.CHINA);
		System.out.println(format1.format(8888.88));

		NumberFormat format2 = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println(format2.format(8888.88));
	}
}
