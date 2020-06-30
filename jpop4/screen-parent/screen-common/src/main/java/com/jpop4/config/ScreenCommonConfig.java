package com.jpop4.config;


import com.jpop4.error.resolver.ErrorResolver;
import com.jpop4.i18n.MessageResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@Configuration
@ComponentScan({
        "com.jpop4.aspect",
        "com.jpop4.error",
        "com.jpop4.exceptions",
        "com.jpop4.i18n",
        "com.jpop4.validation",
})
public class ScreenCommonConfig {

    @Bean
    public MessageResolver messageResolver() {
        return new MessageResolver();
    }

    @Bean
    public ErrorResolver errorResolver() {
        return new ErrorResolver(messageResolver());
    }
}
