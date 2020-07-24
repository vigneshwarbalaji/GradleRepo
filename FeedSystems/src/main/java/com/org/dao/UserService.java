package com.org.dao;

import java.util.List;

import com.org.model.User;
import com.org.model.UserFeeds;

public interface UserService {
	
	public boolean createUser(User users);
	public User getUserByMail(String email);
	public boolean addUserFeeds(UserFeeds userfeed);
	public UserFeeds getLastFeedOfTheUser(String email);
	public List<UserFeeds> listMyFeeds(String email);
	public List<UserFeeds> listAllFeeds();
	public List<UserFeeds> getFeedsByMailId(String email);
	public String milliSecToTimeConversion(long millisec);
	public String milliSecToDateConversion(long millisec);
	public List<User> getAllUsers();
	public UserFeeds queryingById(long id);
/*	public long timeAndDateToMillis(String myDate);*/
	
}
