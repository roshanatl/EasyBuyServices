package pl.pjagielski.jersey;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.pjagielski.guice.MainContextListener;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return MainContextListener.injector.getInstance(ObjectMapper.class);
    }

}
