package org.EasyBuy.EasyBuyServices.guice;

import org.EasyBuy.EasyBuyServices.camel.ItemRoute;
import org.EasyBuy.EasyBuyServices.camel.QueueRoute;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class MainContextListener extends GuiceServletContextListener {

    public static Injector injector;
    private static Logger logger = LoggerFactory.getLogger(MainContextListener.class);

    @Override
    protected Injector getInjector() {
        logger.info("Getting injector");
        injector = createInjector();
        return injector;
    }

    protected Injector createInjector() {
        return Guice.createInjector(createCamelModules(), new AbstractModule() {
            @Override
            protected void configure() {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.registerModule(new JodaModule());

                bind(ObjectMapper.class).toInstance(objectMapper);
                bind(Service.class);
            }
        });
    }

    protected CamelModuleWithRouteTypes createCamelModules() {
        return new CamelModuleWithRouteTypes(QueueRoute.class,ItemRoute.class);
    }

}
