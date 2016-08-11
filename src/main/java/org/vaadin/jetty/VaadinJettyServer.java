package org.vaadin.jetty;

import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>A specialized Jetty {@link Server} to run Vaadin applications.</p>
 * <p>Server HTTP port, UI implementation, UIProvider, webapp directory,
 * and application context path are configurable through the parameters
 * in the available constructors.</p>
 * <p>If used to test Vaadin Add-ons, please refer to the documentation
 * at <a href="https://github.com/alejandro-du/vaadin-jetty">
 *     https://github.com/alejandro-du/vaadin-jetty</a>.</p>
 *
 * @author alejandro@vaadin.com
 */
public class VaadinJettyServer extends Server {

    private static final String DEFAULT_WEBAPP_DIRECTORY = "target/classes";
    private static final String DEFAULT_CONTEXT_PATH = "/";

    public VaadinJettyServer(int port, Class<? extends UI> uiClass) throws IOException {
        this(port, uiClass, DefaultUIProvider.class, DEFAULT_WEBAPP_DIRECTORY, DEFAULT_CONTEXT_PATH);
    }

    public VaadinJettyServer(int port, Class<? extends UI> uiClass, Class<? extends UIProvider> uiProvider) throws IOException {
        this(port, uiClass, uiProvider, DEFAULT_WEBAPP_DIRECTORY, DEFAULT_CONTEXT_PATH);
    }

    public VaadinJettyServer(int port, Class<? extends UI> uiClass, String webappDirectory) throws IOException {
        this(port, uiClass, DefaultUIProvider.class, webappDirectory, DEFAULT_CONTEXT_PATH);
    }

    public VaadinJettyServer(int port, Class<? extends UI> uiClass, String webappDirectory, String contextPath) throws IOException {
        this(port, uiClass, DefaultUIProvider.class, webappDirectory, contextPath);
    }

    public VaadinJettyServer(int port, Class<? extends UI> uiClass, Class<? extends UIProvider> uiProvider, String webappDirectory, String contextPath) throws IOException {
        super(port);

        ServletHolder servlet = buildVaadinServlet(uiClass, uiProvider);
        createIfDoesntExists(webappDirectory);

        WebAppContext context = new WebAppContext(webappDirectory, contextPath);
        context.addServlet(servlet, "/*");
        setHandler(context);
    }

    private ServletHolder buildVaadinServlet(Class<? extends UI> uiClass, Class<? extends UIProvider> uiProvider) {
        ServletHolder servlet = new ServletHolder(new VaadinServlet());
        servlet.setInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER, uiProvider.getName());
        servlet.setInitParameter("UI", uiClass.getName());
        return servlet;
    }

    private void createIfDoesntExists(String directory) throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

}
