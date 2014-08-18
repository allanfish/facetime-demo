package com.conlect.oatos.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Text file source
 * 
 * @author jinkerjiang
 * 
 */
public class TextFileSource {

	public final static String TARGET_TEXT_File_SUFFIX = ".cache.html";
	public final static String HTML_SERVLET_PREFIX = "html";
	public final static String HTML_AS_JS_SERVLET_PREFIX = "htmlasjs";

	public static final String TAG_SCRIPT_END = "</script>";
	public static final String TAG_SCRIPT_START = "<script>";

	public static String PropertySize = "size";
	public static String PropertyTotal = "total";
	public static String endMark = "end";
	public static String startMark = "start";
	public static String PropertyIndex = "index";
	public static String PropertyContent = "content";
	public final static String jsFunctionName = "$wnd.progressBar.download";

	private static TextFileSource instance;

	private static String LOCK = "lock";

	public static TextFileSource getInstance() {
		if (instance == null) {
			synchronized (LOCK) {
				instance = new TextFileSource();
			}
		}
		return instance;
	}

	/**
	 * Save text file path and it's string array
	 */
	private Map<String, TextFileInfo> pathToFileInfoMap;

	private TextFileSource() {
		pathToFileInfoMap = new HashMap<String, TextFileInfo>();
	}

	/**
	 * Get string array the path associated
	 * 
	 * @param path
	 * @return
	 */
	public String[] get(String path) {
		TextFileInfo textFileInfo = pathToFileInfoMap.get(path);
		if (textFileInfo != null) {
			return textFileInfo.getFrags();
		}
		return null;
	}

	/**
	 * Get fragment's max size the path associated
	 * 
	 * @param path
	 * @return
	 */
	public int getFragMaxSize(String path) {
		TextFileInfo textFileInfo = pathToFileInfoMap.get(path);
		if (textFileInfo != null) {
			return textFileInfo.getFragMaxSize();
		}
		return -1;
	}

	/**
	 * Save and set string array of the path
	 * 
	 * @param path
	 * @param frags
	 */
	public void put(String path, String[] frags) {
		pathToFileInfoMap.put(path, new TextFileInfo(frags, path));
	}

	/**
	 * Text file's information
	 * 
	 * @author jinkerjiang
	 * 
	 */
	public class TextFileInfo {
		private int fragMaxSize;// in bit
		private String[] frags;
		private String path;

		public TextFileInfo(String[] frags, String path) {
			setFrags(frags);
			setPath(path);
		}

		public int getFragMaxSize() {
			return fragMaxSize;
		}

		public String[] getFrags() {
			return frags;
		}

		public void setFrags(String[] frags) {
			this.frags = frags;

			// calculate fragment max size
			if (frags != null) {
				int maxSize = 0;
				int fragSize = 0;
				for (String frag : frags) {
					fragSize = frag.length();
					maxSize = fragSize > maxSize ? fragSize : maxSize;
				}
				fragMaxSize = maxSize;
			}
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}
}
