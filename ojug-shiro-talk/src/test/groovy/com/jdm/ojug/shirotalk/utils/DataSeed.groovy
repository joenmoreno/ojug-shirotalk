package com.jdm.ojug.shirotalk.utils;

import static org.junit.Assert.*

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.persist.PersistService
import com.google.inject.persist.jpa.JpaPersistModule
import com.jdm.ojug.shirotalk.dao.RoleDao
import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.domain.Role
import com.jdm.ojug.shirotalk.domain.User

//@Ignore
class DataSeed {

	private static Injector i
	private static PersistService p

	@BeforeClass
	public static void setupClass() {
		i = Guice.createInjector(new TestModule(), new JpaPersistModule("unit"))
		p = i.getInstance(PersistService)
		p.start()
	}

	@Test
	public void seedData() {
		
		Role adminRole = new Role(code: 'ADMIN', description: 'User can administer other users.')
		Role userRole = new Role(code: 'USER', description: 'Default role.')
		RoleDao roleDao = i.getInstance(RoleDao)
		roleDao.persistRole(userRole)
		roleDao.persistRole(adminRole)

		UserDao userDao = i.getInstance(UserDao)
		
		String password = 'P@55w0rd'
		PasswordUtil passwordUtil = new PasswordUtil();
		Object salt = passwordUtil.generateSalt()
		String encodedEncryptedPassword = passwordUtil.generatePassword(password, salt)

		User joeSchmoeUser = new User(email: 'joenmoreno@gmail.com',
			firstName: 'joe',
			lastName: 'schmoe',
			password: encodedEncryptedPassword,
			salt: salt.toString(),
			username: 'joeschmoe',
			roles: [adminRole, userRole] as ArrayList)
		
		userDao.persistUser(joeSchmoeUser)
		
	}

	@AfterClass
	public static void cleanupClass() {
		p?.stop()
	}
}
