package com.jdm.ojug.shirotalk.services

import org.apache.shiro.authz.annotation.RequiresRoles;

import groovyx.net.http.RESTClient

import com.jdm.ojug.shirotalk.domain.HelloWorldMessage


class MessageCreatorImpl implements MessageCreator {

	@Override
	public HelloWorldMessage generateMessage() {
		def randomChuckClient = new RESTClient( 'http://api.icndb.com' )
		def response = randomChuckClient.get(path: '/jokes/random')
		String message = response.data.value.joke
		return new HelloWorldMessage(message: message);
	}


}
