package com.javase.util;

import java.text.DecimalFormat;

public class NumberFormatTest2 {

	public static void main(String[] args) {

		//����һ�����ָ�ʽ�����󣬸�ʽ��ģ��Ϊ".##"��������2λС��.
		DecimalFormat a = new DecimalFormat("##.##");
		System.err.println(a.format(03.03035));
		System.err.println(a.format(03.30));
		System.err.println(a.format(03.335));
		System.err.println(a.format(03.3));
		//˵�������С�����治��2λС��Ჹ��.�μ�RoundingС��

		//����������ʱ���ú���applyPattern(String)�޸ĸ�ʽģ��
		//����2λС�����С�����治��2λС��Ჹ��
		a.applyPattern("00.00");
		System.err.println(a.format(333.3));

		//���ǧ�ֺ�
		a.applyPattern(".##\u2030");
		System.err.println(a.format(0.78934));//���ǧλ���,С������λ������ǧλ��

		//��Ӱٷֺ�
		a.applyPattern("#.##%");
		System.err.println(a.format(0.78645));

		//���ǰ���������ַ��ǵ�Ҫ�õ����������
		a.applyPattern("�����ҵ�Ǯ$###.###��Բ");
		System.err.println(a.format(2533333443.3333));

		//��ӻ��ұ�ʾ���(��ͬ�Ĺ�ң���ӵķ�Ų�һ��
		a.applyPattern("\u00A4");
		System.err.println(a.format(34));

		//��������ģ��,�ǵ�Ҫ�÷ֺŸ���
		a.applyPattern("0.0;-#.0");
		System.err.println(a.format(33));
		System.err.println(a.format(-33));

		//�ۺ����ã�����Ĳ�ͬǰ��׺
		String pattern = "my moneny ###,###.## RMB;your money -###,###.## US";
		a.applyPattern(pattern);
		System.out.println(a.format(1223233.456));
		System.out.println(a.format(-1223233.456));
	}
}
