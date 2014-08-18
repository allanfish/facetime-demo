package com.qycloud.oatos.server.security;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class SecurityPropertyConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	        throws BeansException {
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		props.setProperty("jdbc.username", Security.hexStringToByteString(username));
		props.setProperty("jdbc.password", Security.hexStringToByteString(password));
		super.processProperties(beanFactoryToProcess, props);
	}

}
