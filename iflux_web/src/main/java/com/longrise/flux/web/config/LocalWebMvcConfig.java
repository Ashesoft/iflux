package com.longrise.flux.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class LocalWebMvcConfig implements WebFluxConfigurer {
    @Value("${address.audio.intercept}")
    private String audioIntercept;
    @Value("${address.audio.redirect}")
    private String audioRedirect;

    @Autowired
    private Environment env;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 将服务器请求资源的地址映射到本地路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(audioIntercept).addResourceLocations(audioRedirect);
        registry.addResourceHandler(env.getProperty("address.video.intercept")).addResourceLocations(env.getProperty(
                "address.video.redirect"));
    }
}
