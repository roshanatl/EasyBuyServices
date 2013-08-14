package example.jersey;

import org.apache.camel.guice.CamelModuleWithRouteTypes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import example.camel.QueueRoute;
import example.guice.Service;

public class Main extends GuiceServletContextListener {

    public static Injector injector;

    @Override
    protected Injector getInjector() {
        System.out.println("Getting injector");
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
