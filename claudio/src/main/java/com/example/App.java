package TestJava.claudio.src.main.java.com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        
        WebAppContext context = new WebAppContext();
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        
        server.setHandler(context);
        
        server.start();
        System.out.println("Server started on http://localhost:8080/");
        server.join();
    }
}