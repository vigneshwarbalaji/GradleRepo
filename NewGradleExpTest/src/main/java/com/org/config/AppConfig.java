package com.org.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.org.test.SimpleClass;

@ApplicationPath("/")
public class AppConfig extends Application 

{
	
	private Set<Object> singletons = new HashSet<Object>();

	public AppConfig() {
		singletons.add(new SimpleClass());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	
}

