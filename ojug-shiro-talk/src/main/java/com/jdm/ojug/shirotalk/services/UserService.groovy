package com.jdm.ojug.shirotalk.services;

import java.util.List;

import com.jdm.ojug.shirotalk.domain.User;

interface UserService {

	User fetchUserByUsername(String username)
	void persistUser(User user)
	List<User> fetchAllUsers()
}
