package org.EasyBuy.EasyBuyServices.camel;

import java.math.BigInteger;

import org.EasyBuy.EasyBuyServices.Model.Item;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ItemRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:getItem").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				 Item item = new Item();
				 item.setId(BigInteger.valueOf(1234));
				 item.setName("Sugar");
				 exchange.getIn().setBody(item);;
			}
		});
    }

}
