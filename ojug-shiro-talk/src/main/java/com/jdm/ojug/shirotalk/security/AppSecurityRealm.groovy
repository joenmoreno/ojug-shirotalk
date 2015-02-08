package com.jdm.ojug.shirotalk.security

import javax.inject.Inject
import javax.inject.Provider

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SaltedAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.realm.AuthenticatingRealm

import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.domain.User

class AppSecurityRealm extends AuthenticatingRealm {
	private Provider<UserDao> userDao

	@Inject
	public AppSecurityRealm(Provider<UserDao> userDao) {
		this.userDao = userDao
		setName("dbRealm")

		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME)
		matcher.setStoredCredentialsHexEncoded(false)
		matcher.setHashIterations(1024)
		setCredentialsMatcher(matcher)
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username == null) {
			System.out.println("Username is null.");
			return null;
		}

		// read password hash and salt from db
		final User user = userDao.get().fetchUserByUsername(username)

		if (user == null) {
			System.out.println("No account found for user [" + username + "]");
			return null;
		}
		
		// return salted credentials
		SaltedAuthenticationInfo info = new AppSaltedAuthenticationInfo(username, user.password, user.salt);

		return info;

	}
}
