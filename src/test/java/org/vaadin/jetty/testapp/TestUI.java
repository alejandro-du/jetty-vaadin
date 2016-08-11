package org.vaadin.jetty.testapp;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.jetty.VaadinJettyServer;

@Theme(ValoTheme.THEME_NAME)
public class TestUI extends UI {

    public static void main(String[] args) throws Exception {
        VaadinJettyServer server = new VaadinJettyServer(8080, TestUI.class);
        server.start();
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("It works!"));
    }

}
