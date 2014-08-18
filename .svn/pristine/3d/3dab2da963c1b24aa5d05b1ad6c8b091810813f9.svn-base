package com.qycloud.web.bean.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 文件上传Queue Jms发送者. 通知将缓存到本地的文件上传到FileManager
 */
@Component("fileUploadJmsSender")
public class FileUploadJmsSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQQueue fileUploadQueue;

	public void send(final String fileUploadJson) {
		jmsTemplate.send(fileUploadQueue, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(fileUploadJson);
			}
		});
	}
}
