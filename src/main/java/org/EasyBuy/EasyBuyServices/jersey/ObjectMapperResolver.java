package org.EasyBuy.EasyBuyServices.jersey;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.EasyBuy.EasyBuyServices.guice.MainContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return MainContextListener.injector.getInstance(ObjectMapper.class);
    }

}
