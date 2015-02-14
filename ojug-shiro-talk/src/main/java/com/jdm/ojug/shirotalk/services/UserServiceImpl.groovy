package com.jdm.ojug.shirotalk.services

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.jdm.ojug.shirotalk.dao.UserDao;
import com.jdm.ojug.shirotalk.domain.User;

class UserServiceImpl implements UserService {

	private Provider<UserDao> userDao

	@Inject
	public UserServiceImpl(Provider<UserDao> userDao) {
		this.userDao = userDao
	}

	@Override
	public User fetchUserByUsername(String username) {
		return userDao.get().fetchUserByUsername(username)
	}

	@Override
	public void persistUser(User user) {
		userDao.get().persistUser(user)
	}

	@Override
	public List<User> fetchAllUsers() {
		return userDao.get().fetchAllUsers()
	}
}
