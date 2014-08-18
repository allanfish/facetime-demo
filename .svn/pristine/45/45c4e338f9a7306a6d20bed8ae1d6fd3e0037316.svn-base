package com.qycloud.oatos.convert.test.logic;

import java.io.File;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.qycloud.oatos.convert.MyDocumentFormatRegistry;

public class OfficeConverterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OpenOfficeConnection connection1 = null;
		OpenOfficeConnection connection2 = null;
		try {

			connection1 = new SocketOpenOfficeConnection(8100);
			connection1.connect();
			MyDocumentFormatRegistry formatRegistry = new MyDocumentFormatRegistry();
			DocumentConverter converter1 = new OpenOfficeDocumentConverter(connection1, formatRegistry);
			
			connection2 = new SocketOpenOfficeConnection(8100);
			connection2.connect();
			DocumentConverter converter2 = new OpenOfficeDocumentConverter(connection2, formatRegistry);

			File source1 = new File("D:\\test\\d1.docx");
			File target1 = new File("D:\\test\\test\\d1.pdf");
			ConvertThread t1 = new ConvertThread(source1, target1, converter1);
			t1.start();

			File source2 = new File("D:\\test\\d2.doc");
			File target2 = new File("D:\\test\\test\\d2.pdf");
			ConvertThread t2 = new ConvertThread(source2, target2, converter1);
			t2.start();

			File source3 = new File("D:\\test\\d3.docx");
			File target3 = new File("D:\\test\\test\\d3.pdf");
			ConvertThread t3 = new ConvertThread(source3, target3, converter1);
			t3.start();

			File source4 = new File("D:\\test\\d4.ppt");
			File target4 = new File("D:\\test\\test\\d4.pdf");
			ConvertThread t4 = new ConvertThread(source4, target4, converter1);
			t4.start();

			File source5 = new File("D:\\test\\d5.ppt");
			File target5 = new File("D:\\test\\test\\d5.pdf");
			ConvertThread t5 = new ConvertThread(source5, target5, converter1);
			t5.start();

			File source6 = new File("D:\\test\\d6.pptx");
			File target6 = new File("D:\\test\\test\\d6.pdf");
			ConvertThread t6 = new ConvertThread(source6, target6, converter2);
			t6.start();

			File source7 = new File("D:\\test\\d7.doc");
			File target7 = new File("D:\\test\\test\\d7.pdf");
			ConvertThread t7 = new ConvertThread(source7, target7, converter2);
			t7.start();

			File source8 = new File("D:\\test\\d8.ppt");
			File target8 = new File("D:\\test\\test\\d8.pdf");
			ConvertThread t8 = new ConvertThread(source8, target8, converter2);
			t8.start();

			File source9 = new File("D:\\test\\d9.ppt");
			File target9 = new File("D:\\test\\test\\d9.pdf");
			ConvertThread t9 = new ConvertThread(source9, target9, converter2);
			t9.start();

			File source10 = new File("D:\\test\\d10.ppt");
			File target10 = new File("D:\\test\\test\\d10.pdf");
			ConvertThread t10 = new ConvertThread(source10, target10, converter2);
			t10.start();

			File source11 = new File("D:\\test\\d11.docx");
			File target11 = new File("D:\\test\\test\\d11.pdf");
			ConvertThread t11 = new ConvertThread(source11, target11, converter2);
			t11.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			connection1.disconnect();
			connection2.disconnect();
		}
	}

	private static class ConvertThread extends Thread {
		private File source;
		private File target;
		private DocumentConverter converter;

		public ConvertThread(File source, File target,
				DocumentConverter converter) {
			this.source = source;
			this.target = target;
			this.converter = converter;
		}

		@Override
		public void run() {
			try {
				System.out.println(new Date().toString() + "===start===" + source);
				long start = System.currentTimeMillis();
				converter.convert(source, target);
				long cost = System.currentTimeMillis() - start;
				System.out.println(new Date().toString() + "===end===" + source + "===" + cost);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

}
