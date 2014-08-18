package com.facetime.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MyEclipse9 插件配置代码生成器 <br>
 * 1. 将插件解压，放到任意目录 <br>
 * 2. 然后将上面代码中的插件安装目录设置为插件解压到的目录 <br>
 * 3. 运行代码，将运行打印出来的信息复制出来添加到: <br>
 * MyEclipse_HOME\configuration\org.eclipse.equinox.simpleconfigurator
 * \bundles.info 文件最后，重新启动MyEclipse, 就OK了
 *
 */
public class PluginConfigCreator {

	private static final String PLUGIN_BASEDIR = "D:\\DevTools\\eclipse\\eclipse_plugins\\";
	private static final String PLUGIN_DIRS = "easyexplorer,findbug-1.3.9,jad-eclipse,jdepend-1.2.3,pmd-4.2.5,subclipse-1.8.5,ucdetector_1.10.0,jinto-0.13.5";

	public PluginConfigCreator() {
	}

	public static void main(String[] args) {
		printAll();
	}

	public static void printAll() {
		/* 你的插件的安装目录 */
		String[] pluginDirs = PLUGIN_DIRS.split(",");
		for (String pluginDir : pluginDirs) {
			new PluginConfigCreator().print(PLUGIN_BASEDIR + pluginDir);
			System.out.println("");
		}
	}

	public List<String> getFileList(String path) {
		path = getFormatPath(path);
		path = path + "/";
		File filePath = new File(path);
		if (!filePath.isDirectory())
			return null;
		String[] filelist = filePath.list();
		List<String> filelistFilter = new ArrayList<String>();
		for (String element : filelist) {
			String tempfilename = getFormatPath(path + element);
			filelistFilter.add(tempfilename);
		}
		return filelistFilter;
	}

	public String getFormatPath(String path) {
		path = path.replaceAll("////", "/");
		path = path.replaceAll("//", "/");
		return path;
	}

	public String getString(Object object) {
		if (object == null)
			return "";
		return String.valueOf(object);
	}

	public void print(String path) {
		List<String> list = getFileList(path);
		if (list == null)
			return;
		int length = list.size();
		for (int i = 0; i < length; i++) {
			String result = "";
			String thePath = getFormatPath(getString(list.get(i)));
			File file = new File(thePath);
			if (file.isDirectory()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					print(thePath);
					continue;
				}
				String[] filenames = fileName.split("_");
				String filename1 = filenames[0];
				String filename2 = filenames[1];
				result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + "//,4,false";
				System.out.println(result);
			} else if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0)
					continue;
				int last = fileName.lastIndexOf("_");// 最后一个下划线的位置
				String filename1 = fileName.substring(0, last);
				String filename2 = fileName.substring(last + 1, fileName.length() - 4);
				result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + ",4,false";
				System.out.println(result);
			}
		}
	}
}
