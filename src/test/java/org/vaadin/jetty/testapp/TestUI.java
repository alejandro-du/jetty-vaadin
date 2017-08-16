package org.vaadin.jetty.testapp;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class TestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("It works!"));
    }

}
