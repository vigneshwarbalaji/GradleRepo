package com.org.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.googlecode.objectify.ObjectifyService;
import com.org.model.User;
import com.org.model.UserFeeds;

public class UserServiceImpl implements UserService{
	
	@Override
	public boolean createUser(User users)
	{
		User existingUser = getUserByMail(users.getEmail());
	
		if(existingUser == null)
		{
			   ObjectifyService.ofy().save().entity(users);
		        return true;
		}
		return false;
	     
	}

	@Override
	public User getUserByMail(String email)
	{
		User existingUser=ObjectifyService.ofy().load().type(User.class).filter("email",email).first().now();
        
        return existingUser;
	}
	
	@Override
	public boolean addUserFeeds(UserFeeds userfeed)
	{
		ObjectifyService.ofy().save().entity(userfeed);
		return true; 
	}
	
	@Override
	public UserFeeds getLastFeedOfTheUser(String email)
	{
		UserFeeds lastFeed = ObjectifyService.ofy().load().type(UserFeeds.class).filter("mail",email).order("-milliseconds").first().now();
		
		return lastFeed;
	}
	
	
	@Override
	public List<UserFeeds> listMyFeeds(String email)
	{
		
		List<UserFeeds>myfeeds = ObjectifyService.ofy().load().type(UserFeeds.class).filter("mail",email).order("-milliseconds").list();
		
		return myfeeds;
		
	}
	
	@Override
	public List<UserFeeds> listAllFeeds()
	{
		
		List<UserFeeds>allfeeds = ObjectifyService.ofy().load().type(UserFeeds.class).order("-milliseconds").list();
		
		return allfeeds;
		
	}
	
	@Override
	public List<UserFeeds> getFeedsByMailId(String email)
	{
		
		List<UserFeeds>myfeeds = ObjectifyService.ofy().load().type(UserFeeds.class).filter("mail",email).order("-milliseconds").list();
		
		return myfeeds;
		
	}
	
	public String milliSecToTimeConversion(long millisec)
	{
		LocalDateTime date;

			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.systemDefault());
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
	    String formattedTime = date.format(formatter);
	    
	    return formattedTime;
	}
	
	
	public String milliSecToDateConversion(long millisec)
	{
		LocalDateTime date;
		
			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.systemDefault());

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLL-yyyy", Locale.ENGLISH);
	    String formattedDate = date.format(formatter);
	    
	    return formattedDate;
	}
	
	/*
	public long timeAndDateToMillis(String myDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
		Date date = null;
		try {
			date = sdf.parse(myDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long millis = date.getTime();

		return millis;
	}*/
	
	public UserFeeds queryingById(long id)
	{
//		UserFeeds likedFeed = ObjectifyService.ofy().load().type(UserFeeds.class).filter("id",id).first().now();
		UserFeeds likedFeed = ObjectifyService.ofy().load().type(UserFeeds.class).id(id).now();
		
		return likedFeed;
	}
	
	@Override
	public List<User> getAllUsers()
	{
		List<User> allusers = ObjectifyService.ofy().load().type(User.class).list();
		
		return allusers;
	}

}
