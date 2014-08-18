package com.qycloud.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.DefaultTransportListener;
import org.apache.commons.lang.StringEscapeUtils;

import com.conlect.oatos.dto.autobean.IMessageDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.MessagesDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.activemq.WebClient;
import com.qycloud.web.utils.ConfigUtil;
import com.qycloud.web.utils.Logs;

/**
 * send message to mq
 * 
 * @author yang
 * 
 */
public class MQMessageService {

	private static final String msgStart = "<message>";
	private static final String msgEnd = "</message>";

	/**
	 * 远程服务请求代理
	 */
	private static final XhrProxy proxy = XhrProxy.getInstance();

	private boolean connected = false;

	private ActiveMQConnectionFactory factory;

	private ActiveMQConnection connection;
	private Session session;
	private MessageProducer producer;

	private static MQMessageService instance;

	public synchronized static MQMessageService getInstance() {
		if (instance == null) {
			instance = new MQMessageService();
		}
		return instance;
	}

	private MQMessageService() {
		String brokerURL = WebClient.BROKER_URL;
		factory = new ActiveMQConnectionFactory(brokerURL);
		try {
			connection = getConnection();
		} catch (JMSException ex) {
			Logs.getLogger().error("", ex);
		}
	}

	/**
	 * send message, save to app server
	 * 
	 * @param token
	 * @param messageDTO
	 */
	public String sendMessage(String token, IMessageDTO messageDTO) {
		String result = sendMessage(token, messageDTO, true);
		return result;
	}

	/**
	 * send message, save to app server
	 * 
	 * @param token
	 * @param messageDTO
	 * @param trySave
	 *            try to save message to app server or database
	 * @return
	 */
	public String sendMessage(String token, IMessageDTO messageDTO, boolean trySave) {
		String result = CommConstants.OK_MARK;
		try {
			if (connected) {
				messageDTO.setSendDate(new Date());
				String messageJson = PojoMapper.toJson(messageDTO);
				// send message to MQ
				sendMessageToMQ(messageDTO.getReceiver(), messageDTO.getMessageType(), messageJson);
				if (trySave && token != null) {
					// save message history
					saveMessage(token, messageDTO.getMessageType(), messageJson);
				}
			} else {
				result = ErrorType.errorMQDisconnected.name();
			}

		} catch (Exception ex) {
			Logs.getLogger().error("", ex);
			result = CommConstants.ERROR_MARK;
		}
		return result;
	}

	/**
	 * send messages
	 * 
	 * @param token
	 * @param messageDTOs
	 * @param trySave
	 * @return
	 */
	public String sendMessages(String token, List<IMessageDTO> messageDTOs, boolean trySave) {
		String result = CommConstants.OK_MARK;

		for (IMessageDTO iMessageDTO : messageDTOs) {
			result = sendMessage(token, iMessageDTO, false);
		}

		if (trySave && CommConstants.OK_MARK.equals(result) && token != null) {
			// save message history
			List<MessageDTO> saveMsgs = new ArrayList<MessageDTO>();
			for (IMessageDTO msg : messageDTOs) {
				if (CommonUtil.isSaveMessage(msg.getMessageType())) {
					MessageDTO m = new MessageDTO();
					m.setSender(msg.getSender());
					m.setFromUser(msg.getFromUser());
					m.setReceiver(msg.getReceiver());
					m.setReceiverName(msg.getReceiverName());
					m.setMessageType(msg.getMessageType());
					m.setMessageBody(msg.getMessageBody());
					m.setStatus(msg.getStatus());
					m.setSendDate(msg.getSendDate());
					saveMsgs.add(m);
				}
			}
			if (saveMsgs.size() > 0) {
				MessagesDTO messagesDTO = new MessagesDTO();
				messagesDTO.setMessageDTOList(saveMsgs);
				String postData = PojoMapper.toJson(messagesDTO);
				proxy.post(ConfigUtil.getServiceUrl(RESTurl.saveMessages), token, postData);
			}
		}

		return result;
	}

	/**
	 * send message, not save to app server
	 * 
	 * @param messageDTO
	 * @return
	 */
	public String sendMessage(IMessageDTO messageDTO) {
		return sendMessage(null, messageDTO, false);
	}

	/**
	 * send message to MQ
	 * 
	 * @param receiver
	 * @param type
	 * @param messageJson
	 * @throws Exception
	 */
	private void sendMessageToMQ(long receiver, String type, String messageJson) throws Exception {
		Destination destination = null;
		String destinationName = MyConst.DEFAULT_DOMAIN_NAME + String.valueOf(receiver);
		if (MessageType.GroupChat.equals(type) || MessageType.ShareFileInGroup.equals(type) || MessageType.ShareInstantFile.equals(type) || MessageType.InviteBuddyToChatGroup.equals(type)
				|| MessageType.ReplyForInvitedToChatGroup.equals(type) || MessageType.InformChatGroupToGetMemberList.equals(type) || MessageType.InformJoinChatGroup.equals(type)
				|| MessageType.InformRemoveChatGroupMember.equals(type)) {
			// topic
			destination = getSession().createTopic(destinationName);
		} else {
			// TODO
			// queue
			// destination = getSession().createQueue(destinationName);
			destination = getSession().createTopic(destinationName);
		}
		// build message
		StringBuilder msg = new StringBuilder();
		msg.append(msgStart);
		msg.append(StringEscapeUtils.escapeXml(messageJson));
		msg.append(msgEnd);
		TextMessage txtMessage = getSession().createTextMessage(msg.toString());
		// send message to mq
		getProducer().send(destination, txtMessage);
	}

	/**
	 * save message history
	 * 
	 * @param token
	 * @param type
	 * @param messageJson
	 */
	public void saveMessage(String token, String type, String messageJson) {
		if (token != null) {
			if (CommonUtil.isSaveMessage(type)) {
				proxy.post(ConfigUtil.getServiceUrl(RESTurl.sendMessage), token, messageJson);
			}
		}
	}

	/**
	 * get connection
	 * 
	 * @return
	 * @throws JMSException
	 */
	private ActiveMQConnection getConnection() throws JMSException {
		if (connection == null) {
			connection = (ActiveMQConnection) factory.createConnection();
			connection.start();
			connected = true;
			connection.addTransportListener(new DefaultTransportListener() {

				@Override
				public void onException(IOException error) {
					connected = false;
					Logs.getLogger().error("", error);
					// destroy();
				}

				@Override
				public void transportInterupted() {
					connected = false;
					Logs.getLogger().error("mq connection interupted");
					// destroy();
				}

				@Override
				public void transportResumed() {
					connected = true;
					Logs.getLogger().info("mq connection resumed");
				}

			});
		}
		return connection;
	}

	/**
	 * get session
	 * 
	 * @return
	 * @throws JMSException
	 */
	private Session getSession() throws JMSException {
		if (session == null) {
			session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
		}
		return session;
	}

	/**
	 * get message producer
	 * 
	 * @return
	 * @throws JMSException
	 */
	private MessageProducer getProducer() throws JMSException {
		if (producer == null) {
			producer = getSession().createProducer(null);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}
		return producer;
	}

	/**
	 * close
	 */
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (session != null) {
				session.close();
			}
			if (producer != null) {
				producer.close();
			}
		} catch (Exception ex) {
			Logs.getLogger().error("", ex);
		} finally {
			connected = false;
			connection = null;
			session = null;
			producer = null;
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "{[]}:;,<Hello? sdfsd sdfsd sdfsdf > dsfdsf sdf;?sdf ds' \"";

		IMessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessageType(MessageType.ChatMessage);
		messageDTO.setSender(1L);
		messageDTO.setReceiver(2L);
		messageDTO.setMessageBody(str);

		// XStream xStream = new XStream();
		//
		// System.out.println("xstream : " + xStream.toXML(messageDTO));

		System.out.print(StringEscapeUtils.escapeXml(str));

		System.exit(-1);

		// System.out.println(getInstance().sendMessage(messageDTO));
	}

}
