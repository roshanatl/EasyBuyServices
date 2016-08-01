package org.EasyBuy.EasyBuyServices.camel;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.EasyBuy.EasyBuyServices.utill.JsonstringToMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.codehaus.jettison.json.JSONObject;

public class AbzoobaRoute extends RouteBuilder {
	static final String ABZOOBA_REST_URL = "http4://52.23.170.75:5000/model1";

    @Override
    public void configure() throws Exception {
       from("direct:getAttributes")
        .marshal().json(JsonLibrary.Jackson)
        .setProperty("requestJson", body())
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
        .to(ABZOOBA_REST_URL)
        .convertBodyTo(String.class)
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				Map<String, Object> myMap=null;
				myMap = JsonstringToMap.jsonString2Map(exchange.getIn().getBody(String.class));
				myMap.remove("id");
				myMap.remove("Raw_Data");
				JSONObject requestJson = exchange.getProperty("requestJson", JSONObject.class);
				myMap.put("UPC Number", requestJson.get("ImageFileName"));
				exchange.getIn().setBody(myMap);;
			}
		});
	}

}
