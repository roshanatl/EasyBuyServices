package pl.pjagielski.jersey;

import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import pl.pjagielski.camel.QueueRoute;
import pl.pjagielski.guice.Service;

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
        return Guice.createInjector(createCamelModules(), new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(Service.class);
            }
        });
    }

    protected CamelModuleWithRouteTypes createCamelModules() {
        return new CamelModuleWithRouteTypes(QueueRoute.class);
    }

}
