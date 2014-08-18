package com.conlect.oatos.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * File utilities.
 */
public class FileUtils {

	// ---------------------------------------------------------------- misc
	// shortcuts
	// private static Map<String, FileTypeBean> fileTypeMap;
	public static class FileTypeBean {
		public String desc, icon;

		FileTypeBean(final String desc, final String icon) {
			this.desc = desc;
			this.icon = icon;
		}
	}

	// ---------------------------------------------------------------- option

	// default global FileOption
	public static FileOption defaultOption = new FileOption();

	/**
	 * {@link FileUtils File utilities} parameters.
	 */
	public static class FileOption implements Cloneable {

		public boolean preserveDate = true; // should destination file have the
											// same timestamp as source
		public boolean overwrite = true; // overwrite existing destination
		public boolean createDirs = true; // create missing subdirectories of
											// destination
		public boolean recursive = true; // use recursive directory copying and
											// deleting
		public boolean continueOnError = true; // don't stop on error and
												// continue job as much as
												// possible
		public String encoding = "UTF-8"; // default Encoding for
											// reading/writing strings

		@Override
		public FileOption clone() throws CloneNotSupportedException {
			return (FileOption) super.clone();
		}
	}

	/**
	 * CreatingUtils new {@link FileOption} creating by cloning current default
	 * option.
	 */
	public static FileOption cloneFileOption() {
		try {
			return defaultOption.clone();
		} catch (CloneNotSupportedException cnsex) {
			return null;
		}
	}

	/**
	 * CreatingUtils new {@link FileOption} creating with default values.
	 */
	public static FileOption params() {
		return new FileOption();
	}

	public static String[] imageFormats = new String[] { "jpg", "jepg", "gif",
			"png", "bmp" };

	public static String[] textFormats = new String[] { "txt", "logging" };

	public static String[] htmlFormats = new String[] { "htm", "html" };

	public static String[] mplayerFormats = new String[] { "avi", "wmv", "asf" };

	public static String[] flashFormats = new String[] { "swf" };

	public static String[] mswordFormats = new String[] { "doc" };

	/**
	 * Checks if two files points to the same file.
	 */
	public static boolean equals(String file1, String file2) {
		return equals(new File(file1), new File(file2));
	}

	/**
	 * Checks if two files points to the same file.
	 */
	public static boolean equals(File file1, File file2) {
		try {
			file1 = file1.getCanonicalFile();
			file2 = file2.getCanonicalFile();
		} catch (IOException ioex) {
			return false;
		}
		return file1.equals(file2);
	}

	/**
	 * Converts file URLs to file. Ignores other schemes and returns
	 * <code>null</code>.
	 */
	public static File toFile(URL url) {
		return new File(toFileName(url));
	}

	/**
	 * Converts file URLs to file getName. Ignores other schemes and returns
	 * <code>null</code>.
	 */
	public static String toFileName(URL url) {
		if ((url == null) || (url.getProtocol().equals("file") == false)) {
			return null;
		}
		String filename = url.getFile().replace('/', File.separatorChar);
		int pos = 0;
		while ((pos = filename.indexOf('%', pos)) >= 0) {
			if (pos + 2 < filename.length()) {
				String hexStr = filename.substring(pos + 1, pos + 3);
				char ch = (char) Integer.parseInt(hexStr, 16);
				filename = filename.substring(0, pos) + ch
						+ filename.substring(pos + 3);
			}
		}
		return filename;
	}

	/**
	 * Converts array of URLS to file names string. Other schemes are ignored.
	 */
	public static String toFileNames(URL[] urls) {
		StringBuilder path = new StringBuilder();
		for (URL url : urls) {
			String fileName = toFileName(url);
			if (fileName == null) {
				continue;
			}
			path.append(fileName).append(File.pathSeparatorChar);
		}
		return path.toString();
	}

	// ---------------------------------------------------------------- mkdirs

	/**
	 * CreatingUtils all folders at once.
	 */
	public static void mkdirs(String dirs) throws IOException {
		mkdirs(new File(dirs));
	}

	/**
	 * CreatingUtils all folders at once.
	 */
	public static void mkdirs(File dirs) throws IOException {
		if (dirs.exists()) {
			if (dirs.isDirectory() == false) {
				throw new IOException("Directory '" + "' is not a directory.");
			}
			return;
		}
		if (dirs.mkdirs() == false) {
			throw new IOException("Unable to create directory '" + dirs + "'.");
		}
	}

	/**
	 * CreatingUtils single folder.
	 */
	public static void mkdir(String dir) throws IOException {
		mkdir(new File(dir));
	}

	/**
	 * CreatingUtils single folders.
	 */
	public static void mkdir(File dir) throws IOException {
		if (dir.exists()) {
			if (dir.isDirectory() == false) {
				throw new IOException("Destination '" + "' is not a directory.");
			}
			return;
		}
		if (dir.mkdir() == false) {
			throw new IOException("Unable to create directory '" + dir + "'.");
		}
	}

	// ---------------------------------------------------------------- touch

	public static void touch(String file) throws IOException {
		touch(new File(file));
	}

	/**
	 * Implements the Unix "touch" utility. It creates a new file with size 0
	 * or, if the file exists already, it is opened and closed without modifying
	 * it, but updating the file date and time.
	 */
	public static void touch(File file) throws IOException {
		if (file.exists() == false) {
			IOUtils.close(new FileOutputStream(file));
		}
		file.setLastModified(System.currentTimeMillis());
	}

	// ---------------------------------------------------------------- copy
	// file to file

	public static void copyFile(String src, String dest) throws IOException {
		copyFile(new File(src), new File(dest), defaultOption);
	}

	public static void copyFile(String src, String dest, FileOption option)
			throws IOException {
		copyFile(new File(src), new File(dest), option);
	}

	public static void copyFile(File src, File dest) throws IOException {
		copyFile(src, dest, defaultOption);
	}

	/**
	 * Copies a file to another file with specified copy option.
	 */
	public static void copyFile(File src, File dest, FileOption option)
			throws IOException {
		checkFileCopy(src, dest, option);
		doCopyFile(src, dest, option);
	}

	private static void checkFileCopy(File src, File dest, FileOption option)
			throws IOException {
		if (src.exists() == false) {
			throw new FileNotFoundException("Source '" + src
					+ "' does not exist.");
		}
		if (src.isFile() == false) {
			throw new IOException("Source '" + src + "' is not a file.");
		}
		if (equals(src, dest) == true) {
			throw new IOException("Source '" + src + "' and destination '"
					+ dest + "' are the same.");
		}

		File destParent = dest.getParentFile();
		if (destParent != null && destParent.exists() == false) {
			if (option.createDirs == false) {
				throw new IOException("Destination directory '" + destParent
						+ "' doesn't exist.");
			}
			if (destParent.mkdirs() == false) {
				throw new IOException("Destination directory '" + destParent
						+ "' cannot be created.");
			}
		}
	}

	/**
	 * Internal file copy when most of the pre-checking has passed.
	 */
	private static void doCopyFile(File src, File dest, FileOption option)
			throws IOException {
		if (dest.exists()) {
			if (dest.isDirectory()) {
				throw new IOException("Destination '" + dest
						+ "' is a directory.");
			}
			if (option.overwrite == false) {
				throw new IOException("Destination '" + dest
						+ "' already exists.");
			}
		}

		doCopy(src, dest);

		if (src.length() != dest.length()) {
			throw new IOException("Copying of '" + src + "' to '" + dest
					+ "' failed due to different sizes.");
		}
		if (option.preserveDate) {
			dest.setLastModified(src.lastModified());
		}
	}

	// ---------------------------------------------------------------- simple
	// copy file

	/**
	 * Copies one file to another without any checking.
	 * 
	 * @see #doCopy(java.io.File, java.io.File)
	 */
	protected static void doCopy(String src, String dest) throws IOException {
		doCopy(new File(src), new File(dest));
	}

	/**
	 * Copies one file to another without any checking. It is assumed that both
	 * parameters represents valid files.
	 */
	protected static void doCopy(File src, File dest) throws IOException {
		FileInputStream input = new FileInputStream(src);
		try {
			FileOutputStream output = new FileOutputStream(dest);
			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.close(output);
			}
		} finally {
			IOUtils.close(input);
		}
	}

	// ---------------------------------------------------------------- copy
	// file to directory

	// public static void copyFile(final File from, final File to) throws
	// IOException {
	// final InputStream inputStream = new BufferedInputStream(new
	// FileInputStream(from));
	// copyFile(inputStream, to);
	// }

	public static void copyFile(final InputStream inputStream, final File to)
			throws IOException {
		createFile(to);
		OutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(to));
			IOUtils.copyStream(inputStream, outputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void copyFileToDir(String fileName, String destDir)
			throws IOException {
		copyFileToDir(new File(fileName), new File(destDir), defaultOption);
	}

	public static void copyFileToDir(String fileName, String destDir,
			FileOption option) throws IOException {
		copyFileToDir(new File(fileName), new File(destDir), option);
	}

	public static void copyFileToDir(File src, File destDir) throws IOException {
		copyFileToDir(src, destDir, defaultOption);
	}

	/**
	 * Copies a file to folder with specified copy option.
	 */
	public static void copyFileToDir(File src, File destDir, FileOption option)
			throws IOException {
		if (destDir.exists() && destDir.isDirectory() == false) {
			throw new IOException("Destination '" + destDir
					+ "' is not a directory.");
		}
		copyFile(src, new File(destDir, src.getName()), option);
	}

	// ---------------------------------------------------------------- copy dir

	public static void copyDir(String srcDir, String destDir)
			throws IOException {
		copyDir(new File(srcDir), new File(destDir), defaultOption);
	}

	public static void copyDir(String srcDir, String destDir, FileOption option)
			throws IOException {
		copyDir(new File(srcDir), new File(destDir), option);
	}

	public static void copyDir(File srcDir, File destDir) throws IOException {
		copyDir(srcDir, destDir, defaultOption);
	}

	/**
	 * Copies directory with specified copy option.
	 */
	public static void copyDir(File srcDir, File destDir, FileOption option)
			throws IOException {
		checkDirCopy(srcDir, destDir);
		doCopyDirectory(srcDir, destDir, option);
	}

	private static void checkDirCopy(File srcDir, File destDir)
			throws IOException {
		if (!srcDir.exists()) {
			throw new FileNotFoundException("Source '" + srcDir
					+ "' does not exist.");
		}
		if (!srcDir.isDirectory()) {
			throw new IOException("Source '" + srcDir + "' is not a directory.");
		}
		if (equals(srcDir, destDir)) {
			throw new IOException("Source '" + srcDir + "' and destination '"
					+ destDir + "' are the same.");
		}
	}

	private static void doCopyDirectory(File srcDir, File destDir,
			FileOption option) throws IOException {
		if (destDir.exists()) {
			if (!destDir.isDirectory()) {
				throw new IOException("Destination '" + destDir
						+ "' is not a directory.");
			}
		} else {
			if (!option.createDirs) {
				throw new IOException("Destination '" + destDir
						+ "' doesn't exists.");
			}
			if (!destDir.mkdirs()) {
				throw new IOException("Destination '" + destDir
						+ "' directory cannot be created.");
			}
			if (option.preserveDate) {
				destDir.setLastModified(srcDir.lastModified());
			}
		}

		File[] files = srcDir.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of '" + srcDir
					+ '\'');
		}

		IOException exception = null;
		for (File file : files) {
			File destFile = new File(destDir, file.getName());
			try {
				if (file.isDirectory()) {
					if (option.recursive) {
						doCopyDirectory(file, destFile, option);
					}
				} else {
					doCopyFile(file, destFile, option);
				}
			} catch (IOException ioex) {
				if (option.continueOnError) {
					exception = ioex;
					continue;
				}
				throw ioex;
			}
		}

		if (exception != null) {
			throw exception;
		}
	}

	// ---------------------------------------------------------------- move
	// file

	public static void moveFile(String src, String dest) throws IOException {
		moveFile(new File(src), new File(dest), defaultOption);
	}

	public static void moveFile(String src, String dest, FileOption option)
			throws IOException {
		moveFile(new File(src), new File(dest), option);
	}

	public static void moveFile(File src, File dest) throws IOException {
		moveFile(src, dest, defaultOption);
	}

	public static void moveFile(File src, File dest, FileOption option)
			throws IOException {
		checkFileCopy(src, dest, option);
		doMoveFile(src, dest, option);
	}

	private static void doMoveFile(File src, File dest, FileOption option)
			throws IOException {
		if (dest.exists()) {
			if (!dest.isFile()) {
				throw new IOException("Destination '" + dest
						+ "' is not a file.");
			}
			if (!option.overwrite) {
				throw new IOException("Destination '" + dest
						+ "' already exists.");
			}
			dest.delete();
		}

		if (!src.renameTo(dest)) {
			throw new IOException("Moving of '" + src + "' to '" + dest
					+ "' failed.");
		}
	}

	// ---------------------------------------------------------------- move
	// file to dir

	public static void moveFileToDir(String src, String destDir)
			throws IOException {
		moveFileToDir(new File(src), new File(destDir), defaultOption);
	}

	public static void moveFileToDir(String src, String destDir,
			FileOption option) throws IOException {
		moveFileToDir(new File(src), new File(destDir), option);
	}

	public static void moveFileToDir(File src, File destDir) throws IOException {
		moveFileToDir(src, destDir, defaultOption);
	}

	public static void moveFileToDir(File src, File destDir, FileOption option)
			throws IOException {
		if (destDir.exists() && destDir.isDirectory() == false) {
			throw new IOException("Destination '" + destDir
					+ "' is not a directory.");
		}
		moveFile(src, new File(destDir, src.getName()), option);
	}

	// ---------------------------------------------------------------- move dir

	public static void moveDir(String srcDir, String destDir)
			throws IOException {
		moveDir(new File(srcDir), new File(destDir));
	}

	public static void moveDir(File srcDir, File destDir) throws IOException {
		checkDirCopy(srcDir, destDir);
		doMoveDirectory(srcDir, destDir);
	}

	private static void doMoveDirectory(File src, File dest) throws IOException {
		if (dest.exists()) {
			if (!dest.isDirectory()) {
				throw new IOException("Destination '" + dest
						+ "' is not a directory.");
			}
			dest = new File(dest, dest.getName());
			dest.mkdir();
		}

		if (!src.renameTo(dest)) {
			throw new IOException("Moving of '" + src + "' to '" + dest
					+ "' failed.");
		}
	}

	// ---------------------------------------------------------------- delete
	// file

	public static void deleteFile(String dest) throws IOException {
		deleteFile(new File(dest));
	}

	public static void deleteFile(File dest) throws IOException {
		if (!dest.exists()) {
			throw new FileNotFoundException("Destination '" + dest
					+ "' doesn't exist");
		}
		if (!dest.isFile()) {
			throw new IOException("Destination '" + dest + "' is not a file.");
		}
		if (!dest.delete()) {
			throw new IOException("Unable to delete '" + dest + "'.");
		}
	}

	// ---------------------------------------------------------------- delete
	// dir

	public static void deleteDir(String dest) throws IOException {
		deleteDir(new File(dest), defaultOption);
	}

	public static void deleteDir(String dest, FileOption option)
			throws IOException {
		deleteDir(new File(dest), option);
	}

	public static void deleteDir(File dest) throws IOException {
		deleteDir(dest, defaultOption);
	}

	/**
	 * Deletes a directory.
	 */
	public static void deleteDir(File dest, FileOption option)
			throws IOException {
		cleanDir(dest, option);
		if (!dest.delete()) {
			throw new IOException("Unable to delete '" + dest + "'.");
		}
	}

	public static void cleanDir(String dest) throws IOException {
		cleanDir(new File(dest), defaultOption);
	}

	public static void cleanDir(String dest, FileOption option)
			throws IOException {
		cleanDir(new File(dest), option);
	}

	public static void cleanDir(File dest) throws IOException {
		cleanDir(dest, defaultOption);
	}

	/**
	 * Cleans a directory without deleting it.
	 */
	public static void cleanDir(File dest, FileOption option)
			throws IOException {
		if (!dest.exists()) {
			throw new FileNotFoundException("Destination '" + dest
					+ "' doesn't exists.");
		}

		if (!dest.isDirectory()) {
			throw new IOException("Destination '" + dest
					+ "' is not a directory.");
		}

		File[] files = dest.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of '" + dest + "'.");
		}

		IOException exception = null;
		for (File file : files) {
			try {
				if (file.isDirectory()) {
					if (option.recursive) {
						deleteDir(file, option);
					}
				} else {
					file.delete();
				}
			} catch (IOException ioex) {
				if (option.continueOnError) {
					exception = ioex;
					continue;
				}
				throw ioex;
			}
		}

		if (exception != null) {
			throw exception;
		}
	}

	// ----------------------------------------------------------------
	// read/write string

	public static String readFileString(String source) throws IOException {
		return readFileString(new File(source), defaultOption.encoding);
	}

	public static String readFileString(String source, String encoding)
			throws IOException {
		return readFileString(new File(source), encoding);
	}

	public static String readFileString(File source) throws IOException {
		return readFileString(source, defaultOption.encoding);
	}

	/**
	 * Reads file content as string.
	 */
	public static String readFileString(File source, String encoding)
			throws IOException {
		if (!source.exists()) {
			throw new FileNotFoundException("Source '" + source
					+ "' doesn't exist.");
		}
		if (!source.isFile()) {
			throw new IOException("Source '" + source + "' is not a file.");
		}
		long len = source.length();
		if (len >= Integer.MAX_VALUE) {
			len = Integer.MAX_VALUE;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(source);
			StringWriter sw = new StringWriter((int) len);
			IOUtils.copy(in, sw, encoding);
			return sw.toString();
		} finally {
			IOUtils.close(in);
		}
	}

	public static void writeFile(File file, String data, String encoding,
			boolean append) throws IOException {
		if (file.exists()) {
			if (!file.isFile()) {
				throw new IOException("Destination '" + file
						+ "' exist, but it is not a file.");
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file, append);
			out.write(data.getBytes(encoding));
		} finally {
			IOUtils.close(out);
		}
	}

	public static void writeFile(File dest, byte[] data, int off, int len,
			boolean append) throws IOException {
		if (dest.exists() == true) {
			if (dest.isFile() == false) {
				throw new IOException("Destination '" + dest
						+ "' exist but it is not a file.");
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(dest, append);
			out.write(data, off, len);
		} finally {
			IOUtils.close(out);
		}
	}

	public static void writeToFile(String fileName, String data)
			throws IOException {
		writeFile(new File(fileName), data, defaultOption.encoding, false);
	}

	public static void writeToFile(String fileName, String data, String encoding)
			throws IOException {
		writeFile(new File(fileName), data, encoding, false);
	}

	public static void writeToFile(File dest, String data) throws IOException {
		writeFile(dest, data, defaultOption.encoding, false);
	}

	public static void writeToFile(File dest, String data, String encoding)
			throws IOException {
		writeFile(dest, data, encoding, false);
	}

	public static void appendToFile(String dest, String data)
			throws IOException {
		writeFile(new File(dest), data, defaultOption.encoding, true);
	}

	public static void appendToFile(String dest, String data, String encoding)
			throws IOException {
		writeFile(new File(dest), data, encoding, true);
	}

	public static void appendToFile(File dest, String data) throws IOException {
		writeFile(dest, data, defaultOption.encoding, true);
	}

	public static void appendToFile(File dest, String data, String encoding)
			throws IOException {
		writeFile(dest, data, encoding, true);
	}

	// ----------------------------------------------------------------
	// read/write string lines

	public static String[] readLines(String fileName) throws IOException {
		return readLines(new File(fileName), defaultOption.encoding);
	}

	public static String[] readLines(String fileName, String encoding)
			throws IOException {
		return readLines(new File(fileName), encoding);
	}

	public static String[] readLines(File fileName) throws IOException {
		return readLines(fileName, defaultOption.encoding);
	}

	/**
	 * Reads lines from source files.
	 */
	public static String[] readLines(File source, String encoding)
			throws IOException {
		if (source.exists() == false) {
			throw new FileNotFoundException("Source '" + source
					+ "' doesn't exist.");
		}
		if (source.isFile() == false) {
			throw new IOException("Source '" + source + "' is not a file.");
		}
		FileInputStream in = null;
		in = new FileInputStream(source);
		return readLines(in, encoding);
	}

	public static void writeLine(String file, String line) throws Exception {
		PrintWriter pw = new PrintWriter(file);
		try {

		} catch (Exception e) {
		}
		pw.println(line);
	}

	public static String[] readLines(InputStream in, String encoding)
			throws IOException {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					encoding));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				list.add(strLine);
			}
		} finally {
			IOUtils.close(in);
		}
		return list.toArray(new String[list.size()]);

	}

	// ----------------------------------------------------------------
	// read/write bytearray

	public static byte[] readBytes(String file) throws IOException {
		return readBytes(new File(file));
	}

	public static byte[] readBytes(File source) throws IOException {
		if (source.exists() == false) {
			throw new FileNotFoundException("Source '" + source
					+ "' doesn't exist.");
		}
		if (source.isFile() == false) {
			throw new IOException("Source '" + source
					+ "' exists, but it is not a file.");
		}
		long len = source.length();
		if (len >= Integer.MAX_VALUE) {
			throw new IOException("Source size is greater then max array size.");
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(source);
			return IOUtils.readBytes(in);
		} finally {
			IOUtils.close(in);
		}
	}

	public static void writeToFile(String fileName, byte[] data)
			throws IOException {
		writeFile(new File(fileName), data, 0, data.length, false);
	}

	public static void writeToFile(String fileName, byte[] data, int off,
			int len) throws IOException {
		writeFile(new File(fileName), data, off, len, false);
	}

	public static void writeToFile(File destFile, byte[] data)
			throws IOException {
		writeFile(destFile, data, 0, data.length, false);
	}

	public static void writeToFile(File destFile, byte[] data, int off, int len)
			throws IOException {
		writeFile(destFile, data, off, len, false);
	}

	public static void appendToFile(String dest, byte[] data)
			throws IOException {
		writeFile(new File(dest), data, 0, data.length, true);
	}

	public static void appendToFile(String dest, byte[] data, int off, int len)
			throws IOException {
		writeFile(new File(dest), data, off, len, true);
	}

	public static void appendToFile(File dest, byte[] data) throws IOException {
		writeFile(dest, data, 0, data.length, true);
	}

	public static void appendToFile(File dest, byte[] data, int off, int len)
			throws IOException {
		writeFile(dest, data, off, len, true);
	}

	// ---------------------------------------------------------------- equals
	// content

	public static boolean compare(String file1, String file2)
			throws IOException {
		return compare(new File(file1), new File(file2));
	}

	/**
	 * Compare the contents of two files to determine if they are equal or not.
	 * <p>
	 * This method checks to see if the two files are different lengths or if
	 * they point to the same file, before resorting to byte-by-byte comparison
	 * of the contents.
	 * <p>
	 * Code origin: Avalon
	 */
	public static boolean compare(File file1, File file2) throws IOException {
		boolean file1Exists = file1.exists();
		if (file1Exists != file2.exists()) {
			return false;
		}

		if (file1Exists == false) {
			return true;
		}

		if ((file1.isFile() == false) || (file2.isFile() == false)) {
			throw new IOException("Only files can be compared.");
		}

		if (file1.length() != file2.length()) {
			return false;
		}

		if (equals(file1, file1)) {
			return true;
		}

		InputStream input1 = null;
		InputStream input2 = null;
		try {
			input1 = new FileInputStream(file1);
			input2 = new FileInputStream(file2);
			return IOUtils.compare(input1, input2);
		} finally {
			IOUtils.close(input1);
			IOUtils.close(input2);
		}
	}

	// ---------------------------------------------------------------- time

	public static boolean isNewer(String file, String reference) {
		return isNewer(new File(file), new File(reference));
	}

	/**
	 * PerformTest if specified <code>File</code> is newer than the reference
	 * <code>File</code>.
	 * 
	 * @param file
	 *            the <code>File</code> of which the modification date must be
	 *            compared
	 * @param reference
	 *            the <code>File</code> of which the modification date is used
	 * @return <code>true</code> if the <code>File</code> exists and has been
	 *         modified more recently than the reference <code>File</code>.
	 */
	public static boolean isNewer(File file, File reference) {
		if (reference.exists() == false) {
			throw new IllegalArgumentException("The reference file '" + file
					+ "' doesn't exist");
		}
		return isNewer(file, reference.lastModified());
	}

	public static boolean isOlder(String file, String reference) {
		return isOlder(new File(file), new File(reference));
	}

	public static boolean isOlder(File file, File reference) {
		if (reference.exists() == false) {
			throw new IllegalArgumentException("The reference file '" + file
					+ "' doesn't exist");
		}
		return isOlder(file, reference.lastModified());
	}

	/**
	 * Tests if the specified <code>File</code> is newer than the specified time
	 * reference.
	 * 
	 * @param file
	 *            the <code>File</code> of which the modification date must be
	 *            compared.
	 * @param timeMillis
	 *            the time reference measured in milliseconds since the epoch
	 *            (00:00:00 GMT, January 1, 1970)
	 * @return <code>true</code> if the <code>File</code> exists and has been
	 *         modified after the given time reference.
	 */
	public static boolean isNewer(File file, long timeMillis) {
		if (!file.exists()) {
			return false;
		}
		return file.lastModified() > timeMillis;
	}

	public static boolean isNewer(String file, long timeMillis) {
		return isNewer(new File(file), timeMillis);
	}

	public static boolean isOlder(File file, long timeMillis) {
		if (!file.exists()) {
			return false;
		}
		return file.lastModified() < timeMillis;
	}

	public static boolean isOlder(String file, long timeMillis) {
		return isOlder(new File(file), timeMillis);
	}

	// ---------------------------------------------------------------- smart
	// copy

	public static void copy(String src, String dest) throws IOException {
		copy(new File(src), new File(dest), defaultOption);
	}

	public static void copy(String src, String dest, FileOption option)
			throws IOException {
		copy(new File(src), new File(dest), option);
	}

	public static void copy(File src, File dest) throws IOException {
		copy(src, dest, defaultOption);
	}

	/**
	 * Smart copy. If source is a directory, copy it to destination. Otherwise,
	 * if destination is directory, copy source file to it. Otherwise, try to
	 * copy source file to destination file.
	 */
	public static void copy(File src, File dest, FileOption option)
			throws IOException {
		if (src.isDirectory()) {
			copyDir(src, dest, option);
			return;
		}
		if (dest.isDirectory()) {
			copyFileToDir(src, dest, option);
			return;
		}
		copyFile(src, dest, option);
	}

	// ---------------------------------------------------------------- smart
	// move

	public static void move(String src, String dest) throws IOException {
		move(new File(src), new File(dest), defaultOption);
	}

	public static void move(String src, String dest, FileOption option)
			throws IOException {
		move(new File(src), new File(dest), option);
	}

	public static void move(File src, File dest) throws IOException {
		move(src, dest, defaultOption);
	}

	/**
	 * Smart move. If source is a directory, move it to destination. Otherwise,
	 * if destination is directory, move source file to it. Otherwise, try to
	 * move source file to destination file.
	 */
	public static void move(File src, File dest, FileOption option)
			throws IOException {
		if (src.isDirectory() == true) {
			moveDir(src, dest);
			return;
		}
		if (dest.isDirectory() == true) {
			moveFileToDir(src, dest, option);
			return;
		}
		moveFile(src, dest, option);
	}

	// ---------------------------------------------------------------- smart
	// delete

	public static void delete(String dest) throws IOException {
		delete(new File(dest), defaultOption);
	}

	public static void delete(String dest, FileOption option)
			throws IOException {
		delete(new File(dest), option);
	}

	public static void delete(File dest) throws IOException {
		delete(dest, defaultOption);
	}

	/**
	 * Smart delete of destination file or directory.
	 */
	public static void delete(File dest, FileOption option) throws IOException {
		if (dest.isDirectory()) {
			deleteDir(dest, option);
			return;
		}
		deleteFile(dest);
	}

	// ---------------------------------------------------------------- misc

	/**
	 * Check if one file is an ancestor of second one.
	 * 
	 * @param strict
	 *            if <code>false</code> then this method returns
	 *            <code>true</code> if ancestor and file are equal
	 * @return <code>true</code> if ancestor is parent of file;
	 *         <code>false</code> otherwise
	 */
	public static boolean isAncestor(File ancestor, File file, boolean strict) {
		File parent = strict ? getParentFile(file) : file;
		while (true) {
			if (parent == null) {
				return false;
			}
			if (parent.equals(ancestor)) {
				return true;
			}
			parent = getParentFile(parent);
		}
	}

	/**
	 * Returns parent for the file. The method correctly processes "." and ".."
	 * in file names. The getName remains relative if was relative before.
	 * Returns <code>null</code> if the file has no parent.
	 */
	public static File getParentFile(final File file) {
		int skipCount = 0;
		File parentFile = file;
		while (true) {
			parentFile = parentFile.getParentFile();
			if (parentFile == null) {
				return null;
			}
			if (".".equals(parentFile.getName())) {
				continue;
			}
			if ("..".equals(parentFile.getName())) {
				skipCount++;
				continue;
			}
			if (skipCount > 0) {
				skipCount--;
				continue;
			}
			return parentFile;
		}
	}

	public static boolean isFilePathAcceptable(File file, FileFilter fileFilter) {
		do {
			if (fileFilter != null && !fileFilter.accept(file)) {
				return false;
			}
			file = file.getParentFile();
		} while (file != null);
		return true;
	}

	// ---------------------------------------------------------------- temp

	/**
	 * CreatingUtils temp directory.
	 */
	public static File createTempDirectory(String prefix, String suffix)
			throws IOException {
		File file = doCreateTempFile(prefix, suffix, null);
		file.delete();
		file.mkdir();
		return file;
	}

	/**
	 * CreatingUtils temp file.
	 */
	public static File createTempFile(final File dir, String prefix,
			String suffix, final boolean create) throws IOException {
		File file = doCreateTempFile(prefix, suffix, dir);
		file.delete();
		if (create) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * CreatingUtils temp file.
	 */
	public static File createTempFile(String prefix, String suffix)
			throws IOException {
		File file = doCreateTempFile(prefix, suffix, null);
		file.delete();
		file.createNewFile();
		return file;
	}

	private static File doCreateTempFile(String prefix, String suffix,
			final File dir) throws IOException {
		if (prefix.length() < 3) {
			prefix = (prefix + "___").substring(0, 3);
		}
		int exceptionsCount = 0;
		while (true) {
			try {
				return File.createTempFile(prefix, suffix, dir)
						.getCanonicalFile();
			} catch (IOException ioex) { // Win32 createFileExclusively access
											// denied
				if (++exceptionsCount >= 100) {
					throw ioex;
				}
			}
		}
	}

	// -------------------------------

	// public static boolean createDirectoryRecursively(File directory) {
	// if (directory == null) {
	// return false;
	// } else if (directory.exists()) {
	// return true;
	// } else if (!directory.isAbsolute()) {
	// directory = new File(directory.getAbsolutePath());
	// }
	// final String parent = directory.getParent();
	// if ((parent == null) || !createDirectoryRecursively(new File(parent))) {
	// return false;
	// }
	// directory.mkdir();
	// return directory.exists();
	// }

	public static File createFile(final File file) throws IOException {
		if (!file.exists()) {
			// createDirectoryRecursively(file.getParentFile());
			mkdirs(file.getParentFile());
			file.createNewFile();
		}
		return file;
	}

	public static void writeToFile(final File file, final InputStream in)
			throws IOException {
		createFile(file);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			IOUtils.copyStream(in, fos);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static boolean isCompatibleFile(String filename,
			final String[] formats) {
		if ((filename == null) || (formats == null)) {
			return false;
		}
		filename = filename.toLowerCase();
		for (final String format : formats) {
			if (filename.endsWith("." + format.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
