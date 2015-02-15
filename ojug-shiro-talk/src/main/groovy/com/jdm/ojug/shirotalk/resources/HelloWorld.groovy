package com.jdm.ojug.shirotalk.resources

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.jdm.ojug.shirotalk.domain.HelloWorldMessage
import com.jdm.ojug.shirotalk.services.MessageCreator

@Path("helloworld")
public class HelloWorld {
    private MessageCreator messageCreator;
    
    @Inject
    public HelloWorld(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldMessage getHello() {
        return messageCreator.generateMessage();
    }
  
}