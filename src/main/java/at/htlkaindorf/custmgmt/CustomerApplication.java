package at.htlkaindorf.custmgmt;

import at.htlkaindorf.custmgmt.db.CustomerDB;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.io.File;
import java.net.URL;

@WebListener
@ApplicationPath("/api")
public class CustomerApplication extends Application implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        File file = new File (sce.getServletContext().getRealPath("xml/customers.xml"));
        CustomerDB.getInstance().loadDB(file);


        URL url = sce.getServletContext().getClassLoader().getResource("xml/customersRes.xml");
        System.out.println(url);
    }
}