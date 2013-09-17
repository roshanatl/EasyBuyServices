package pl.pjagielski.jersey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.pjagielski.guice.Service;

@Singleton
@Path("resource")
public class Resource {

    private static Logger logger = LoggerFactory.getLogger(Resource.class);

	private final Service service;

	@Inject
	public Resource(Service service) {
		this.service = service;
	}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sampleGet() {
        logger.debug("Got without uriInfo");
        return service.calculateResponse();
    }

    @GET
    @Path("/uri")
    @Produces(MediaType.TEXT_PLAIN)
   	public String sampleGetWithUriInfo(@Context UriInfo uriInfo) {
        logger.debug("Matched uris {}", uriInfo.getMatchedURIs());
		return service.calculateResponse();
    }

	@GET
    @Path("/date")
	@Produces(MediaType.APPLICATION_JSON)
	public CurrentDate currentDateAsJson() {
		return new CurrentDate(DateTime.now());
	}

}