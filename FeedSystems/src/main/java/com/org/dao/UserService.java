package com.org.dao;

import com.org.model.User;

public interface UserService {
	
	public boolean createUser(User users);
	public User getUserByMail(String email);

}
