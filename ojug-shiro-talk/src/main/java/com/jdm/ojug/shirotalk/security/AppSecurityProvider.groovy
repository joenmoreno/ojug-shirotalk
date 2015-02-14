package com.jdm.ojug.shirotalk.security

import javax.inject.Inject
import javax.inject.Provider

import com.jdm.ojug.shirotalk.dao.UserDao


class AppSecurityProvider implements Provider<AppSecurityRealm> {

	private final Provider<UserDao> userDaoProvider

	@Inject
	public AppSecurityProvider(Provider<UserDao> userDaoProvider) {
		this.userDaoProvider = userDaoProvider
	}

	@Override
	public AppSecurityRealm get() {
		return new AppSecurityRealm(userDaoProvider);
	}
}
