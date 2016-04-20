package com.edinnova.vrfilm.context;

import javax.servlet.ServletContextEvent;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

public class MyApplicationContext extends ContextLoaderListener {
	Logger logger = Logger.getLogger(MyApplicationContext.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

}
