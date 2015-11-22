package play.java.web.config.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get a handle to the JNDI environment naming context
            Context context = new InitialContext();
            Context env = (Context)context.lookup("java:comp/env");

            // Get a single value
            String webmasterEmail = (String)env.lookup("webmasterEmail");
            Integer maxExemptions = (Integer) env.lookup("maxExemptions");
            String configFile = (String)env.lookup("config.file");

            PrintWriter out = resp.getWriter();
            try {
                out.write(String.format("<h1>Hello %s</h1>", webmasterEmail));
                out.write(String.format("<h1>maxExemptions %d</h1>", maxExemptions));
                out.write(String.format("<h1>Configuring with %s</h1>", configFile));
            } finally {
                out.close();
            }
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }
}
