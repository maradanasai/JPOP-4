package com.jpop4.webapp.config;

import com.jpop4.config.UserConfig;
import com.jpop4.config.UserScreenConfig;
import com.jpop4.config.ScreenCommonConfig;


public class UserServiceWebInitializer extends AbstractWebInitializer {
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                UserConfig.class,
                UserScreenConfig.class,
                ScreenCommonConfig.class,
        };
    }
}
