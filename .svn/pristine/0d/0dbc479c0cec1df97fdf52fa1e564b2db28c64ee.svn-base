package com.qycloud.oatos.server.logic.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qycloud.oatos.server.domain.entity.MailAccount;
import com.qycloud.oatos.server.util.BllLogger;

/**
 * 邮箱服务器连接
 * 
 * @author jinkerjiang
 * 
 */
@Service("MailServerConnector")
public class MailServerConnector {
	private final static Logger logger = BllLogger.getLogger();

	private Map<String, Store> mailAddressToStoreMap = new HashMap<String, Store>();

	private static MailServerConnector instance = new MailServerConnector();

	public static MailServerConnector getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param mailAccountModel
	 * @return
	 * @throws MessagingException
	 */
	public Store getStore(MailAccount mailAccountModel) throws MessagingException {

		Store store = mailAddressToStoreMap.get(mailAccountModel.getMailAddress());

		if (store == null || !store.isConnected()) {
			store = createStore(mailAccountModel);
			mailAddressToStoreMap.put(mailAccountModel.getMailAddress(), store);
		}

		return store;
	}

	private Store createStore(MailAccount mailAccountModel) throws NoSuchProviderException, MessagingException {
		Properties prop = new Properties();
		// TODO 根据邮箱服务器不同设置
		String host = "imap.gmail.com";
		prop.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(prop);

		Store store = session.getStore();

		logger.info("your address is : " + mailAccountModel.getMailAddress());
		logger.info("Connecting...");
		store.connect(host, mailAccountModel.getMailAddress(), mailAccountModel.getPassword());
		logger.info("Connected...");
		return store;
	}

}
