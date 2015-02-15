package com.jdm.ojug.shirotalk.guice;

import javax.inject.Singleton;
import javax.servlet.ServletContext;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.name.Names;
import com.jdm.ojug.shirotalk.security.AppSecurityProvider;

public class ShiroSecurityModule extends ShiroWebModule {

	public ShiroSecurityModule(ServletContext servletContext) {
		super(servletContext);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void configureShiroWeb() {

		bindRealm().toProvider(AppSecurityProvider.class).in(Singleton.class);
		
		//Set Session Timeout to 30minutes 
		bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(1800000L);
		
		bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/#/login");
		bindConstant().annotatedWith(Names.named("logout.redirectUrl")).to("/#/login");

		addFilterChain("/api/ka", ANON);
		addFilterChain("/api/login", ANON);
		
		addFilterChain("/api/logout", LOGOUT);
		
		addFilterChain("/api/helloworld", AUTHC_BASIC, config(ROLES, "ADMIN"));
		addFilterChain("/api/admin/**", AUTHC_BASIC, config(ROLES, "ADMIN"));

		addFilterChain("/**", AUTHC);

	}

}
