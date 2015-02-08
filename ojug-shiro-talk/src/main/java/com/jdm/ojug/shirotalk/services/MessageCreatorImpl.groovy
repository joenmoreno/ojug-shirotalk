package com.jdm.ojug.shirotalk.services

import com.jdm.ojug.shirotalk.domain.HelloWorldMessage


class MessageCreatorImpl implements MessageCreator {

	@Override
	public HelloWorldMessage generateMessage() {
		return new HelloWorldMessage(message: "Hello there, bud!");
	}


}
