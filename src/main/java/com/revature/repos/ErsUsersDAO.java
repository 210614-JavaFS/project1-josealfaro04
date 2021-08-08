package com.revature.repos;

import java.util.List;

import com.revature.models.ErsUsers;

public interface ErsUsersDAO {

	//public List<ErsUsers> findAllUsers();
	public ErsUsers getOneUser(String name);
	public int getByUserId(String username);
//	public boolean updateUser(ErsUsers user);
	//public boolean addUser(ErsUsers user);
	public boolean login(String username, String password);
}
