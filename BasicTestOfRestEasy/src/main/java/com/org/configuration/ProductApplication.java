package com.org.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.org.controller.ControllerServlet;

public class ProductApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public ProductApplication() {
        singletons.add(new ControllerServlet());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}