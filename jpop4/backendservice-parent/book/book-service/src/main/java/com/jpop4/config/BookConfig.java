package com.jpop4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({
        "com.jpop4.repository",
        "com.jpop4.service"
})
public class BookConfig {
}
