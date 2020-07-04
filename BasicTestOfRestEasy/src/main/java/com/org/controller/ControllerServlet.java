package com.org.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.org.model.Product;
@Path("/")
public class ControllerServlet {
	
	
	
	
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

	@GET
	@Path("/ControllerServlet")
	@Produces("application/json")
	public String signIn() throws IOException
	{
				
		String email = request.getParameter("email").trim();
//		System.out.println(email);
		String pass = request.getParameter("pass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(false);
		
		if(email.isEmpty()||pass.isEmpty())
		{
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else if(email.equals("viki") && pass.equals("pass"))
		{
	
				session = request.getSession(true);
				session.setAttribute("email",email);
				session.setAttribute("name", email);
				//System.out.println(userAcc.getEmail());
				
				//response.getWriter().print("true");		
				map.put("value","true");
			
		
		}
		else
		{
			map.put("value","incorrect details");
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}

	
	
	
	/*
	
	@GET
	@Path("/get")
	@Produces("application/json")
	public String getProductInJSON() {

		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		map.put("product", product);
		
		String obj = "";
		try {
			obj = new ObjectMapper().writeValueAsString(map);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;

		
	}

	@POST
	@Path("/new")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {

		String result = "Product created : " + product;
		return Response.status(201).entity(result).build();

	}*/
} 