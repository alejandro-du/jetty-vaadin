package org.vaadin.jetty.testapp;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author Alejandro Duarte
 */
public class TestWebConfig {

    @WebServlet("/*")
    @VaadinServletConfiguration(ui = TestUI.class, productionMode = false)
    public static class JdbcExampleVaadinServlet extends VaadinServlet {
    }

}
