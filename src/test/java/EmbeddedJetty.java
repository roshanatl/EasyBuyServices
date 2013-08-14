import java.net.URI;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

public class EmbeddedJetty {

    private Server server;
    private GuiceServletContextListener guiceContextListener;

    public EmbeddedJetty(GuiceServletContextListener guiceContextListener) {
        this.guiceContextListener = guiceContextListener;
    }

    public void start() throws Exception {

        server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");

        contextHandler.addEventListener(guiceContextListener);
        contextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        ServletHolder holder = contextHandler.addServlet(ServletContainer.class, "/*");
        holder.setInitParameter("javax.ws.rs.Application", "example.jersey.MyApplication");

        System.out.println(">>> STARTING EMBEDDED JETTY SERVER");
        server.start();
        
    }
    
    public void stop() throws Exception{
        server.stop();
    }
    
    public URI getBaseUri(){
        return server.getURI();
    }
    
}