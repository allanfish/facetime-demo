package com.conlect.oatos.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 文本文件读取器<br>
 * <br>
 * Text file reader
 * 
 * @author jinkerjiang
 * 
 */
public class TextFileReader {
	/**
	 * Read text file, and convert to a string array
	 * 
	 * @param textFile
	 * @return
	 */
	private static String[] read(File textFile) throws FileNotFoundException {
		return read(textFile, 1024);
	}

	/**
	 * Read text file, and convert to a string array
	 * 
	 * @param textFile
	 * @param fragSize
	 * @return
	 */
	private static String[] read(File textFile, int fragSize)
			throws FileNotFoundException {
		List<String> result = new ArrayList<String>();

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(textFile));

			char[] frag = new char[fragSize];
			int length = 0;
			while ((length = bufferedReader.read(frag)) != -1) {
				result.add(new String(frag).substring(0, length));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toArray(new String[0]);
	}

	/**
	 * 转换.html文件为字符串数组,并且在每个{@code <script>}结尾部分添加进度调用语句<br>
	 * <br>
	 * Convert html file as string array, and add an progress function invoking
	 * at end of every script tag's
	 * 
	 * @param textFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String[] renderWithProgressInvoker(File textFile)
			throws FileNotFoundException {
		List<String> result = new ArrayList<String>();

		StringBuilder all = new StringBuilder();

		String[] original = read(textFile);
		if (original != null) {
			for (String string : original) {
				all.append(string);
			}
		}

		int total = all.length();
		int fromIndex = 0;
		int find = 0;
		int offset = TextFileSource.TAG_SCRIPT_END.length();
		while ((find = all.indexOf(TextFileSource.TAG_SCRIPT_END, fromIndex)) != -1) {
			StringBuilder builder = new StringBuilder();

			if (fromIndex == 0) {
				builder.append(all.substring(fromIndex, find + offset));
				builder.append(buildProgressJsFunctionInvoking(null, find
						+ offset - fromIndex, total));
			} else {
				builder.append(buildProgressJsFunctionInvoking(null, find
						+ offset - fromIndex, total));
				builder.append(all.substring(fromIndex, find + offset));
			}

			result.add(builder.toString());

			fromIndex = find + offset;
		}

		// complete html at the end of results
		String end = result.get(result.size() - 1);
		end = end + "</body></html>";
		result.set(result.size() - 1, end);

		return result.toArray(new String[0]);
	}

	/**
	 * 组装javascript进度调用函数语句,如<br>
	 * $wnd.progressBar.download({
	 * content:'...',index:'##',total:'##',size:'##'});<br>
	 * <br>
	 * 
	 * @param index
	 * @param size
	 * @param total
	 * @return
	 */
	private static String buildProgressJsFunctionInvoking(String index,
			int size, int total) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(TextFileSource.TAG_SCRIPT_START);
		stringBuilder.append("try{");
		stringBuilder.append(TextFileSource.jsFunctionName);
		stringBuilder.append("({");
		stringBuilder.append(TextFileSource.PropertyContent).append(":'',");
		stringBuilder.append(TextFileSource.PropertyIndex).append(":\"")
				.append(index).append("\",");
		stringBuilder.append(TextFileSource.PropertyTotal).append(":\"")
				.append(total).append("\",");
		stringBuilder.append(TextFileSource.PropertySize).append(":\"")
				.append(size).append("\"");
		stringBuilder.append("});");
		stringBuilder.append("}catch(e){}");
		stringBuilder.append(TextFileSource.TAG_SCRIPT_END);

		return stringBuilder.toString();
	}

	/**
	 * 将*.cache.html 文件中转换成js文件
	 * 
	 * @param path
	 *            the file path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String buildJSString(String path)
			throws FileNotFoundException {
		String[] strs = read(new File(path));

		StringBuilder content = new StringBuilder();

		for (String string : strs) {
			content.append(string);
		}

		String result = content.toString();
		StringBuilder resultBuilder = new StringBuilder();

		resultBuilder.append("(function(){");
		resultBuilder.append("var frame = document.createElement('iframe');");
		resultBuilder.append("frame.id='ChatClientWindow';");
		resultBuilder
				.append("frame.style.cssText = 'position:absolute;width:0;height:0;border:none';");
		resultBuilder.append("document.body.appendChild(frame);");
		resultBuilder.append("frame.contentWindow.document.write(\""
				+ StringEscapeUtils.escapeJavaScript(result) + "\");");
		resultBuilder.append("})();");

		return resultBuilder.toString();
	}

	/**
	 * 将nocache.js 文件改成立即装载内容
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String renderQuickLoadNocacheJs(String path)
			throws FileNotFoundException {
		String[] strs = read(new File(path));

		StringBuilder content = new StringBuilder();

		for (String string : strs) {
			content.append(string);
		}

		String result = content.toString();
		// 以下替换只对GWT2.4有效，如升级GWT，请检查是否可行，再行修改
		result = result
				.replace(
						"var H;function I(){if(!H){H=true;var a=m.createElement(wb);a.src=xb;a.id=P;a.style.cssText=yb;a.tabIndex=-1;m.body.appendChild(a);n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:W,millis:(new Date).getTime(),type:zb});a.contentWindow.location.replace(s+K)}}",
						"var H;function I(){if(!H){H=true;/*var a=m.createElement(wb);a.src=xb;a.id=P;a.style.cssText=yb;a.tabIndex=-1;m.body.appendChild(a);n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:W,millis:(new Date).getTime(),type:zb});a.contentWindow.location.replace(s+K)*/var script=m.createElement(\"script\");script.type='text/javascript';script.src=s+K;m.body.appendChild(script);n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:W,millis:(new Date).getTime(),type:zb});}}");
		result = result
				.replace(
						"if(m.addEventListener){m.addEventListener(gc,function(){I();N()},false)}var M=setInterval(function(){if(/loaded|complete/.test(m.readyState)){I();N()}},50);n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:R,millis:(new Date).getTime(),type:X});n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:Ob,millis:(new Date).getTime(),type:S});m.write(hc)}",
						"if(m.addEventListener){m.addEventListener(gc,function(){I();N()},false)}var M=setInterval(function(){if(/loaded|complete/.test(m.readyState)){I();N()}},50);ChatClientWindow.onInjectionDone('ChatClientWindow');I();N();n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:R,millis:(new Date).getTime(),type:X});n&&n({moduleName:P,sessionId:o,subSystem:Q,evtGroup:Ob,millis:(new Date).getTime(),type:S});/*m.write(hc)*/}");

		return result;
	}
}
