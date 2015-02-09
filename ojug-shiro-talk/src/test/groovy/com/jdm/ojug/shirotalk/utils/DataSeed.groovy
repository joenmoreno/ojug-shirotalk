

package com.jdm.ojug.shirotalk.utils;

import static org.junit.Assert.*

import org.apache.onami.persist.PersistenceService
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector
import com.jdm.ojug.shirotalk.dao.RoleDao
import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.domain.Role
import com.jdm.ojug.shirotalk.domain.User
import com.jdm.ojug.shirotalk.guice.AppPersistenceModule

//@Ignore
class DataSeed {

	private static Injector i
	private static PersistenceService p

	@BeforeClass
	public static void setupClass() {

		i = Guice.createInjector(new TestModule(), new AppPersistenceModule())
		p = i.getInstance(PersistenceService)
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
