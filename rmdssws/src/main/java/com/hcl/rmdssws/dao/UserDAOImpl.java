package com.hcl.rmdssws.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hcl.rmdssws.model.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	/* This method gets the user by user id */
	@Override
	public User getUserById(int userId) {
		logger.info("inside getUserById() and userId :: " + userId);
		return (User) getHibernateTemplate().get(User.class, userId);
	}

	@Override
	public User addUser(User user) {
		logger.info("inside addUser()");
		User usr = (User) getHibernateTemplate().merge(user);
		return usr;
		 
	}

	@Override
	public List<User> getUsers(User user) {
		logger.info("inside the getUsers()");

		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if (user.getUserId() != 0) {
			criteria.add(Restrictions.eq("userId", user.getUserId()));
		}
		if (user.getUserName() != null && user.getUserName().trim().length() > 0) {
			criteria.add(Restrictions.eq("userName", user.getUserName()));
		}
		if (user.getDesg() != null  && user.getDesg().trim().length() > 0) {
			criteria.add(Restrictions.eq("desg", user.getDesg()));
		}

		List<User> userList = (List<User>) getHibernateTemplate()
				.findByCriteria(criteria);
		return userList;
	}

}
