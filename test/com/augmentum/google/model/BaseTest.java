package com.augmentum.google.model;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.druid.DruidPlugin;

public class BaseTest<T extends Model<T>> extends TestCase{
	private DruidPlugin druidPlugin;
	private ActiveRecordPlugin arp;
	private Class<T> modelClass;
	private static Logger logger = Logger.getLogger(BaseTest.class);

	@SuppressWarnings("unchecked")
	public BaseTest() {
		modelClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@BeforeClass
	public void setUp() {
		String jdbcConfig = System.getProperty("user.dir")
				+ "/WebContent/WEB-INF/jdbc.properties";
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(jdbcConfig)));
			druidPlugin = new DruidPlugin(properties.getProperty("jdbcUrl")
					.toString(), properties.getProperty("user").toString(),
					properties.getProperty("password").toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return;
		}
		arp = new ActiveRecordPlugin(druidPlugin);
		TableBind tableBind = modelClass.getAnnotation(TableBind.class);
		String modelTable = tableBind.tableName();
		String modelPk = tableBind.pkName();
		arp.addMapping(modelTable, modelPk, modelClass);
		druidPlugin.start();
		arp.start();

	}

	@AfterClass
	public void setDown() {
		if (null != druidPlugin) {
			druidPlugin.stop();
		}
		if (null != arp) {
			arp.stop();
		}
	}
	
}
