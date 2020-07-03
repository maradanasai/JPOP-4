package com.jpop4.config;


import com.jpop4.error.resolver.ErrorResolver;
import com.jpop4.i18n.MessageResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


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
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageResolver messageResolver() {
        return new MessageResolver();
    }

    @Bean
    public ErrorResolver errorResolver() {
        return new ErrorResolver(messageResolver());
    }
}
