package com.conlect.oatos.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * IO功能辅助类
 * 
 * @author dzb
 */
public class IOUtils {

	/**
	 * The default buffer size to use.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private static final byte[] UTF_BOM = new byte[] { (byte) 0xEF,
			(byte) 0xBB, (byte) 0xBF };

	private static final int IoBufferSize = 1024;

	private static final String Encoding = "UTF-8";

	// NOTE: This class is focused on InputStream, OutputStream, Reader and
	// Writer. Each method should take at least one of these as a parameter.
	// NOTE: This class should not depend on any other classes

	/**
	 * Instances should NOT be constructed in standard programming.
	 */
	public IOUtils() {
	}

	/**
	 * Closes an input stream and releases any system resources associated with
	 * this stream. No exception will be thrown if an I/O error occurs.
	 */
	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	/**
	 * Closes an output stream and releases any system resources associated with
	 * this stream. No exception will be thrown if an I/O error occurs.
	 */
	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException ioex) {
				// ignore
			}
			try {
				out.close();
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	/**
	 * Closes a character-input stream and releases any system resources
	 * associated with this stream. No exception will be thrown if an I/O error
	 * occurs.
	 */
	public static void close(Reader in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	/**
	 * Closes a character-output stream and releases any system resources
	 * associated with this stream. No exception will be thrown if an I/O error
	 * occurs.
	 */
	public static void close(Writer out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException ioex) {
				// ignore
			}
			try {
				out.close();
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	/**
	 * Compares the content of two byte streams.
	 * 
	 * @return <code>true</code> if the content of the first stream is equal to
	 *         the content of the second stream.
	 */
	public static boolean compare(InputStream input1, InputStream input2)
			throws IOException {

		if (!(input1 instanceof BufferedInputStream)) {
			input1 = new BufferedInputStream(input1);
		}

		if (!(input2 instanceof BufferedInputStream)) {
			input2 = new BufferedInputStream(input2);
		}

		int ch = input1.read();
		while (ch != -1) {
			int ch2 = input2.read();
			if (ch != ch2) {
				return false;
			}
			ch = input1.read();
		}
		int ch2 = input2.read();
		return ch2 == -1;
	}

	/**
	 * Compares the content of two character streams.
	 * 
	 * @return <code>true</code> if the content of the first stream is equal to
	 *         the content of the second stream.
	 */
	public static boolean compare(Reader input1, Reader input2)
			throws IOException {
		if (!(input1 instanceof BufferedReader)) {
			input1 = new BufferedReader(input1);
		}
		if (!(input2 instanceof BufferedReader)) {
			input2 = new BufferedReader(input2);
		}

		int ch = input1.read();
		while (ch != -1) {
			int ch2 = input2.read();
			if (ch != ch2) {
				return false;
			}
			ch = input1.read();
		}
		int ch2 = input2.read();
		return ch2 == -1;
	}

	// content equals
	// -----------------------------------------------------------------------
	/**
	 * Compare the contents of two Streams to determine if they are equal or
	 * not.
	 * <p>
	 * This method buffers the input internally using
	 * <code>BufferedInputStream</code> if they are not already buffered.
	 * 
	 * @param input1
	 *            the first stream
	 * @param input2
	 *            the second stream
	 * @return true if the content of the streams are equal or they both don't
	 *         exist, false otherwise
	 * @throws NullPointerException
	 *             if either input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static boolean contentEquals(InputStream input1, InputStream input2)
			throws IOException {
		if (!(input1 instanceof BufferedInputStream)) {
			input1 = new BufferedInputStream(input1);
		}
		if (!(input2 instanceof BufferedInputStream)) {
			input2 = new BufferedInputStream(input2);
		}

		int ch = input1.read();
		while (-1 != ch) {
			int ch2 = input2.read();
			if (ch != ch2) {
				return false;
			}
			ch = input1.read();
		}

		int ch2 = input2.read();
		return ch2 == -1;
	}

	/**
	 * Compare the contents of two Readers to determine if they are equal or
	 * not.
	 * <p>
	 * This method buffers the input internally using
	 * <code>BufferedReader</code> if they are not already buffered.
	 * 
	 * @param input1
	 *            the first reader
	 * @param input2
	 *            the second reader
	 * @return true if the content of the readers are equal or they both don't
	 *         exist, false otherwise
	 * @throws NullPointerException
	 *             if either input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static boolean contentEquals(Reader input1, Reader input2)
			throws IOException {
		if (!(input1 instanceof BufferedReader)) {
			input1 = new BufferedReader(input1);
		}
		if (!(input2 instanceof BufferedReader)) {
			input2 = new BufferedReader(input2);
		}

		int ch = input1.read();
		while (-1 != ch) {
			int ch2 = input2.read();
			if (ch != ch2) {
				return false;
			}
			ch = input1.read();
		}

		int ch2 = input2.read();
		return ch2 == -1;
	}

	/**
	 * Copies input stream to output stream using buffer. Streams dont have to
	 * be wrapped to buffered, since copying is already optimizied.
	 */
	public static int copy(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[IoBufferSize];
		int count = 0;
		int read;
		while (true) {
			read = input.read(buffer, 0, IoBufferSize);
			if (read == -1) {
				break;
			}
			output.write(buffer, 0, read);
			count += read;
		}
		return count;
	}

	/**
	 * Copies specified number of bytes from input stream to output stream using
	 * buffer.
	 */
	public static int copy(InputStream input, OutputStream output, int byteCount)
			throws IOException {
		byte buffer[] = new byte[IoBufferSize];
		int count = 0;
		int read;
		while (byteCount > 0) {
			if (byteCount < IoBufferSize) {
				read = input.read(buffer, 0, byteCount);
			} else {
				read = input.read(buffer, 0, IoBufferSize);
			}
			if (read == -1) {
				break;
			}
			byteCount -= read;
			count += read;
			output.write(buffer, 0, read);
		}
		return count;
	}

	/**
	 * Copies input stream to writer using buffer.
	 */
	public static void copy(InputStream input, Writer output)
			throws IOException {
		copy(input, output, Encoding);
	}

	/**
	 * Copies specified number of bytes from input stream to writer using
	 * buffer.
	 */
	public static void copy(InputStream input, Writer output, int byteCount)
			throws IOException {
		copy(input, output, Encoding, byteCount);
	}

	/**
	 * Copies input stream to writer using buffer and specified Encoding.
	 */
	public static void copy(InputStream input, Writer output, String encoding)
			throws IOException {
		copy(new InputStreamReader(input, encoding), output);
	}

	/**
	 * Copies specified number of bytes from input stream to writer using buffer
	 * and specified Encoding.
	 */
	public static void copy(InputStream input, Writer output, String encoding,
			int byteCount) throws IOException {
		copy(new InputStreamReader(input, encoding), output, byteCount);
	}

	/**
	 * Copies reader to output stream using buffer.
	 */
	public static void copy(Reader input, OutputStream output)
			throws IOException {
		copy(input, output, Encoding);
	}

	/**
	 * Copies specified number of characters from reader to output stream using
	 * buffer.
	 */
	public static void copy(Reader input, OutputStream output, int charCount)
			throws IOException {
		copy(input, output, Encoding, charCount);
	}

	/**
	 * Copies reader to output stream using buffer and specified Encoding.
	 */
	public static void copy(Reader input, OutputStream output, String encoding)
			throws IOException {
		Writer out = new OutputStreamWriter(output, encoding);
		copy(input, out);
		out.flush();
	}

	/**
	 * Copies specified number of characters from reader to output stream using
	 * buffer and specified Encoding.
	 */
	public static void copy(Reader input, OutputStream output, String encoding,
			int charCount) throws IOException {
		Writer out = new OutputStreamWriter(output, encoding);
		copy(input, out, charCount);
		out.flush();
	}

	/**
	 * Copies reader to writer using buffer. Streams dont have to be wrapped to
	 * buffered, since copying is already optimized.
	 */
	public static int copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[IoBufferSize];
		int count = 0;
		int read;
		while ((read = input.read(buffer, 0, IoBufferSize)) >= 0) {
			output.write(buffer, 0, read);
			count += read;
		}
		output.flush();
		return count;
	}

	/**
	 * Copies specified number of characters from reader to writer using buffer.
	 */
	public static int copy(Reader input, Writer output, int charCount)
			throws IOException {
		char buffer[] = new char[IoBufferSize];
		int count = 0;
		int read;
		while (charCount > 0) {
			if (charCount < IoBufferSize) {
				read = input.read(buffer, 0, charCount);
			} else {
				read = input.read(buffer, 0, IoBufferSize);
			}
			if (read == -1) {
				break;
			}
			charCount -= read;
			count += read;
			output.write(buffer, 0, read);
		}
		return count;
	}

	public static void copyBytes(final byte[] bytes,
			final OutputStream outputStream) throws IOException {
		outputStream.write(bytes, 0, bytes.length);
		outputStream.flush();
	}

	public static int copyStream(final InputStream inputStream,
			final OutputStream outputStream) throws IOException {
		if (inputStream == null) {
			return 0;
		}
		int result = 0;
		final byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
		for (;;) {
			final int numRead = inputStream.read(buf);
			if (numRead == -1) {
				break;
			}
			outputStream.write(buf, 0, numRead);
			result += numRead;
		}
		outputStream.flush();
		return result;
	}

	// Getter
	public static byte[] getStreamBytes(final InputStream inputStream)
			throws IOException {
		if (inputStream == null) {
			return null;
		}
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copyStream(inputStream, outputStream);
		return outputStream.toByteArray();
	}

	public static String getStringFromInputStream(final InputStream inputStream)
			throws IOException {
		final byte[] bytes = getStreamBytes(inputStream);
		return bytes == null ? null : new String(bytes);
	}

	public static String getStringFromReader(final Reader reader)
			throws IOException {
		BufferedReader bufferedreader = null;
		try {
			bufferedreader = new BufferedReader(reader);
			final StringWriter sw = new StringWriter();
			final PrintWriter writer = new PrintWriter(sw);
			String s;
			while ((s = bufferedreader.readLine()) != null) {
				writer.println(s);
			}
			writer.flush();
			return sw.toString();
		} finally {
			if (bufferedreader != null) {
				bufferedreader.close();
			}
		}
	}

	public static byte[] marshal(final Serializable serializable)
			throws IOException {
		return IOUtils.marshal(serializable, false);
	}

	public static byte[] marshal(final Serializable serializable,
			final boolean zip) throws IOException {
		if (serializable == null) {
			return null;
		}
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(serializable);
			if (zip) {
				return IOUtils.zipBytes(bos.toByteArray());
			} else {
				return bos.toByteArray();
			}
		} finally {
			bos.close();
		}
	}

	/**
	 * Reads all available bytes from InputStream as a byte array. Uses
	 * <code>in.availiable()</code> to determine the size of input stream. This
	 * is the fastest method for reading input stream to byte array, but depends
	 * on stream implementation of <code>available()</code>. Buffered
	 * internally.
	 */
	public static byte[] readAvailableBytes(InputStream in) throws IOException {
		int l = in.available();
		byte byteArray[] = new byte[l];
		int i = 0, j;
		while (i < l && (j = in.read(byteArray, i, l - i)) >= 0) {
			i += j;
		}
		if (i < l) {
			throw new IOException(
					"Could not completely read from input stream.");
		}
		return byteArray;
	}

	public static byte[] readBytes(InputStream input) throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static byte[] readBytes(InputStream input, int byteCount)
			throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output, byteCount);
		return output.toByteArray();
	}

	public static byte[] readBytes(Reader input) throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static byte[] readBytes(Reader input, int byteCount)
			throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output, byteCount);
		return output.toByteArray();
	}

	public static byte[] readBytes(Reader input, String encoding)
			throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output, encoding);
		return output.toByteArray();
	}

	public static byte[] readBytes(Reader input, String encoding, int byteCount)
			throws IOException {
		FastByteArrayOutputStream output = new FastByteArrayOutputStream();
		copy(input, output, encoding, byteCount);
		return output.toByteArray();
	}

	public static char[] readChars(InputStream input) throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output);
		return output.toCharArray();
	}

	public static char[] readChars(InputStream input, int charCount)
			throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output, charCount);
		return output.toCharArray();
	}

	public static char[] readChars(InputStream input, String encoding)
			throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output, encoding);
		return output.toCharArray();
	}

	public static char[] readChars(InputStream input, String encoding,
			int charCount) throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output, encoding, charCount);
		return output.toCharArray();
	}

	public static char[] readChars(Reader input) throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output);
		return output.toCharArray();
	}

	public static char[] readChars(Reader input, int charCount)
			throws IOException {
		FastCharArrayWriter output = new FastCharArrayWriter();
		copy(input, output, charCount);
		return output.toCharArray();
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a list of Strings, one
	 * entry per line, using the default character encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(InputStream input) throws IOException {
		InputStreamReader reader = new InputStreamReader(input);
		return readLines(reader);
	}

	/**
	 * Get the contents of a <code>Reader</code> as a list of Strings, one entry
	 * per line.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		List<String> list = new ArrayList<String>();
		String line = reader.readLine();
		while (line != null) {
			list.add(line);
			line = reader.readLine();
		}
		return list;
	}

	/**
	 * Reads a stream as a string.
	 * 
	 * @param in
	 *            The input stream
	 * @return The string
	 * @throws IOException
	 */
	public static String readString(final InputStream in) throws IOException {
		return readString(new BufferedReader(new InputStreamReader(in)));
	}

	/**
	 * Reads a string using a character Encoding.
	 * 
	 * @param in
	 *            The input
	 * @param encoding
	 *            The character Encoding of the input data
	 * @return The string
	 * @throws IOException
	 */
	public static String readString(final InputStream in,
			final CharSequence encoding) throws IOException {
		return readString(new BufferedReader(new InputStreamReader(in,
				encoding.toString())));
	}

	/**
	 * Reads all input from a reader into a string.
	 * 
	 * @param in
	 *            The input
	 * @return The string
	 * @throws IOException
	 */
	public static String readString(final Reader in) throws IOException {
		final StringBuffer buffer = new StringBuffer(2048);
		int value;

		while ((value = in.read()) != -1) {
			buffer.append((char) value);
		}

		return buffer.toString();
	}

	public static void removeAll(final File dir) throws IOException {
		IOUtils.removeAll(dir, true);
	}

	public static void removeAll(final File dir, final boolean inc)
			throws IOException {
		if (!dir.exists()) {
			return;
		}
		if (!dir.isDirectory()) {
			IOUtils.removeDirectory(dir);
			return;
		}
		final String[] list = dir.list();
		if (list != null) {
			for (final String element : list) {
				final File child = new File(dir, element);
				IOUtils.removeAll(child);
			}
		}
		if (inc) {
			IOUtils.removeDirectory(dir);
		}
	}

	public static long sizeOfDirectory(final File directory) {
		if (!directory.exists()) {
			return 0;
		}
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory
					+ " is not a directory.");
		}
		long size = 0;
		final File files[] = directory.listFiles();
		if (files == null) {
			return 0;
		}
		for (final File file : files) {
			if (file.isDirectory()) {
				size += IOUtils.sizeOfDirectory(file);
			} else {
				size += file.length();
			}
		}
		return size;
	}

	/**
	 * 为一个输入流包裹一个缓冲流。如果这个输入流本身就是缓冲流，则直接返回
	 * 
	 * @param ins
	 *            输入流。
	 * @return 缓冲输入流
	 */
	public static BufferedInputStream toBufferedStream(InputStream ins) {
		if (ins instanceof BufferedInputStream) {
			return (BufferedInputStream) ins;
		}
		return new BufferedInputStream(ins);
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from
	 * @return the requested byte array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	/**
	 * Get the contents of a <code>Reader</code> as a <code>byte[]</code> using
	 * the default character Encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from
	 * @return the requested byte array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static byte[] toByteArray(Reader input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	/**
	 * Get the contents of a <code>Reader</code> as a <code>byte[]</code> using
	 * the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @return the requested byte array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static byte[] toByteArray(Reader input, String encoding)
			throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output, encoding);
		return output.toByteArray();
	}

	// read char[]
	// -----------------------------------------------------------------------
	/**
	 * Get the contents of an <code>InputStream</code> as a character array
	 * using the default character Encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param is
	 *            the <code>InputStream</code> to read from
	 * @return the requested character array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static char[] toCharArray(InputStream is) throws IOException {
		CharArrayWriter output = new CharArrayWriter();
		copy(is, output);
		return output.toCharArray();
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a character array
	 * using the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param is
	 *            the <code>InputStream</code> to read from
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @return the requested character array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static char[] toCharArray(InputStream is, String encoding)
			throws IOException {
		CharArrayWriter output = new CharArrayWriter();
		copy(is, output, encoding);
		return output.toCharArray();
	}

	/**
	 * Get the contents of a <code>Reader</code> as a character array.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from
	 * @return the requested character array
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static char[] toCharArray(Reader input) throws IOException {
		CharArrayWriter sw = new CharArrayWriter();
		copy(input, sw);
		return sw.toCharArray();
	}

	public static String toFileSize(final long longSize, final int decimalPos) {
		final NumberFormat fmt = NumberFormat.getNumberInstance();
		if (decimalPos >= 0) {
			fmt.setMaximumFractionDigits(decimalPos);
		}
		final double size = longSize;
		double val = size / (1024 * 1024);
		if (val > 1) {
			return fmt.format(val).concat(" MB");
		}
		val = size / 1024;
		if (val > 10) {
			return fmt.format(val).concat(" KB");
		}
		return fmt.format(val).concat(" bytes");
	}

	// ---------------------------------------------------------------- read
	// bytes

	/**
	 * 判断并移除UTF-8的BOM头
	 */
	public static InputStream toPureUTF8(InputStream in) {
		try {
			if (in.available() == -1) {
				return in;
			}
			PushbackInputStream pis = new PushbackInputStream(in, 3);
			byte[] header = new byte[3];
			int len = pis.read(header, 0, 3);
			if (len < 1) {
				return in;
			}
			if (header[0] != UTF_BOM[0] || header[1] != UTF_BOM[1]
					|| header[2] != UTF_BOM[2]) {
				pis.unread(header, 0, len);
			}
			return pis;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// read toString
	// -----------------------------------------------------------------------
	/**
	 * Get the contents of an <code>InputStream</code> as a String using the
	 * default character Encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from
	 * @return the requested String
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static String toString(InputStream input) throws IOException {
		StringWriter sw = new StringWriter();
		copy(input, sw);
		return sw.toString();
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a String using the
	 * specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @return the requested String
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static String toString(InputStream input, String encoding)
			throws IOException {
		StringWriter sw = new StringWriter();
		copy(input, sw, encoding);
		return sw.toString();
	}

	/**
	 * Get the contents of a <code>Reader</code> as a String.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from
	 * @return the requested String
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static String toString(Reader input) throws IOException {
		StringWriter sw = new StringWriter();
		copy(input, sw);
		return sw.toString();
	}

	public static Serializable unmarshal(final byte[] b) throws IOException,
			ClassNotFoundException {
		return IOUtils.unmarshal(b, false);
	}

	public static Serializable unmarshal(final byte[] b, final boolean zip)
			throws IOException, ClassNotFoundException {
		if (b == null || b.length == 0) {
			return null;
		}
		ObjectInputStream ois = null;
		try {
			if (zip) {
				final byte[] bytes = IOUtils.unzipBytes(b);
				if (bytes != null) {
					ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
					return (Serializable) ois.readObject();
				} else {
					return null;
				}
			} else {
				ois = new ObjectInputStream(new ByteArrayInputStream(b));
				return (Serializable) ois.readObject();
			}
		} finally {
			if (ois != null) {
				ois.close();
			}
		}
	}

	public static byte[] unzipBytes(final byte[] b) throws IOException {
		if (b == null || b.length == 0) {
			return b;
		}
		final ByteArrayInputStream bis = new ByteArrayInputStream(b);
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(bis);
			if (zis.getNextEntry() != null) {
				int count;
				final byte data[] = new byte[2048];
				final ByteArrayOutputStream bos = new ByteArrayOutputStream();
				while ((count = zis.read(data, 0, 2048)) != -1) {
					bos.write(data, 0, count);
				}
				return bos.toByteArray();
			} else {
				return null;
			}
		} finally {
			if (zis != null) {
				zis.close();
			}
		}
	}

	// ---------------------------------------------------------------- read
	// chars

	// write byte[]
	// -----------------------------------------------------------------------
	/**
	 * Writes bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
	 * 
	 * @param data
	 *            the byte array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(byte[] data, OutputStream output)
			throws IOException {
		if (data != null) {
			output.write(data);
		}
	}

	/**
	 * Writes bytes from a <code>byte[]</code> to chars on a <code>Writer</code>
	 * using the default character Encoding of the platform.
	 * <p>
	 * This method uses {@link String#String(byte[])}.
	 * 
	 * @param data
	 *            the byte array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>Writer</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(byte[] data, Writer output) throws IOException {
		if (data != null) {
			output.write(new String(data));
		}
	}

	/**
	 * Writes bytes from a <code>byte[]</code> to chars on a <code>Writer</code>
	 * using the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method uses {@link String#String(byte[], String)}.
	 * 
	 * @param data
	 *            the byte array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>Writer</code> to write to
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(byte[] data, Writer output, String encoding)
			throws IOException {
		if (data != null) {
			if (encoding == null) {
				write(data, output);
			} else {
				output.write(new String(data, encoding));
			}
		}
	}

	/**
	 * Writes chars from a <code>char[]</code> to bytes on an
	 * <code>OutputStream</code>.
	 * <p>
	 * This method uses {@link String#String(char[])} and
	 * {@link String#getBytes()}.
	 * 
	 * @param data
	 *            the char array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(char[] data, OutputStream output)
			throws IOException {
		if (data != null) {
			output.write(new String(data).getBytes());
		}
	}

	/**
	 * Writes chars from a <code>char[]</code> to bytes on an
	 * <code>OutputStream</code> using the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method uses {@link String#String(char[])} and
	 * {@link String#getBytes(String)}.
	 * 
	 * @param data
	 *            the char array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(char[] data, OutputStream output, String encoding)
			throws IOException {
		if (data != null) {
			if (encoding == null) {
				write(data, output);
			} else {
				output.write(new String(data).getBytes(encoding));
			}
		}
	}

	// write char[]
	// -----------------------------------------------------------------------
	/**
	 * Writes chars from a <code>char[]</code> to a <code>Writer</code> using
	 * the default character Encoding of the platform.
	 * 
	 * @param data
	 *            the char array to write, do not modify during output, null
	 *            ignored
	 * @param output
	 *            the <code>Writer</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(char[] data, Writer output) throws IOException {
		if (data != null) {
			output.write(data);
		}
	}

	/**
	 * Writes chars from a <code>String</code> to bytes on an
	 * <code>OutputStream</code> using the default character Encoding of the
	 * platform.
	 * <p>
	 * This method uses {@link String#getBytes()}.
	 * 
	 * @param data
	 *            the <code>String</code> to write, null ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(String data, OutputStream output)
			throws IOException {
		if (data != null) {
			output.write(data.getBytes());
		}
	}

	/**
	 * Writes chars from a <code>String</code> to bytes on an
	 * <code>OutputStream</code> using the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method uses {@link String#getBytes(String)}.
	 * 
	 * @param data
	 *            the <code>String</code> to write, null ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(String data, OutputStream output, String encoding)
			throws IOException {
		if (data != null) {
			if (encoding == null) {
				write(data, output);
			} else {
				output.write(data.getBytes(encoding));
			}
		}
	}

	// write String
	// -----------------------------------------------------------------------
	/**
	 * Writes chars from a <code>String</code> to a <code>Writer</code>.
	 * 
	 * @param data
	 *            the <code>String</code> to write, null ignored
	 * @param output
	 *            the <code>Writer</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(String data, Writer output) throws IOException {
		if (data != null) {
			output.write(data);
		}
	}

	// ---------------------------------------------------------------- compare
	// content

	/**
	 * Writes chars from a <code>AppendingStringBuffer</code> to bytes on an
	 * <code>OutputStream</code> using the default character Encoding of the
	 * platform.
	 * <p>
	 * This method uses {@link String#getBytes()}.
	 * 
	 * @param data
	 *            the <code>AppendingStringBuffer</code> to write, null ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(StringBuffer data, OutputStream output)
			throws IOException {
		if (data != null) {
			output.write(data.toString().getBytes());
		}
	}

	/**
	 * Writes chars from a <code>AppendingStringBuffer</code> to bytes on an
	 * <code>OutputStream</code> using the specified character Encoding.
	 * <p>
	 * Character Encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method uses {@link String#getBytes(String)}.
	 * 
	 * @param data
	 *            the <code>AppendingStringBuffer</code> to write, null ignored
	 * @param output
	 *            the <code>OutputStream</code> to write to
	 * @param encoding
	 *            the Encoding to use, null means platform default
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(StringBuffer data, OutputStream output,
			String encoding) throws IOException {
		if (data != null) {
			if (encoding == null) {
				write(data, output);
			} else {
				output.write(data.toString().getBytes(encoding));
			}
		}
	}

	// ---------------------------------------------------------------- silent
	// close

	// write AppendingStringBuffer
	// -----------------------------------------------------------------------
	/**
	 * Writes chars from a <code>AppendingStringBuffer</code> to a
	 * <code>Writer</code>.
	 * 
	 * @param data
	 *            the <code>AppendingStringBuffer</code> to write, null ignored
	 * @param output
	 *            the <code>Writer</code> to write to
	 * @throws NullPointerException
	 *             if output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since 1.1
	 */
	public static void write(StringBuffer data, Writer output)
			throws IOException {
		if (data != null) {
			output.write(data.toString());
		}
	}

	public static byte[] zipBytes(final byte[] b) throws IOException {
		return IOUtils.zipBytes(b, "default-obj");
	}

	public static byte[] zipBytes(final byte[] b, final String zipEntryName)
			throws IOException {
		if (b == null || b.length == 0) {
			return b;
		}
		final ByteArrayOutputStream ret = new ByteArrayOutputStream();
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(ret);
			zos.putNextEntry(new ZipEntry(zipEntryName));
			zos.write(b);
		} finally {
			if (zos != null) {
				zos.close();
			}
		}
		return ret.toByteArray();
	}

	// ---------------------------------------------------------------- copy

	private static void removeDirectory(final File dir) throws IOException {
		if (!dir.delete()) {
			throw new IOException("Can't delete the file [" + dir + "]");
		}
	}
}
