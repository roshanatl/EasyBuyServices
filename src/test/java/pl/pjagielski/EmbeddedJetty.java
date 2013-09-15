package pl.pjagielski;

import java.io.File;
import java.net.URI;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.FileResource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.GuiceServletContextListener;

public class EmbeddedJetty {

    private static Logger logger = LoggerFactory.getLogger(EmbeddedJetty.class);

    private Server server;
    private GuiceServletContextListener guiceContextListener;

    public EmbeddedJetty(GuiceServletContextListener guiceContextListener) {
        this.guiceContextListener = guiceContextListener;
    }

    public void start() throws Exception {
        server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setDescriptor("src/test/resources/WEB-INF/web.xml");
        webAppContext.addEventListener(guiceContextListener);
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.getMetaData().addContainerResource(new FileResource(new File("./target/classes").toURI()));

        webAppContext.setConfigurations(new Configuration[] {
            new AnnotationConfiguration(),
            new WebXmlConfiguration(),
            new WebInfConfiguration()
        });
        webAppContext.setParentLoaderPriority(true);

        server.setHandler(webAppContext);

        logger.info(">>> STARTING EMBEDDED JETTY SERVER");
        server.start();
        
    }
    
    public void stop() throws Exception{
        server.stop();
    }
    
    public URI getBaseUri(){
        return server.getURI();
    }
    
}