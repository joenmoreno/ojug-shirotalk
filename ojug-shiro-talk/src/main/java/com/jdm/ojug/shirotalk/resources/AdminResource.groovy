package com.jdm.ojug.shirotalk.resources

import javax.inject.Inject
import javax.inject.Provider
import javax.ws.rs.GET
import javax.ws.rs.Path

import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.domain.User

@Path("admin")
class AdminResource {
	private Provider<UserDao> userDao

	@Inject
	public AdminResource(Provider<UserDao> userDao) {
		this.userDao = userDao
	}
	
	@GET
	@Path("users")
	public List<User> fetchAllUsers() {
		return userDao.get().fetchAllUsers();
	}
}
