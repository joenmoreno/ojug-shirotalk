package com.jdm.ojug.shirotalk.resources

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.jdm.ojug.shirotalk.domain.User
import com.jdm.ojug.shirotalk.services.UserService

@Path("admin")
class AdminResource {
	private final UserService userService
	
	@Inject
	public AdminResource(UserService userService) {
		this.userService = userService
	}
	
	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchAllUsers() {
		return userService.fetchAllUsers();
	}
}
