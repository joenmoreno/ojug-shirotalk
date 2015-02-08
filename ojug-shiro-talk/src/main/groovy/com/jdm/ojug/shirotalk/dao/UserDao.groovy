package com.jdm.ojug.shirotalk.dao

import com.jdm.ojug.shirotalk.domain.User

interface UserDao {
	User fetchUserByUsername(String username)
	void persistUser(User user)
}
