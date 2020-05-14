package io.demo.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.demo.crud.interceptor.UploadInterceptor;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @FileRequired 注解,如果有file字段不能为空
        registry.addInterceptor(UploadInterceptor()).addPathPatterns("/**");
	 }
	 
	 @Bean
	 public UploadInterceptor UploadInterceptor() {
		 return new UploadInterceptor();
	 }
	 
}
