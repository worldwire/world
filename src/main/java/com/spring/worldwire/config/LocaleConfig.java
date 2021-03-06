package com.spring.worldwire.config;

import com.spring.worldwire.companent.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        MyLocaleResolver slr = new MyLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/messages");
        source.setFallbackToSystemLocale(false);
        source.setDefaultEncoding("utf-8");
        return source;
    }

}
