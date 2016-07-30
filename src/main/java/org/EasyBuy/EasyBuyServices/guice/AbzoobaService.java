package org.EasyBuy.EasyBuyServices.guice;

import java.util.Map;

import org.EasyBuy.EasyBuyServices.Model.ParseRequest;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.google.inject.Singleton;

@Singleton
public class AbzoobaService {

    
    @EndpointInject(uri="direct:getAttributes")
    ProducerTemplate aabzoobaRoute;
    
	@SuppressWarnings("unchecked")
	public Map<String,Object> getAttributes(ParseRequest parseRequest) {
		Map<String,Object> attributes = (Map<String,Object>)aabzoobaRoute.requestBody(parseRequest);
		return attributes;
	}

}
