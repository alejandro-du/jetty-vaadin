package org.vaadin.jetty.testapp;

import org.vaadin.jetty.VaadinJettyServer;

/**
 * @author Alejandro Duarte
 */
public class TestWebapp {

    public static void main(String[] args) throws Exception {
        VaadinJettyServer server = new VaadinJettyServer(8080, "target/test-classes");
        server.start();
    }

}
