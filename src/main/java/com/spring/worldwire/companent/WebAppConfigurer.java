package com.spring.worldwire.companent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther pg
 * @Date create in 22:32 2018/7/13
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getHandlerInterceptor(){
        return new UserInterceptor();
    }

    @Bean
    public HandlerInterceptor getLoginHandlerInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getLoginHandlerInterceptor()).addPathPatterns("/**/lc/**");
        super.addInterceptors(registry);
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }

}
