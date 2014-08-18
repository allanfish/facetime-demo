package com.itcast.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader {

	private static final String fromPath = "D:\\Android\\workspace\\JavaDemo\\bin\\com\\itcast\\classloader\\Sample.class";
	private static final String toPath = "D:\\Android\\workspace\\JavaDemo\\itcastlib\\Sample.class";

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream(toPath);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			encode(fis, byteStream);
			byte[] buffer = byteStream.toByteArray();
			System.out.println("hllllllllll");
			return defineClass(name, buffer, 0, buffer.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(fromPath);
		FileOutputStream fos = new FileOutputStream(toPath);
		encode(fis, fos);
		fis.close();
		fos.close();
	}

	private static void encode(InputStream inStream, OutputStream outStream) throws IOException {
		int b = -1;
		while ((b = inStream.read()) != -1)
			outStream.write(b ^ 0xff);

	}
}
