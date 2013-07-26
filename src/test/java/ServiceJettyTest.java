import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import example.guice.Service;

public class ServiceJettyTest {

    private static EmbeddedJetty embeddedJetty;
    

    @BeforeClass
    public static void beforeClass() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
    }
    
    @AfterClass
    public static void afterClass() throws Exception {
        embeddedJetty.stop();
    }

    @Test
    public void givenRunningJettyInstance_whenGetMyresource_thenServiceStringReturned() {
        Client client = ClientBuilder.newClient();
        
        System.out.println(embeddedJetty.getBaseUri());
        
        WebTarget path = client.target(embeddedJetty.getBaseUri())
                        .path("myresource");
        String entity = path
                        .request(MediaType.TEXT_PLAIN_TYPE)
                        .get(String.class);

        assertEquals(Service.SERVICE_STRING, entity);
        
        
    }

}
