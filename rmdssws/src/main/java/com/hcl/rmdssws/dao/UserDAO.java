package com.hcl.rmdssws.dao;

import java.util.List;

import com.hcl.rmdssws.model.User;

public interface UserDAO {

	User getUserById(int userId);

	User addUser(User user);
	
	List<User> getUsers(User user);

}
