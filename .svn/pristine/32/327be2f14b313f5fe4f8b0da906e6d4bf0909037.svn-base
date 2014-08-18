package com.itcast.spring.proxy;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {

	private final Logger logger = Logger.getLogger(getClass());

	private final Map<String, Object> beanFactory = new HashMap<String, Object>();

	public BeanFactory() {
	}

	public BeanFactory(InputStream is) {
		super();
		try {
			parseBeanXml(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String key) {
		return beanFactory.get(key);
	}

	public Map<String, Object> getBeans() {
		return beanFactory;
	}

	private Object getProxy(final Object target, final List<Interceptor> interceptores) throws InstantiationException,
			IllegalAccessException {
		Object beanProxy = Proxy.newProxyInstance(SpringProxy.class.getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						for (Interceptor interceptor : interceptores)
							interceptor.before();
						Object retVal = method.invoke(target, args);
						for (Interceptor interceptor : interceptores)
							interceptor.after();
						return retVal;
					}

				});
		return beanProxy;
	}

	private Map<String, Object> parseBeanXml(InputStream is) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);

		Element root = doc.getRootElement();
		logger.debug("root element name:" + root.getName());
		List<?> beanList = root.elements("bean");
		List<Element> beanElements = new ArrayList<Element>();
		for (Object beanObj : beanList) {
			Element bean = (Element) beanObj;
			String beanName = bean.attributeValue("id");
			String beanClassStr = bean.attributeValue("class");
			Class<?> beanClass = Class.forName(beanClassStr);
			Object beanInstance = beanClass.newInstance();
			beanElements.add(bean);
			beanFactory.put(beanName, beanInstance);
		}

		for (Element bean : beanElements) {
			List<?> proxyElements = bean.elements("proxy");
			if (!proxyElements.isEmpty()) {
				String beanName = bean.attributeValue("id");
				String beanClassStr = bean.attributeValue("class");
				Class<?> beanClass = Class.forName(beanClassStr);
				List<Class<?>> proxyClasses = new ArrayList<Class<?>>();
				List<Interceptor> interceptors = new ArrayList<Interceptor>();
				for (Object proxyObj : proxyElements) {
					Element proxy = (Element) proxyObj;
					Attribute proxyRefAttr = proxy.attribute("ref");
					Class<?> proxyClass = beanFactory.get(proxyRefAttr.getStringValue()).getClass();
					proxyClasses.add(proxyClass);
					interceptors.add((Interceptor) proxyClass.newInstance());
				}

				Object beanProxy = getProxy(beanClass.newInstance(), interceptors);
				beanFactory.put(beanName, beanProxy);
			}
		}
		return beanFactory;
	}

}
