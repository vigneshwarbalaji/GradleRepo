package com.org.test;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;



public class SimpleClass
{
@Context
HttpServletRequest request;
@Context
HttpServletResponse response;

@Context
ServletContext servletContext;

	

@GET
public String index() throws IOException
{
	
	InputStream st=servletContext.getResourceAsStream("/Login.jsp");
	BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));

	String string = new String();
	String modifiedHtml = "";
	while( (string = buffReader.readLine() ) != null){
	modifiedHtml=modifiedHtml+string;
	}

	buffReader.close();
	st.close();
	
	
	
	return modifiedHtml;
	
}


@Path("/Signup")
@GET
public String signUpPage() throws IOException
{
	
	InputStream st=servletContext.getResourceAsStream("/Signup.jsp");
	BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));

	String string = new String();
	String modifiedHtml = "";
	while( (string = buffReader.readLine() ) != null){
	modifiedHtml=modifiedHtml+string;
	}

	buffReader.close();
	st.close();
	
	
	
	return modifiedHtml;
	
}

@Path("/Dashboard")
@GET
public String DashBoardPage() throws IOException
{
	
	InputStream st=servletContext.getResourceAsStream("/Dashboard.jsp");
	BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));

	String string = new String();
	String modifiedHtml = "";
	while( (string = buffReader.readLine() ) != null){
	modifiedHtml=modifiedHtml+string;
	}

	buffReader.close();
	st.close();
	
	
	
	return modifiedHtml;
	
}
}