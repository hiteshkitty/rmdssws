package com.hcl.rmdssws.services;

import com.hcl.rmdssws.model.User;
import com.hcl.rmdssws.model.Users;

public interface UserWS {

	User getUserById(int userId);

	User addUser(User user);
	
	Users getUsers(int userId, String userName, String desg);
}
