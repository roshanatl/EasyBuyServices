package org.EasyBuy.EasyBuyServices.guice;

import org.EasyBuy.EasyBuyServices.Model.Item;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.google.inject.Singleton;

@Singleton
public class Service {

    @EndpointInject(uri="direct:enqueue")
    ProducerTemplate producer;
    
    @EndpointInject(uri="direct:getItem")
    ProducerTemplate getItemRoute;
    

    public static final String SERVICE_STRING = "SERVICE_STRING";

    public String calculateResponse() {
        producer.sendBody("test");
        return SERVICE_STRING;
    }

	public Item getItems() {
		Item item = (Item)getItemRoute.requestBody(null);
		return item;
	}

}
