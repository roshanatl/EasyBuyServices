package pl.pjagielski.guice;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.google.inject.Singleton;

@Singleton
public class Service {

    @EndpointInject(uri="direct:enqueue")
    ProducerTemplate producer;

    public static final String SERVICE_STRING = "SERVICE_STRING";

    public String test() {
        producer.sendBody("test");
        return SERVICE_STRING;
    }

}
