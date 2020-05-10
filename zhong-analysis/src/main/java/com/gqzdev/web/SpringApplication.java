package com.gqzdev.web;

import com.gqzdev.web.config.Appconfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

/**
 * @ClassName Application
 * @Description
 * @Author ganquanzhong
 * @Date2020/5/9 20:56
 * @Version
 **/


public class SpringApplication {

	public static void run() throws LifecycleException {

		/**
		 * 在run()方法中   初始化spring环境  并且启动tomcat
		 */
		// Spring IOC
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(Appconfig.class);
		ac.refresh();

		// Spring Web
		File base = new File("");
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8888);

		//Webapp项目  需要找到web.xml
		//Context context = tomcat.addWebapp("/", base.getAbsolutePath());

		//普通java项目
		Context rootCtx = tomcat.addContext("/", base.getAbsolutePath());

		//创建dispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		Wrapper app = tomcat.addServlet(rootCtx, "app", dispatcherServlet);
		app.setLoadOnStartup(1);
		rootCtx.addServletMappingDecoded("/","app");

		tomcat.start();
		tomcat.getServer().await();


	}
}
