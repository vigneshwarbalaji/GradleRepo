package com.org.objectifyconfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.org.model.User;
import com.org.model.UserFeeds;

public class OfyHelper implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.init();
		ObjectifyService.register(User.class);
		ObjectifyService.register(UserFeeds.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
