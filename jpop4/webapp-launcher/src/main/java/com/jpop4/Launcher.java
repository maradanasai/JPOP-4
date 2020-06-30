package com.jpop4;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Server;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.slf4j.LoggerFactory;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Launcher {
    public static void main(String[] args) throws LifecycleException {
        long started = System.nanoTime();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.getConnector(); // Triggers creation of the default connector
        tomcat.enableNaming();
        Server server = tomcat.getServer();

        server.addLifecycleListener(event -> {
            if (event.getType().equals(Lifecycle.AFTER_START_EVENT)) {
                // Do not extract this logger into a constant since some logging properties are set by loadPropertiesToSystemProperties()
                LoggerFactory.getLogger(Launcher.class).info("Application Started in {} ms", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - started));
            }
        });

        addContext(BookServiceWebInitializer::new, tomcat, "");
        tomcat.start();
        server.await();
    }

    private static void addContext(Supplier<? extends WebApplicationInitializer> initializer, Tomcat tomcat, String contextPath) {
        LifecycleListener config = new Config(initializer);
        Context context = tomcat.addWebapp(tomcat.getHost(), contextPath, System.getProperty("user.dir"), config);

        StandardJarScanner jarScanner = (StandardJarScanner) context.getJarScanner();
        jarScanner.setScanBootstrapClassPath(false);
        jarScanner.setScanAllDirectories(false);
        jarScanner.setScanClassPath(false);
        jarScanner.setScanManifest(false);
    }

    private static class Config extends ContextConfig {

        final WebApplicationInitializer initializer;

        private Config(Supplier<? extends WebApplicationInitializer> initializer) {
            this.initializer = initializer.get();
        }

        @Override
        protected void processServletContainerInitializers() {
            super.processServletContainerInitializers();
            initializerClassMap.keySet().removeIf(it -> it.getClass() == SpringServletContainerInitializer.class);
            initializerClassMap.put((c, ctx) -> initializer.onStartup(ctx), new HashSet<>());
        }
    }
}
