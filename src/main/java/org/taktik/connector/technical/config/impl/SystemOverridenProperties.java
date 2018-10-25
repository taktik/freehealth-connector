package org.taktik.connector.technical.config.impl;

import java.util.Properties;

public class SystemOverridenProperties extends Properties {

	@Override
	public String getProperty(String key) {
		String candidate = System.getProperty(key);
		return candidate != null ? candidate : super.getProperty(key);
	}

	@Override
	public synchronized Object setProperty(String key, String value) {
		System.setProperty(key, value);
		return super.setProperty(key, value);
	}
}
