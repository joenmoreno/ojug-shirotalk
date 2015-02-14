package com.jdm.ojug.shirotalk.resources

import javax.inject.Inject
import javax.inject.Provider
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import org.apache.log4j.Logger
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.LockedAccountException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.subject.Subject

import com.jdm.ojug.shirotalk.dao.UserDao
import com.jdm.ojug.shirotalk.domain.User
import com.jdm.ojug.shirotalk.domain.UsernamePasswordCredentials

@Path("login")
class LoginResource {

	private static Logger logger = Logger.getLogger(LoginResource);
	
	private Provider<UserDao> userDao

	@Inject
	public LoginResource(Provider<UserDao> userDao) {
		this.userDao = userDao
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UsernamePasswordCredentials usernamePasswordCredentials) {
		boolean loginResult = false

		try {
			loginResult = tryLogin(usernamePasswordCredentials.username, usernamePasswordCredentials.password)
		} catch (UnknownAccountException uae) {
			logger.warn("There is no user with username of " + usernamePasswordCredentials.username);
		} catch (IncorrectCredentialsException ice) {
			logger.warn("Password for account " + usernamePasswordCredentials.username + " was incorrect!");
		} catch (LockedAccountException lae) {
			logger.warn("The account for username " + usernamePasswordCredentials.username
					+ " is locked.  "
					+ "Please contact your administrator to unlock it.");
		}

		if(loginResult) {
			User user = userDao.get().fetchUserByUsername(usernamePasswordCredentials.username)
			return Response.ok().entity(user).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	public boolean tryLogin(String username, String password) {

		Subject currentUser = SecurityUtils
				.getSubject();

		if (!currentUser.isAuthenticated()) {

			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);

			currentUser.login(token);

		}

		return true;
	}
}
