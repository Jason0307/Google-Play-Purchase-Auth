/**
 * 
 */
package com.augmentum.google.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;

/**
 * @author Jason.Zhu
 * @date 2014-3-13
 * @email jasonzhu@augmentum.com.cn
 */
public abstract class AbstractInitProps {
	protected Configuration config;

	// props file name
	protected String propsFileName;;

	public AbstractInitProps(String propsFileName) {
		setPropsFileName(propsFileName);
		try {
			ConfigurationFactory factory = new ConfigurationFactory(
					getPropsFileName());
			config = factory.getConfiguration();
		} catch (Throwable th) {
			th.printStackTrace();
			throw new ExceptionInInitializerError(th);
		}
	}

	protected String getPropsFileName() {
		return propsFileName;
	}

	protected void setPropsFileName(String propsFileName) {
		this.propsFileName = propsFileName;
	}

	public int getInt(String key) {
		return config.getInt(key, 0);
	}

	public int getInt(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	public long getLong(String key) {
		return config.getLong(key, 0L);
	}

	public long getLong(String key, long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public double getDouble(String key) {
		return config.getDouble(key, 0);
	}

	public double getDouble(String key, double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	public boolean getBoolean(String key) {
		return config.getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	public String getString(String key) {
		return config.getString(key, null);
	}

	public String getString(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
}
