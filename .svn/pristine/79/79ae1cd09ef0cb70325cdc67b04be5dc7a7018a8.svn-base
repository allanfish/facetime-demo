package com.qycloud.web.activemq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.MessageAvailableConsumer;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents a messaging client used from inside a web container typically
 * stored inside a HttpSession <br>
 * 1. controls to prevent DOS attacks with users requesting many consumers <br>
 * 2. configure consumers with small prefetch. <br>
 * 
 * @version $Revision: 1.1.1.1 $
 */
public class WebClient implements HttpSessionActivationListener, HttpSessionBindingListener {

	public static final String WEB_CLIENT_ATTRIBUTE = "org.apache.activemq.webclient";
	public static final String CONNECTION_FACTORY_ATTRIBUTE = "org.apache.activemq.connectionFactory";
	public static final String CONNECTION_FACTORY_PREFETCH_PARAM = "org.apache.activemq.connectionFactory.prefetch";
	public static final String CONNECTION_FACTORY_OPTIMIZE_ACK_PARAM = "org.apache.activemq.connectionFactory.optimizeAck";
	public static final String BROKER_URL_INIT_PARAM = "org.apache.activemq.brokerURL";
	public static final String SELECTOR_NAME = "org.apache.activemq.selectorName";

	private static final Log LOG = LogFactory.getLog(WebClient.class);

	/**
	 * activemq's broker url
	 */
	public static String BROKER_URL = "";

	private static transient ConnectionFactory factory;
	private static transient Connection connection;

	private transient Map<Destination, MessageConsumer> consumers = new HashMap<Destination, MessageConsumer>();
	private transient Session session;
	private transient MessageProducer producer;
	private int deliveryMode = DeliveryMode.NON_PERSISTENT;
	public static String selectorName;

	private final Semaphore semaphore = new Semaphore(1);

	private CamelContext camelContext;
	private ProducerTemplate producerTemplate;

	public WebClient() {
		if (factory == null) {
			throw new IllegalStateException("initContext(ServletContext) not called");
		}
	}

	/**
	 * @return the web client for the current HTTP session or null if there is
	 *         not a web client created yet
	 */
	public static WebClient getWebClient(HttpSession session) {
		return (WebClient) session.getAttribute(WEB_CLIENT_ATTRIBUTE);
	}

	public static void initContext(ServletContext context) {
		initConnectionFactory(context);
		context.setAttribute("webClients", new HashMap<String, WebClient>());
		if (selectorName == null) {
			selectorName = context.getInitParameter(SELECTOR_NAME);
		}
		if (selectorName == null) {
			selectorName = "selector";
		}
	}

	public synchronized void closeConsumers() {
		for (Iterator<MessageConsumer> it = consumers.values().iterator(); it.hasNext();) {
			MessageConsumer consumer = it.next();
			it.remove();
			try {
				consumer.setMessageListener(null);
				if (consumer instanceof MessageAvailableConsumer) {
					((MessageAvailableConsumer) consumer).setAvailableListener(null);
				}
				consumer.close();
			} catch (JMSException e) {
				LOG.debug("caught exception closing consumer", e);
			}
		}
	}

	public synchronized void close() {
		try {
			if (consumers != null) {
				closeConsumers();
			}
			if (producerTemplate != null) {
				producerTemplate.stop();
			}
		} catch (Exception e) {
			LOG.debug("caught exception closing consumer", e);
		} finally {
			producer = null;
			session = null;
			producerTemplate = null;
			if (consumers != null) {
				consumers.clear();
			}
			consumers = null;

		}
	}

	public boolean isClosed() {
		return consumers == null;
	}

	public void send(Destination destination, Message message) throws JMSException {
		getProducer().send(destination, message);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sent! to destination: " + destination + " message: " + message);
		}
	}

	public void send(Destination destination, Message message, boolean persistent, int priority, long timeToLive)
			throws JMSException {
		int deliveryMode = persistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT;
		getProducer().send(destination, message, deliveryMode, priority, timeToLive);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sent! to destination: " + destination + " message: " + message);
		}
	}

	public Session getJmsSession() throws JMSException {
		if (session == null) {
			session = createJmsSession();
		}
		return session;
	}

	public Connection getConnection() throws JMSException {
		if (connection == null) {
			synchronized (factory) {
				connection = factory.createConnection();
				connection.start();
			}
		}
		return connection;
	}

	protected static synchronized void initConnectionFactory(ServletContext servletContext) {
		if (factory == null) {
			factory = (ConnectionFactory) servletContext.getAttribute(CONNECTION_FACTORY_ATTRIBUTE);
		}
		if (factory == null) {
			String brokerURL = servletContext.getInitParameter(BROKER_URL_INIT_PARAM);

			BROKER_URL = brokerURL;

			LOG.debug("Value of: " + BROKER_URL_INIT_PARAM + " is: " + brokerURL);

			if (brokerURL == null) {
				throw new IllegalStateException("missing brokerURL (specified via " + BROKER_URL_INIT_PARAM
						+ " init-Param");
			}

			ActiveMQConnectionFactory amqfactory = new ActiveMQConnectionFactory(brokerURL);

			// Set prefetch policy for factory
			if (servletContext.getInitParameter(CONNECTION_FACTORY_PREFETCH_PARAM) != null) {
				int prefetch = Integer.valueOf(servletContext.getInitParameter(CONNECTION_FACTORY_PREFETCH_PARAM))
						.intValue();
				amqfactory.getPrefetchPolicy().setAll(prefetch);
			}

			// Set optimize acknowledge setting
			if (servletContext.getInitParameter(CONNECTION_FACTORY_OPTIMIZE_ACK_PARAM) != null) {
				boolean optimizeAck = Boolean.valueOf(
						servletContext.getInitParameter(CONNECTION_FACTORY_OPTIMIZE_ACK_PARAM)).booleanValue();
				amqfactory.setOptimizeAcknowledge(optimizeAck);
			}

			factory = amqfactory;

			servletContext.setAttribute(CONNECTION_FACTORY_ATTRIBUTE, factory);
		}
	}

	public synchronized CamelContext getCamelContext() {
		if (camelContext == null) {
			LOG.debug("Creating camel context");
			camelContext = new DefaultCamelContext();
		}
		return camelContext;
	}

	public synchronized ProducerTemplate getProducerTemplate() throws Exception {
		if (producerTemplate == null) {
			LOG.debug("Creating producer template");
			producerTemplate = getCamelContext().createProducerTemplate();
			producerTemplate.start();
		}
		return producerTemplate;
	}

	public synchronized MessageProducer getProducer() throws JMSException {
		if (producer == null) {
			producer = getJmsSession().createProducer(null);
			producer.setDeliveryMode(deliveryMode);
		}
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public synchronized MessageConsumer getConsumer(Destination destination, String selector) throws JMSException {
		return getConsumer(destination, selector, true);
	}

	public synchronized MessageConsumer getConsumer(Destination destination, String selector, boolean create)
			throws JMSException {
		MessageConsumer consumer = consumers.get(destination);
		if (create && consumer == null) {
			consumer = getJmsSession().createConsumer(destination, selector);
			consumers.put(destination, consumer);
		}
		return consumer;
	}

	public synchronized void closeConsumer(Destination destination) throws JMSException {
		MessageConsumer consumer = consumers.get(destination);
		if (consumer != null) {
			consumers.remove(destination);
			consumer.setMessageListener(null);
			if (consumer instanceof MessageAvailableConsumer) {
				((MessageAvailableConsumer) consumer).setAvailableListener(null);
			}
			consumer.close();
		}
	}

	public synchronized List<MessageConsumer> getConsumers() {
		return new ArrayList<MessageConsumer>(consumers.values());
	}

	protected Session createJmsSession() throws JMSException {
		return getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void sessionWillPassivate(HttpSessionEvent event) {
		close();
	}

	public void sessionDidActivate(HttpSessionEvent event) {
	}

	public void valueBound(HttpSessionBindingEvent event) {
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		close();
	}
}
