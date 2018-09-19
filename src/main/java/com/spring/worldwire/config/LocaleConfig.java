package com.spring.worldwire.config;

import java.util.Locale;

import com.spring.worldwire.companent.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig {

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		MyLocaleResolver slr = new MyLocaleResolver();
		// 默认语言
		slr.setDefaultLocale(Locale.US);
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
