package com.revature.services;

import com.revature.models.ErsUsers;
import com.revature.repos.ErsUsersDAO;
import com.revature.repos.ErsUsersImpl;

public class UserServices {
	
	private static ErsUsersDAO ersUserDao = new ErsUsersImpl();

	public boolean login(String username, String password) {
		return ersUserDao.login(username, password);
	}
	
	public ErsUsers getOneUser(String username) {
		return ersUserDao.getOneUser(username);
	}
	
	
	
}
