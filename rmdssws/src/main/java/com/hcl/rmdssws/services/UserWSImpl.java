package com.hcl.rmdssws.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.rmdssws.dao.UserDAO;
import com.hcl.rmdssws.model.User;
import com.hcl.rmdssws.model.Users;

/**
 * 
 * @author Hitesh-Sharma
 * 
 */
@WebService(serviceName = "UserWS")
public class UserWSImpl implements UserWS {

	private static final Logger logger = Logger.getLogger(UserWSImpl.class);

	@Autowired
	UserDAO userDAO;

	@WebMethod(exclude = true)
	public UserDAO getUserDAO() {
		return userDAO;
	}

	@WebMethod(exclude = true)
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@WebMethod()
	public @WebResult(name = "User", targetNamespace = "http://model.rmdssws.hcl.com")
	User getUserById(int userId) {

		logger.info("inside getUserById and userId is " + userId);
		User user = userDAO.getUserById(userId);
		return user;
	}

	@WebMethod
	public @WebResult(name = "User", targetNamespace = "http://model.rmdssws.hcl.com")
	User addUser(
			@WebParam(name = "User", targetNamespace = "http://model.rmdssws.hcl.com") User user) {
		logger.info("inside addUser");
		System.out.println("Add user " + user.getUserName());
		return userDAO.addUser(user);
	}

	@WebMethod
	public @WebResult(name = "Users", partName="response" ,targetNamespace = "http://model.rmdssws.hcl.com")
	Users getUsers(int userId, String userName, String desg) {
		logger.info("inside getUsers()");
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setDesg(desg);

		List<User> userList = userDAO.getUsers(user);

		Users users = new Users();
		users.setUserList(userList);
		return users;
	}
}
