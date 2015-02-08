package com.jdm.ojug.shirotalk.utils

import com.google.inject.AbstractModule
import com.jdm.ojug.shirotalk.dao.RoleDao
import com.jdm.ojug.shirotalk.dao.RoleDaoImpl
import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.dao.UserDaoImpl

class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDao).to(UserDaoImpl)
		bind(RoleDao).to(RoleDaoImpl)
	}

}
