package com.qycloud.web.bean.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MyConst;

@Component("jmsSender")
public class JmsSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 */
	public void sendMessage(String receiverId, final String messageJson) {
		String topic = String.format("%s%s", MyConst.DEFAULT_DOMAIN_NAME, receiverId);
		jmsTemplate.send(topic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(String.format("%s%s%s", CommConstants.MSG_START_MARK,
						StringEscapeUtils.escapeXml(messageJson), CommConstants.MSG_END_MARK));
			}
		});
	}
}