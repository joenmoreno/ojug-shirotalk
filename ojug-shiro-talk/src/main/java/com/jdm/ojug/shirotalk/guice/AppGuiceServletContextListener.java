package com.jdm.ojug.shirotalk.guice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

@WebListener
public class AppGuiceServletContextListener extends GuiceServletContextListener {

	private static Injector INJECTOR;
	private static List<Module> MODULES = new ArrayList<Module>();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		createInjectorInstance(servletContextEvent.getServletContext());
		super.contextInitialized(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
		finalizeInjectorInstance();
	}

	@Override
	protected Injector getInjector() {
		return getInjectorInstance();
	}

	public static Injector getInjectorInstance() {
		return INJECTOR;
	}

	public static Module[] getAllModulesAsArray() {
		return (Module[]) MODULES.toArray(new Module[MODULES.size()]);
	}

	private static void createInjectorInstance(ServletContext servletContext) {
		

		MODULES.add(new AppServletModule());
		MODULES.add(new AppPersistenceModule());
		MODULES.add(new ShiroSecurityModule(servletContext));

		INJECTOR = Guice.createInjector(MODULES);
	}

	private static void finalizeInjectorInstance() {
		INJECTOR = null;
	}
}
