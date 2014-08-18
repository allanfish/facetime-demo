package com.lucene.demo;

import org.compass.core.Compass;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.CompassEnvironment;

public class CompassUtil {

	private static final Compass compass;
	private static ThreadLocal<CompassSession> sessionLocal = new ThreadLocal<CompassSession>();

	static {
		// .setSetting(CompassEnvironment.CONNECTION, "ram://index") // 在内存中建立索引
		compass = new CompassConfiguration().setSetting(CompassEnvironment.CONNECTION, "file://indexfile")
				.setSetting("compass.engine.analyzer.default.type", "net.paoding.analysis.analyzer.PaodingAnalyzer")
				.setSetting("compass.engine.highlighter.default.formatter.simple.pre", "<font color=\'yellow\'>")
				.setSetting("compass.engine.highlighter.default.formatter.simple.post", "</font>")
				.addScan("com.lucene.demo").buildCompass();
	}

	public static CompassSession getCompassSession() {
		CompassSession session = sessionLocal.get();
		if (session == null) {
			session = compass.openSession();
			sessionLocal.set(session);
		}
		return session;
	}

	public static void closeCompassSession() {
		sessionLocal.remove();
	}

	public static CompassTransaction beginTransaction() {
		CompassSession session = getCompassSession();
		return session.beginLocalTransaction();
	}

	public static void commit() {
		getCompassSession().commit();
	}

	public static void rollback() {
		getCompassSession().rollback();
	}
}
