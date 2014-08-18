package com.qycloud.test;

import com.conlect.oatos.utils.CallBack;

public class CallBackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CallBack callBack = new CallBack() {

			@Override
			public void goBack(String result) {
				// TODO Auto-generated method stub
				System.out.println(result);

			}
		};
		test(callBack);
	}

	private static void test(CallBack callBack) {
		String str = "test";
		callBack.goBack(str);
	}

}
