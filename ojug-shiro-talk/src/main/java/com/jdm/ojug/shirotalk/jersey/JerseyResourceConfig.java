package com.jdm.ojug.shirotalk.jersey;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.jvnet.hk2.guice.bridge.api.HK2IntoGuiceBridge;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.jdm.ojug.shirotalk.guice.AppGuiceServletContextListener;

@ApplicationPath("/api/*")
public class JerseyResourceConfig extends ResourceConfig {

    @Inject
    public JerseyResourceConfig(ServiceLocator serviceLocator) {
        registerProviders();
        createBiDirectionalGuiceBridge(serviceLocator, AppGuiceServletContextListener.getAllModulesAsArray());
    }

    private void registerProviders() {
        packages("com.jdm.ojug.shirotalk.resources;com.fasterxml.jackson.jaxrs.json");
    }

    public Injector createBiDirectionalGuiceBridge(ServiceLocator serviceLocator, Module... applicationModules) {
        Module[] allModules = new Module[applicationModules.length + 1];

        allModules[0] = new HK2IntoGuiceBridge(serviceLocator);
        for (int lcv = 0; lcv < applicationModules.length; lcv++) {
            allModules[lcv + 1] = applicationModules[lcv];
        }

        Injector injector = AppGuiceServletContextListener.getInjectorInstance();
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge g2h = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        g2h.bridgeGuiceInjector(injector);

        return injector;
    }

}
