package org.EasyBuy.EasyBuyServices.jersey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.EasyBuy.EasyBuyServices.Model.ParseRequest;
import org.EasyBuy.EasyBuyServices.guice.AbzoobaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/abzooba")
public class AbzoobaResource {

    private static Logger logger = LoggerFactory.getLogger(AbzoobaResource.class);
    private final AbzoobaService abzoobaService;

	@Inject
	public AbzoobaResource(AbzoobaService abzoobaService) {
		this.abzoobaService = abzoobaService;
	}


	@POST
	@Path("/parseText")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response getItem(ParseRequest parseRequest ) {
        logger.debug("Fetching Attributes uisng Abzooba");
        return Response.ok(abzoobaService.getAttributes(parseRequest))
        		.header("Access-Control-Allow-Origin", "*")
        		.build()
        ;
    }
}
