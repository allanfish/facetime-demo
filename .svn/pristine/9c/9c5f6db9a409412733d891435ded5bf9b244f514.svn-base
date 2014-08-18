package com.javase.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

	public static void main(String args[]) {

		//使用系统默认的格式  
		System.out.println("----------------------------");
		DecimalFormat myformat1 = new DecimalFormat("###,###.0000");
		System.out.println(myformat1.format(111111123456.12));
		System.out.println(myformat1.format(3300.10));
		Locale.setDefault(Locale.US);

		System.out.println("----------------------------");
		//使用美国的格式  
		DecimalFormat myformat2 = new DecimalFormat("###,###.0000");
		System.out.println(myformat2.format(111111123456.12456));
		System.out.println(myformat2.format(3300.10));

		//----------also use applypattern------------------------//  
		System.out.println("----------------------------");
		DecimalFormat myformat3 = new DecimalFormat();
		myformat3.applyPattern("##,###.000");
		System.out.println(myformat3.format(11112345.12345));

		//-----------------控制指数输出---------------------------//  
		System.out.println("----------------------------");
		DecimalFormat myformat4 = new DecimalFormat();
		DecimalFormat myformat6 = new DecimalFormat("0.0E0");
		myformat4.applyPattern("0.000E0000");
		System.out.println(myformat4.format(10000));
		System.out.println(myformat6.format(10000));
		System.out.println(myformat4.format(12345678.345));
		System.out.println(myformat6.format(12345678.345));

		//------------------百分数的输出---------------------------//  

		/*  DecimalFormat是NumberFormat的一个子类,其实例被指定为特定的地区。因此，你可以使用NumberFormat.getInstance  
		指定一个地区，然后将结构强制转换为一个DecimalFormat对象。文档中提到这个技术可以在大多情况下适用，但是你需要用 
		try/catch 块包围强制转换以防转换不能正常工作 (大概在非常不明显得情况下使用一个奇异的地区)。    */

		System.out.println("----------------------------");
		DecimalFormat myformat5 = null;
		try {
			myformat5 = (DecimalFormat) NumberFormat.getPercentInstance();
		} catch (ClassCastException e) {
			System.err.println(e);
		}
		myformat5.applyPattern("00.0000%");
		System.out.println(myformat5.format(0.34567));
		System.out.println(myformat5.format(1.34567));

		System.out.println("----------------------------");
		DecimalFormat myformat7 = new DecimalFormat("00.0000%");
		System.out.println(myformat7.format(0.34567));
		System.out.println(myformat7.format(1.34567));
	}
}
