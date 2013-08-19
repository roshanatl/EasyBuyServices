package pl.pjagielski;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

import pl.pjagielski.guice.Service;
import pl.pjagielski.jersey.MainContextListener;

public class ServiceJettyTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    private static EmbeddedJetty embeddedJetty;
    private static Injector injector;

    @Test
    public void shouldEnqueueMessageOnMyresource() throws InterruptedException {
        String response = getResource();

        assertEquals(Service.SERVICE_STRING, response);

        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.setResultWaitTime(2000);
        resultEndpoint.assertIsSatisfied();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        MainContextListener mainContextListener = new MainContextListener() {
            @Override
            protected CamelModuleWithRouteTypes createCamelModules() {
                return new CamelModuleWithRouteTypes(TestRoute.class);
            }
        };
        embeddedJetty = new EmbeddedJetty(mainContextListener);
        embeddedJetty.start();
        injector = MainContextListener.injector;
    }

    @Override
    protected CamelContext createCamelContext() {
        return injector.getInstance(CamelContext.class);
    }

    private static class TestRoute extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:enqueue").to("mock:result");
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        embeddedJetty.stop();
    }

    private String getResource() {
        Client client = ClientBuilder.newClient();

        WebTarget path = client.target(embeddedJetty.getBaseUri())
            .path("resource");
        return path
            .request(MediaType.TEXT_PLAIN_TYPE)
            .get(String.class);
    }

}
