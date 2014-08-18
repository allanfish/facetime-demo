package com.conlect.oatos.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * 文件操作工具
 * 
 * @author yang
 * 
 */
public class FileUtil {

	/**
	 * 从文件路径中取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		String name = filePath;
		int i = filePath.lastIndexOf("/");
		if (i < 0) {
			i = filePath.lastIndexOf("\\");
		}
		if (i > 0) {
			name = filePath.substring(i + 1);
		}
		return name;
	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws Exception {
		if (targetFile.exists()) {
			return;
		}
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(sourceFile));
			os = new BufferedOutputStream(new FileOutputStream(targetFile));
			float size = 0;
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				os.write(bytes, 0, read);
				size = size + read;
				bytes = new byte[1024];
			}
		} catch (Exception ex) {
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.flush();
				os.close();
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @param delay
	 * @return
	 */
	public static boolean delete(String path, long delay) {
		return delete(new File(path), delay);
	}

	/**
	 * 删除文件
	 * 
	 * @param target
	 * @param delay
	 * @return
	 */
	public static boolean delete(File target, long delay) {
		try {
			if ((target == null) || !target.exists()) {
				return true;
			}

			Thread.sleep(delay);

			if (target.isDirectory()) {
				File[] subFile = target.listFiles();
				if (subFile != null) {
					for (File f : subFile) {
						if (!delete(f.getAbsolutePath(), 0)) {
							return false;
						}
					}
				}
				if (target.delete()) {
					return true;
				}
			} else {
				if (target.delete()) {
					return true;
				}
			}
		} catch (InterruptedException ex) {
		}
		return false;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isFileExist(String path) {
		File f = new File(path);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成文件guid名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String createFileGuidName(String fileName) {
		String suffix = CommonUtil.getFileSuffixName(fileName);
		if (CommonUtil.isOatw(fileName)) {
			suffix = CommConstants.FILE_FORMAT_HTML;
		}
		String guidName = UUID.randomUUID().toString();
		if (!"".equals(suffix.trim())) {
			guidName = guidName + "." + suffix;
		}
		return guidName;
	}

	/**
	 * 取文件的MD5
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getFileMD5(File file) throws Exception {
		String hash = null;
		BufferedInputStream is = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			is = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = is.read(buffer)) != -1) {
				digest.update(buffer, 0, read);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			hash = bigInt.toString(16);
		} catch (IOException ex) {
			hash = null;
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return hash;
	}

	/**
	 * 根据文件MD5搜索相同文件
	 * 
	 * @param suffix
	 * @param size
	 * @param hash
	 * @param folder
	 * @return
	 */
	public static File searchFile(String suffix, long size, String hash,
			File folder) {
		File file = null;
		try {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isFile()) {
						if (f.getName().toLowerCase()
								.endsWith(suffix.toLowerCase())
								&& f.length() == size
								&& hash.equals(getFileMD5(f))) {
							file = f;
						}
					}
					if (file != null) {
						break;
					}
				}
			}
		} catch (Exception ex) {
		}
		return file;
	}

	/**
	 * 流写入文件
	 * 
	 * @param in
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long copyInputStreamToFile(InputStream in, File file)
			throws IOException {
		return copyStream(in, new FileOutputStream(file));
	}

	/**
	 * 从输入流中读数据到输出流
	 * 
	 * @param in
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public static long copyStream(InputStream in, OutputStream out)
			throws IOException {
		return copyStream(in, 0, out);
	}

	/**
	 * 从输入流中读数据到多个输出流
	 * 
	 * @param in
	 * @param outs
	 * @return
	 * @throws IOException
	 */
	public static long copyStream(InputStream in, OutputStream... outs)
			throws IOException {
		long size = 0;

		BufferedInputStream bis = null;
		BufferedOutputStream[] oses = null;
		try {
			bis = new BufferedInputStream(in);

			oses = new BufferedOutputStream[outs.length];
			for (int i = 0; i < oses.length; i++) {
				oses[i] = new BufferedOutputStream(outs[i]);
			}

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = bis.read(bytes)) != -1) {
				for (BufferedOutputStream os : oses) {
					os.write(bytes, 0, read);
				}
				bytes = new byte[1024];
				size = size + read;
			}
		} catch (IOException ex) {
			if (bis != null) {
				bis.close();
			}
			if (oses != null) {
				for (BufferedOutputStream os : oses) {
					if (os != null) {
						os.flush();
						os.close();
					}
				}
			}
		}
		return size;
	}

	/**
	 * 从输入流的开始位置读数据
	 * 
	 * @param in
	 * @param skip
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public static long copyStream(InputStream in, long skip, OutputStream out)
			throws IOException {
		long size = 0;
		BufferedInputStream bis = null;
		BufferedOutputStream os = null;
		try {
			bis = new BufferedInputStream(in);
			os = new BufferedOutputStream(out);
			int read = 0;
			byte[] bytes = new byte[1024];

			if (skip > 0) {
				bis.skip(skip);
			}

			while ((read = bis.read(bytes)) != -1) {
				os.write(bytes, 0, read);
				bytes = new byte[1024];
				size = size + read;
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (os != null) {
				os.flush();
				os.close();
			}
		}
		return size;
	}

	/**
	 * 将输入流中skip位置, 长度为length的字节写入到输出流中
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @param skip
	 *            起始位置
	 * @param length
	 *            长度
	 * @return
	 * @throws Exception
	 */
	public static long copyStream(InputStream in, OutputStream out, int skip,
			int length) throws IOException {
		long size = 0;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out);
			if (skip > 0) {
				bis.skip(skip);
			}

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = bis.read(bytes)) != -1 && size < length) {
				if (length - size < read) {
					read = (int) (length - size);
				}
				bos.write(bytes, 0, read);
				bytes = new byte[1024];
				size = size + read;
			}

		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.flush();
				bos.close();
			}
		}
		return size;
	}

	/**
	 * copy html images
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @param sourceFileGuid
	 */
	public static void copyHtmlImages(final String sourceDir, String targetDir,
			final String sourceHtmlName) {
		try {
			if (sourceDir.equals(targetDir)) {
				return;
			}
			File sDir = new File(sourceDir);
			if (!sDir.exists()) {
				return;
			}

			File tDir = new File(targetDir);
			if (!tDir.exists()) {
				tDir.mkdirs();
			}

			File[] files = sDir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					boolean accept = !name.equals(sourceHtmlName);
					if (sourceDir.startsWith(DiskUtil.DISK_TEMP_DIR)) {
						accept = accept
								&& name.startsWith(CommonUtil
										.getFilePrefixName(sourceHtmlName));
					}
					return accept;
				}
			});

			if (files != null) {
				for (File f : files) {
					try {
						if (f.isFile()) {
							FileUtil.copyFile(new File(sDir, f.getName()),
									new File(tDir, f.getName()));
						} else if (f.isDirectory()) {
							// copyHtmlImages(sourceDir +
							// MyConstants.FILE_SEPARATOR + f.getName(),
							// targetDir + MyConstants.FILE_SEPARATOR +
							// f.getName(), "");
						}
					} catch (Exception e) {
					}

				}
			}
		} catch (Exception e) {
		}

	}

}
