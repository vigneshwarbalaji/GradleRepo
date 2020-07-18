package com.org.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.org.action.ControllerServlet;


public class FeedApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public FeedApplication() {
        singletons.add(new ControllerServlet());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}