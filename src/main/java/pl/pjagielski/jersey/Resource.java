package pl.pjagielski.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.RequestScoped;

import pl.pjagielski.guice.Service;

@RequestScoped
@Path("resource")
public class Resource {

    private static Logger logger = LoggerFactory.getLogger(Resource.class);

	private Service service;

	@Inject
	public Resource(Service service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@Context UriInfo uriInfo) {
        logger.info("Got uri [{}]", uriInfo);
		return service.test();
	}
}