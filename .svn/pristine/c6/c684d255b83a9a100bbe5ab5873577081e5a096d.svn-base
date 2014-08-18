package com.qycloud.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.qycloud.web.utils.zip.ZipInputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class Zip {

	public static void zip(String zip, String source) throws Exception {
		zip(zip, new File(source));
	}

	public static void zip(String zip, File source) throws Exception {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(zip));
		ZipOutputStream zos = new ZipOutputStream(bos);
		zos.setEncoding("GBK");
		zip(zos, source, "");
		zos.close();
	}

	private static void zip(ZipOutputStream out, File source, String base)
			throws Exception {
		if (source.isDirectory()) { // 判断是否为目录
			File[] fl = source.listFiles();
			ZipEntry zipEntry = new ZipEntry(base + "/");
			zipEntry.setUnixMode(755);// 解决linux乱码
			out.putNextEntry(zipEntry);
			base = base.length() == 0 ? "" : base + "/";
			// 压缩目录中的所有文件
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else if (source.isFile()) {
			ZipEntry zipEntry = new ZipEntry(base);
			zipEntry.setUnixMode(644);// 解决linux乱码
			out.putNextEntry(zipEntry);
			BufferedInputStream bis = null;
			try {
				FileInputStream fis = new FileInputStream(source);
				bis = new BufferedInputStream(fis);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = bis.read(bytes)) != -1) {
					out.write(bytes, 0, read);
					bytes = new byte[1024];
				}
			} catch (Exception ex) {
				// TODO: handle exception
			} finally {
				if (bis != null) {
					bis.close();
				}
			}
		}
	}
	
	public static void unzip(File zipFile, File outDir) throws Exception {
		outDir.mkdirs();

		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));
		BufferedInputStream bis = new BufferedInputStream(in);
		com.qycloud.web.utils.zip.ZipEntry z;

		while ((z = in.getNextEntry()) != null) {
			if (z.isDirectory()) {
				String name = z.getName();
				name = name.substring(0, name.length() - 1);
				File f = new File(outDir, name);
				f.mkdir();
				System.out.println("mkdir:" + name);
			} else {
				File f = new File(outDir, z.getName());
				f.createNewFile();
				BufferedOutputStream os = null;
				try {
					os = new BufferedOutputStream(new FileOutputStream(f));
					int read = 0;
					byte[] bytes = new byte[1024];
					while ((read = bis.read(bytes)) != -1) {
						os.write(bytes, 0, read);
						bytes = new byte[1024];
					}
				} catch (Exception ex) {
					// TODO: handle exception
				} finally {
					if (os != null) {
						os.close();
					}
				}
				System.out.println("f:" + z.getName());
			}
		}
		in.close();
	}
	
	public static void unrar(File rarFile, File outDir) throws Exception {
		outDir.mkdirs();

		Archive archive = new Archive(rarFile);
		archive.getMainHeader().print();
		FileHeader fh;
		while ((fh = archive.nextFileHeader()) != null) {
			File f = new File(outDir, fh.getFileNameW().trim());
			if (fh.isDirectory()) {
				f.mkdir();
			} else {
				f.createNewFile();
				BufferedOutputStream bos = null;
				try {
					bos = new BufferedOutputStream(new FileOutputStream(f));
					archive.extractFile(fh, bos);
				} catch (Exception ex) {
					// TODO: handle exception
				} finally {
					if (bos != null) {
						bos.close();
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Zip File Begin");

		String zipPath = "D:\\temp\\rar.rar";
		String filePath = "D:\\temp\\rar";
		try {
			if (args.length == 2) {
				zipPath = args[0];
				filePath = args[1];
			}
			long c = System.currentTimeMillis();
			Zip.unrar(new File(zipPath), new File(filePath));
			long a = System.currentTimeMillis() - c;
			System.out.println(a);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Zip File Done");
	}

}
