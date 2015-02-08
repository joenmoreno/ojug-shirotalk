package com.jdm.ojug.shirotalk.guice;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.jdm.ojug.shirotalk.dao.UserDao;
import com.jdm.ojug.shirotalk.dao.UserDaoImpl;
import com.jdm.ojug.shirotalk.services.MessageCreator;
import com.jdm.ojug.shirotalk.services.MessageCreatorImpl;

public class AppServletModule extends ServletModule {
	
    
    @Override
    protected void configureServlets() {
    	filter("/*").through(PersistFilter.class);
    	
    	bind(UserDao.class).to(UserDaoImpl.class);
    	bind(MessageCreator.class).to(MessageCreatorImpl.class);
        
        //ShiroWebModule.bindGuiceFilter(binder());
    }

}

