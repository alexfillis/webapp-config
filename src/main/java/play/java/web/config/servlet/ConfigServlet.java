package play.java.web.config.servlet;

import play.java.web.config.config.Configuration;
import play.java.web.config.config.ConfigurationDiscovery;
import play.java.web.config.config.JndiConfigurationDiscovery;
import play.java.web.config.config.PropertiesConfigurationLoader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get a handle to the JNDI environment naming context
            Context context = new InitialContext();

            ConfigurationDiscovery configurationDiscovery = new JndiConfigurationDiscovery(context, "java:comp/env/config.file", new PropertiesConfigurationLoader());
            Configuration config = configurationDiscovery.discover();

            String systemUrl = config.get("test.system.url");
            String systemUser = config.get("test.system.user");
            String logPath = config.get("log.path");

            try (PrintWriter out = resp.getWriter()) {
                out.write(String.format("<h1>%s</h1>", systemUrl));
                out.write(String.format("<h1>%s</h1>", systemUser));
                out.write(String.format("<h1>%s</h1>", logPath));
            }
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }
}
