package com.jpop4.webapp.config;

import com.jpop4.config.BookConfig;
import com.jpop4.config.BookScreenConfig;
import com.jpop4.config.ScreenCommonConfig;


public class BookServiceWebInitializer extends AbstractWebInitializer {
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                BookConfig.class,
                BookScreenConfig.class,
                ScreenCommonConfig.class
        };
    }
}
