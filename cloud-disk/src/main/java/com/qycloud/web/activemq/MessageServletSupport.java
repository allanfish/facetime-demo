package com.qycloud.web.activemq;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A useful base class for any JMS related servlet; there are various ways to
 * map JMS operations to web requests so we put most of the common behaviour in
 * a reusable base class. This servlet can be configured with the following init
 * paramters
 * <dl>
 * <dt>topic</dt>
 * <dd>Set to 'true' if the servle should default to using topics rather than
 * channels</dd>
 * <dt>destination</dt>
 * <dd>The default destination to use if one is not specifiied</dd>
 * <dt></dt>
 * <dd></dd>
 * </dl>
 * 
 * @version $Revision: 1.1.1.1 $
 */
public abstract class MessageServletSupport extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final transient Log LOG = LogFactory.getLog(MessageServletSupport.class);
	private static final String destinationParameter = "destination";
	private static final String typeParameter = "type";
	private static final String bodyParameter = "body";
	private static final int defaultMessagePriority = 5;
	private static final boolean defaultMessagePersistent = true;
	
	private boolean defaultTopicFlag = true;

    private Destination defaultDestination;
    private long defaultMessageTimeToLive;
    private String destinationOptions;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        destinationOptions = servletConfig.getInitParameter("destinationOptions");

        String name = servletConfig.getInitParameter("topic");
        if (name != null) {
            defaultTopicFlag = asBoolean(name);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Defaulting to use topics: " + defaultTopicFlag);
        }
        name = servletConfig.getInitParameter("destination");
        if (name != null) {
            if (defaultTopicFlag) {
                defaultDestination = new ActiveMQTopic(name);
            } else {
                defaultDestination = new ActiveMQQueue(name);
            }
        }

        // lets check to see if there's a connection factory set
        WebClient.initContext(getServletContext());
    }

    public static boolean asBoolean(String param) {
        return asBoolean(param, false);
    }

    public static boolean asBoolean(String param, boolean defaultValue) {
        if (param == null) {
            return defaultValue;
        } else {
            return param.equalsIgnoreCase("true");
        }
    }

    protected void appendParametersToMessage(HttpServletRequest request, TextMessage message) throws JMSException {
        Map<?,?> parameterMap = request.getParameterMap();
        if (parameterMap == null) {
            return;
        }
        Map<Object,?> parameters = new HashMap<Object,Object>(parameterMap);
        String correlationID = asString(parameters.remove("JMSCorrelationID"));
        if (correlationID != null) {
            message.setJMSCorrelationID(correlationID);
        }
        Long expiration = asLong(parameters.remove("JMSExpiration"));
        if (expiration != null) {
            message.setJMSExpiration(expiration.longValue());
        }
        Destination replyTo = asDestination(parameters.remove("JMSReplyTo"));
        if (replyTo != null) {
            message.setJMSReplyTo(replyTo);
        }
        String type = (String)asString(parameters.remove("JMSType"));
        if (correlationID != null) {
            message.setJMSType(type);
        }

        for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            String name = (String)entry.getKey();
            if (!destinationParameter.equals(name) && !typeParameter.equals(name) && !bodyParameter.equals(name) && !"JMSDeliveryMode".equals(name) && !"JMSPriority".equals(name)
                && !"JMSTimeToLive".equals(name)) {
                Object value = entry.getValue();
                if (value instanceof Object[]) {
                    Object[] array = (Object[])value;
                    if (array.length == 1) {
                        value = array[0];
                    } else {
                        LOG.warn("Can't use property: " + name + " which is of type: " + value.getClass().getName() + " value");
                        value = null;
                        int size = array.length;
                        for (int i = 0; i < size; i++) {
                            LOG.debug("value[" + i + "] = " + array[i]);
                        }
                    }
                }
                if (value != null) {
                    message.setObjectProperty(name, value);
                }
            }
        }
    }

    protected long getSendTimeToLive(HttpServletRequest request) {
        String text = request.getParameter("JMSTimeToLive");
        if (text != null) {
            return asLong(text);
        }
        return defaultMessageTimeToLive;
    }

    protected int getSendPriority(HttpServletRequest request) {
        String text = request.getParameter("JMSPriority");
        if (text != null) {
            return asInt(text);
        }
        return defaultMessagePriority;
    }

    protected boolean isSendPersistent(HttpServletRequest request) {
        String text = request.getParameter("JMSDeliveryMode");
        if (text != null) {
            return text.trim().equalsIgnoreCase("persistent");
        }
        return defaultMessagePersistent;
    }

    protected boolean isSync(HttpServletRequest request) {
        String text = request.getParameter("sync");
        if (text != null) {
            return true;
        }
        return false;
    }    

    protected Destination asDestination(Object value) {
        if (value instanceof Destination) {
            return (Destination)value;
        }
        if (value instanceof String) {
            String text = (String)value;
            return ActiveMQDestination.createDestination(text, ActiveMQDestination.QUEUE_TYPE);
        }
        if (value instanceof String[]) {
            String text = ((String[])value)[0];
            if (text == null) {
                return null;
            }
            return ActiveMQDestination.createDestination(text, ActiveMQDestination.QUEUE_TYPE);
        }
        return null;
    }

    protected Integer asInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer)value;
        }
        if (value instanceof String) {
            return Integer.valueOf((String)value);
        }
        if (value instanceof String[]) {
            return Integer.valueOf(((String[])value)[0]);
        }
        return null;
    }

    protected Long asLong(Object value) {
        if (value instanceof Long) {
            return (Long)value;
        }
        if (value instanceof String) {
            return Long.valueOf((String)value);
        }
        if (value instanceof String[]) {
            return Long.valueOf(((String[])value)[0]);
        }
        return null;
    }

    protected long asLong(String name) {
        return Long.parseLong(name);
    }

    protected int asInt(String name) {
        return Integer.parseInt(name);
    }

    protected String asString(Object value) {
        if (value instanceof String[]) {
            return ((String[])value)[0];
        }

        if (value != null) {
            return value.toString();
        }

        return null;
    }

    /**
     * @return the destination to use for the current request
     */
    protected Destination getDestination(WebClient client, HttpServletRequest request) throws JMSException {
        String destinationName = request.getParameter(destinationParameter);
        if (destinationName == null  || destinationName.equals("")) {
            if (defaultDestination == null) {
                return getDestinationFromURI(client, request);
            } else {
                return defaultDestination;
            }
        }

        return getDestination(client, request, destinationName);
    }

    /**
     * @return the destination to use for the current request using the relative
     *         URI from where this servlet was invoked as the destination name
     */
    protected Destination getDestinationFromURI(WebClient client, HttpServletRequest request) throws JMSException {
        String uri = request.getPathInfo();
        if (uri == null) {
            return null;
        }

        // replace URI separator with JMS destination separator
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
            if (uri.length() == 0) {
                return null;
            }
        }

        uri = uri.replace('/', '.');
        LOG.debug("destination uri=" + uri);
        return getDestination(client, request, uri);
    }

    /**
     * @return the Destination object for the given destination name
     */
    protected Destination getDestination(WebClient client, HttpServletRequest request, String destinationName) throws JMSException {

        // TODO cache destinations ???

        boolean isTopic = defaultTopicFlag;
        if (destinationName.startsWith("topic://")) {
            isTopic = true;
        } else if (destinationName.startsWith("channel://") || destinationName.startsWith("queue://")) {
            isTopic = false;
        } else {
            isTopic = isTopic(request);
        }
        if (destinationName.indexOf("://") != -1) {
            destinationName = destinationName.substring(destinationName.indexOf("://") + 3);
        }

        if (destinationOptions != null) {
            destinationName += "?" + destinationOptions;
        }
        LOG.debug(destinationName + " (" + (isTopic ? "topic" : "queue") + ")");
        if (isTopic) {
            return client.getJmsSession().createTopic(destinationName);
        } else {
            return client.getJmsSession().createQueue(destinationName);
        }
    }

    /**
     * @return true if the current request is for a topic destination, else
     *         false if its for a queue
     */
    protected boolean isTopic(HttpServletRequest request) {
        String typeText = request.getParameter(typeParameter);
        if (typeText == null) {
            return defaultTopicFlag;
        }
        return typeText.equalsIgnoreCase("topic");
    }

    /**
     * @return the text that was posted to the servlet which is used as the body
     *         of the message to be sent
     */
    protected String getPostedMessageBody(HttpServletRequest request) throws IOException {
        String answer = request.getParameter(bodyParameter);
        if (answer == null && "text/xml".equals(request.getContentType())) {
            // lets read the message body instead
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                buffer.append(line);
                buffer.append("\n");
            }
            return buffer.toString();
        }
        return answer;
    }
    
    protected String getSelector(HttpServletRequest request) throws IOException {
    	return request.getHeader(WebClient.selectorName);
    }
}
