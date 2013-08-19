package pl.pjagielski.camel;

import org.apache.camel.builder.RouteBuilder;

public class QueueRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:enqueue").to("seda:serviceQueue");
    }

}
