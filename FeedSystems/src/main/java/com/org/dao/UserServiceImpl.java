package com.org.dao;

import com.googlecode.objectify.ObjectifyService;
import com.org.model.User;

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

}
