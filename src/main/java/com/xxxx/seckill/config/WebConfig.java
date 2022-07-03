package com.xxxx.seckill.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置类
 */
@Configuration//与xml中配置的bean意思一样。
@EnableWebMvc //@EnableWebMvc是使用Java 注解快捷配置Spring Webmvc的一个注解。在使用该注解后配
// 置一个继承于WebMvcConfigurerAdapter的配置类即可配置好Spring Webmvc。
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    //WebMvcConfigurer接口默认拦截静态资源，需要重写addResourceHandlers方法


    /**
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    //视频没写这个但是不加静态资源会找不到
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }



}
