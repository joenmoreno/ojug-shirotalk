package com.jdm.ojug.shirotalk.resources

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import org.apache.shiro.SecurityUtils
import org.apache.shiro.session.Session
import org.apache.shiro.subject.Subject

@Path("ka")
class KeepAliveResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response keepAlive() {
		Subject currentUser = SecurityUtils.getSubject()
		Session session = currentUser.getSession(false)
		session?.touch()
		return Response.ok().build()
	}

}
