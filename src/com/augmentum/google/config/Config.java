package com.augmentum.google.config;

import com.augmentum.google.generate.base.BaseModel;
import com.augmentum.google.interceptor.ExceptionInterceptor;
import com.augmentum.google.interceptor.GlobalInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

public class Config extends JFinalConfig{
	@Override
	public void configConstant(Constants c) {
		loadPropertyFile("jdbc.properties");
		c.setDevMode(true);
	    c.setViewType(ViewType.JSP);
	}
	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void configInterceptor(Interceptors i) {
		i.add(new GlobalInterceptor());
		i.add(new ExceptionInterceptor());
	}

	@Override
	public void configPlugin(Plugins p) {

		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"));
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(
				druidPlugin);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.addExcludeClass(BaseModel.class);
		p.add(druidPlugin).add(autoTableBindPlugin);
	}

	@Override
	public void configRoute(Routes r) {
		r.add(new AutoBindRoutes());

	}

	public static void main(String[] args) throws Exception {
		 JFinal.start("WebContent", 80, "/", 5);
	}
}
