package org.EasyBuy.EasyBuyServices.jersey;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.EasyBuy.EasyBuyServices.guice.MainContextListener;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("")
public class Application extends ResourceConfig {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    public Application(ServiceLocator serviceLocator) {
        logger.info("Registering injectables...");

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(MainContextListener.injector);
    }

}