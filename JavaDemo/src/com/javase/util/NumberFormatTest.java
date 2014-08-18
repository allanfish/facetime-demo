package com.javase.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

	public static void main(String args[]) {

		//ʹ��ϵͳĬ�ϵĸ�ʽ  
		System.out.println("----------------------------");
		DecimalFormat myformat1 = new DecimalFormat("###,###.0000");
		System.out.println(myformat1.format(111111123456.12));
		System.out.println(myformat1.format(3300.10));
		Locale.setDefault(Locale.US);

		System.out.println("----------------------------");
		//ʹ�������ĸ�ʽ  
		DecimalFormat myformat2 = new DecimalFormat("###,###.0000");
		System.out.println(myformat2.format(111111123456.12456));
		System.out.println(myformat2.format(3300.10));

		//----------also use applypattern------------------------//  
		System.out.println("----------------------------");
		DecimalFormat myformat3 = new DecimalFormat();
		myformat3.applyPattern("##,###.000");
		System.out.println(myformat3.format(11112345.12345));

		//-----------------����ָ�����---------------------------//  
		System.out.println("----------------------------");
		DecimalFormat myformat4 = new DecimalFormat();
		DecimalFormat myformat6 = new DecimalFormat("0.0E0");
		myformat4.applyPattern("0.000E0000");
		System.out.println(myformat4.format(10000));
		System.out.println(myformat6.format(10000));
		System.out.println(myformat4.format(12345678.345));
		System.out.println(myformat6.format(12345678.345));

		//------------------�ٷ��������---------------------------//  

		/*  DecimalFormat��NumberFormat��һ������,��ʵ����ָ��Ϊ�ض��ĵ�������ˣ������ʹ��NumberFormat.getInstance  
		ָ��һ��������Ȼ�󽫽ṹǿ��ת��Ϊһ��DecimalFormat�����ĵ����ᵽ������������ڴ����������ã���������Ҫ�� 
		try/catch ���Χǿ��ת���Է�ת�������������� (����ڷǳ������Ե������ʹ��һ������ĵ���)��    */

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
