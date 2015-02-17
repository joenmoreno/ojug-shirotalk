package com.jdm.ojug.shirotalk.resources

import javax.inject.Inject
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

import com.jdm.ojug.shirotalk.domain.User
import com.jdm.ojug.shirotalk.domain.UsernamePasswordCredentials
import com.jdm.ojug.shirotalk.services.UserService

@Path("login")
class LoginResource {

	private static Logger logger = Logger.getLogger(LoginResource);

	private final UserService userService

	@Inject
	public LoginResource(UserService userService) {
		this.userService = userService
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UsernamePasswordCredentials usernamePasswordCredentials) {
		
		boolean loginResult = tryLogin(usernamePasswordCredentials.username, usernamePasswordCredentials.password)

		if(loginResult) {
			User user = userService.fetchUserByUsername(usernamePasswordCredentials.username)
			return Response.ok().entity(user).build();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	private boolean tryLogin(String username, String password) {

		boolean loginResult = false

		Subject currentUser = SecurityUtils
				.getSubject();

		
		if (currentUser.isAuthenticated() && currentUser.getPrincipal().equals(username)) {
			loginResult = true
		} else {

			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);

			try {
				currentUser.login(token)
				loginResult = true
			} catch (UnknownAccountException uae) {
				logger.warn("Login attempt error. There is no user with username of " + username);
			} catch (IncorrectCredentialsException ice) {
				logger.warn("Login attempt error. Password for account " + username + " was incorrect.");
			} catch (LockedAccountException lae) {
				logger.warn("The account for username " + username
						+ " is locked.  "
						+ "Please contact your administrator to unlock it.");
			}
		}

		return loginResult;
	}
}
