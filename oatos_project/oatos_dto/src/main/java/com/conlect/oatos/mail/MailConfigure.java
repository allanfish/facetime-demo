package com.conlect.oatos.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.conlect.oatos.utils.StringUtils;

public class MailConfigure {

	public static Properties props = new Properties();
	public static Map<String, Properties> defaultConfigs = new HashMap<String, Properties>();

	static {
		try {
			props.load(MailConfigure.class.getClassLoader()
					.getResourceAsStream(
							"com/conlect/oatos/mail/mail.properties"));
			initMailConfigs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void initMailConfigs() {
		String[] mailAccepts = StringUtils.split(
				props.getProperty("mail.accept"), ",");
		for (String mailType : mailAccepts) {
			Properties pop3Config = readMailConfig(mailType, false);
			Properties imapConfig = readMailConfig(mailType, true);
			defaultConfigs.put(mailType + ".pop3", pop3Config);
			defaultConfigs.put(mailType + ".imap", imapConfig);
		}
	}

	private static Properties readMailConfig(String mailType, boolean isIMAP) {
		Properties mailConfig = new Properties();
		for (Entry<Object, Object> entry : props.entrySet()) {
			String key = entry.getKey().toString();
			boolean notContain = isIMAP ? key.indexOf("pop3") == -1 : key
					.indexOf("imap") == -1;
			if (key.startsWith(mailType) && notContain) {
				mailConfig.put(key.substring(mailType.length() + 1),
						entry.getValue());
			}
		}
		if (mailConfig.size() > 0)
			mailConfig.put("mail.store.protocol", isIMAP ? "imaps" : "pop3");
		return mailConfig;
	}

	public static final Properties getDefaultConfig(String mailType,
			boolean isIMAP) {
		String sendPropocal = isIMAP ? ".imap" : ".pop3";
		return defaultConfigs.get(mailType + sendPropocal);
	}
}
