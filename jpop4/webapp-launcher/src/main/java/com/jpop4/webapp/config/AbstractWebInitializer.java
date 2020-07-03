package com.jpop4.webapp.config;

import com.jpop4.config.ApiDocumentationConfig;
import com.jpop4.config.BaseDbConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public abstract class AbstractWebInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                BaseDbConfig.class,
                ApiDocumentationConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
