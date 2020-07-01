package com.jpop4;

import com.jpop4.config.BaseDbConfig;
import com.jpop4.config.BookConfig;
import com.jpop4.config.BookScreenConfig;
import com.jpop4.config.ScreenCommonConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BookServiceWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                BaseDbConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                BookConfig.class,
                ScreenCommonConfig.class,
                BookScreenConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
