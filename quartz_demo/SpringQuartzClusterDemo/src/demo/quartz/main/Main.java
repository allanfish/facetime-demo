package demo.quartz.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main.
 * 
 * @author
 * 
 */
public class Main {

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
	}

}
