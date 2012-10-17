package org.agoncal.sample.cdi.bootstrapping.servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Antonio Goncalves
 */
public class MainServlet25 extends HttpServlet {

    @Inject
    Hello hello;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("MainServlet25 : " + saySomething());
        out.close();
    }

    public String saySomething() {
        return hello.sayHelloWorld();
    }
}
