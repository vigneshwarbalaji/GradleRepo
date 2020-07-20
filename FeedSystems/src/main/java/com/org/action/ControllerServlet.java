package com.org.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.appengine.api.datastore.Text;
import com.org.dao.UserService;
import com.org.dao.UserServiceImpl;
import com.org.model.User;
import com.org.model.UserFeeds;


@Path("/")
public class ControllerServlet {
	
	UserService dao = (UserService) new UserServiceImpl();
	
	private static final String CLIENT_ID = "1057523589135-9gb46eembt589ce228tfbetn8nhvmqkn.apps.googleusercontent.com";
	
	  private static final HttpTransport TRANSPORT = new NetHttpTransport();
	  private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

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
	
	
//	@Path("/Signup")
//	@GET
//	public String signUpPage() throws IOException
//	{
//		
//		InputStream st=servletContext.getResourceAsStream("/Signup.jsp");
//		BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));
//
//		String string = new String();
//		String modifiedHtml = "";
//		while( (string = buffReader.readLine() ) != null){
//		modifiedHtml=modifiedHtml+string;
//		}
//
//		buffReader.close();
//		st.close();
//		
//		
//		
//		return modifiedHtml;
//		
//	}
	
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
	
	
	@Path("/googleSignIn")
	@POST
	@Produces("application/json")
	public String googleSignIn() throws IOException
	{
		
		HttpSession session = request.getSession(false);
		
		HashMap<String,Object>map = new HashMap<String, Object>();		
		
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT,JSON_FACTORY)
				    .setAudience(Collections.singletonList(CLIENT_ID))
				    .build();

			System.out.println("test1");	
			
			
				String idTokenString = request.getParameter("googleToken");
			
				GoogleIdToken idToken = null;
				try {
					idToken = verifier.verify(idTokenString);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = (String) payload.get("email");
				  boolean emailVerified = Boolean.valueOf(((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmailVerified());
				  String name = (String) payload.get("name");

				  
				  		User user = dao.getUserByMail(email);
				  		
				  		if(user == null) 
				  		{
				  			map.put("value","false");
				  		}
				  		else
				  		{
				  			session = request.getSession(true);
				  			System.out.println("sign In"+user.getEmail());
				  			session.setAttribute("email",user.getEmail());
							session.setAttribute("name",user.getName());
							map.put("value","true");
				  		}
						

				} else {
					
					map.put("value","false");
				  System.out.println("Invalid ID token.");
				}

				String obj = new ObjectMapper().writeValueAsString(map);

				return obj;

	}
	
	
	@Path("/googleSignUp")
	@POST
	@Produces("application/json")
	public String googleSignUp() throws IOException
	{
		
		HttpSession session = request.getSession(false);
		
		HashMap<String,Object>map = new HashMap<String, Object>();

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT,JSON_FACTORY)
				    .setAudience(Collections.singletonList(CLIENT_ID))
				    .build();

				// (Receive idTokenString by HTTPS POST)
			System.out.println("test1");	
			
			
				String idTokenString = request.getParameter("googleToken");
			
				GoogleIdToken idToken = null;
				try {
					idToken = verifier.verify(idTokenString);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = (String) payload.get("email");
				  boolean emailVerified = Boolean.valueOf(((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmailVerified());
				  String name = (String) payload.get("name");

				  User users = new User();
				  boolean result = false;
				  
				  	users.setId(null);
					users.setName(name);
					users.setEmail(email);
					
					result = dao.createUser(users);
					
					if(result == true)
					{
						session = request.getSession(true);
						System.out.println("sign up test 2"+users.getEmail());
						session.setAttribute("email",users.getEmail());
						session.setAttribute("name",users.getName());
						map.put("value","true");
					}
					else
					{
						map.put("value","false");
					}


				} else {
					
					map.put("value","false");
				  System.out.println("Invalid ID token.");
				}

				String obj = new ObjectMapper().writeValueAsString(map);

				return obj;

	}


	@Path("/feedUpdate")
	@POST
	@Produces("application/json")
	public String feedUpdate() throws IOException
	{
		
		HttpSession session = request.getSession(false);		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		Text statusFeed = new Text(request.getParameter("feedText"));
		long milliseconds = System.currentTimeMillis();
		
		System.out.println(name+" "+email+" "+statusFeed+" "+milliseconds);
		UserFeeds userfeed = new UserFeeds();
		
		userfeed.setId(null);
		userfeed.setName(name);
		userfeed.setMail(email);
		userfeed.setFeed(statusFeed);
		userfeed.setMilliseconds(milliseconds);
		
		boolean isFeedAdded = dao.addUserFeeds(userfeed);
		
		if(isFeedAdded == true)
		{
			UserFeeds getLastFeed = dao.getLastFeedOfTheUser(email);
			
			//System.out.println("test2 "+getLastFeed.getMail()+" "+getLastFeed.getMilliseconds()+" "+getLastFeed.getFeed());
			
			String date = dao.milliSecToDateConversion(getLastFeed.getMilliseconds());
			String time = dao.milliSecToTimeConversion(getLastFeed.getMilliseconds());
			
			map.put("lastfeed",getLastFeed);
			map.put("date",date);
			map.put("time",time);
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);

		return obj;
		
	}
	
	
	@Path("/GetListOfMyFeeds")
	@GET
	@Produces("application/json")
	public String listMyFeeds() throws IOException
	{
		
		HttpSession session = request.getSession(false);		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		
		int index = 0;
		
		List<UserFeeds>myfeeds = dao.listMyFeeds(email);
		List<String>date = new ArrayList<String>();
		List<String>time = new ArrayList<String>();
		
		for(UserFeeds userfeeds : myfeeds)
		{
			time.add(index, dao.milliSecToTimeConversion(userfeeds.getMilliseconds()));
			date.add(index, dao.milliSecToDateConversion(userfeeds.getMilliseconds()));
			
			index++;
		}
		
		map.put("myfeeds",myfeeds);
		map.put("time",time);
		map.put("date",date);
		
		String obj = new ObjectMapper().writeValueAsString(map);

		return obj;
	}
	
	
	@Path("/GetListOfAllFeeds")
	@GET
	@Produces("application/json")
	public String listAllFeeds() throws IOException
	{
		
		HttpSession session = request.getSession(false);		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
//		String name = session.getAttribute("name").toString();
//		String email = session.getAttribute("email").toString();
		
		int index = 0;
		
		List<UserFeeds>allfeeds = dao.listAllFeeds();
		List<String>date = new ArrayList<String>();
		List<String>time = new ArrayList<String>();
		
		for(UserFeeds userfeeds : allfeeds)
		{
			time.add(index, dao.milliSecToTimeConversion(userfeeds.getMilliseconds()));
			date.add(index, dao.milliSecToDateConversion(userfeeds.getMilliseconds()));
			
			index++;
		}
		
		map.put("allfeeds",allfeeds);
		map.put("time",time);
		map.put("date",date);
		
		String obj = new ObjectMapper().writeValueAsString(map);

		return obj;
	}
	
	@Path("/GetAllUsers")
	@GET
	@Produces("application/json")
	public String getAllUsers() throws IOException
	{
		
		HttpSession session = request.getSession(false);		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		
		List<User>allusers = dao.getAllUsers();
//		String name = session.getAttribute("name").toString();
//		String email = session.getAttribute("email").toString();
		
//		int index = 0;
//		
//		List<UserFeeds>allfeeds = dao.listAllFeeds();
//		List<String>date = new ArrayList<String>();
//		List<String>time = new ArrayList<String>();
//		
//		for(UserFeeds userfeeds : allfeeds)
//		{
//			time.add(index, dao.milliSecToTimeConversion(userfeeds.getMilliseconds()));
//			date.add(index, dao.milliSecToDateConversion(userfeeds.getMilliseconds()));
//			
//			index++;
//		}
//		
//		map.put("allfeeds",allfeeds);
//		map.put("time",time);
//		map.put("date",date);
		
		map.put("allusers",allusers);
		
		String obj = new ObjectMapper().writeValueAsString(map);

		return obj;
	}
	
}

