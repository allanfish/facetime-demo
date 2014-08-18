package commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * freemarker标准模版类
 */
public class FreemarkerHelper {

	private final Configuration cfg = new Configuration();

	public FreemarkerHelper() {
		this(FreemarkerHelper.class);
	}

	public FreemarkerHelper(Class<?> templateLoadClass) {
		cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), ""));
		cfg.setClassForTemplateLoading(templateLoadClass, "");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
	}

	public static String contentAfterCreate(Class<?> templateLoadClass, String ftlFile, String destFile,
			Map<String, ?> dataMap) {
		create(templateLoadClass, ftlFile, destFile, dataMap);
		StringBuilder result = new StringBuilder("");
		BufferedReader reader = null;
		try {
			File dest = new File(destFile);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(dest)));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				result.append(temp).append("\n");
			}
			dest.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	public static void create(Class<?> templateLoadClass, String ftlFile, String destFile, Map<String, ?> dataMap) {
		FreemarkerHelper template = new FreemarkerHelper(templateLoadClass);
		Writer out = null;
		try {
			Template temp = template.getCfg().getTemplate(ftlFile);
			out = new FileWriter(destFile);
			temp.process(dataMap, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void create(String ftlFile, String destFile, Map<String, ?> dataMap) {
		create(FreemarkerHelper.class, ftlFile, destFile, dataMap);
	}

	public Configuration getCfg() {
		return cfg;
	}
}
