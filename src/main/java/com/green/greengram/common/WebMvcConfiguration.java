package com.green.greengram.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final String uploadPath;

    public WebMvcConfiguration(@Value("${file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + uploadPath);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/**")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestResouce = location.createRelative(resourcePath);

                        if (requestResouce.exists() && requestResouce.isReadable()) {   // 진짜있는파일의 요청이라면
                            return requestResouce;
                        }
                        return new ClassPathResource("/static/index.html"); // 존재하지않는다면 여기로가서써라

                    }
                });//classpath -> static

    }
}
