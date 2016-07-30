package org.EasyBuy.EasyBuyServices.jersey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.EasyBuy.EasyBuyServices.Model.Item;
import org.EasyBuy.EasyBuyServices.guice.Service;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("item")
public class ItemResource {

    private static Logger logger = LoggerFactory.getLogger(ItemResource.class);
    private final Service service;

	@Inject
	public ItemResource(Service service) {
		this.service = service;
	}


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem() {
        logger.debug("Fetching Item");
        return service.getItems();
    }
}