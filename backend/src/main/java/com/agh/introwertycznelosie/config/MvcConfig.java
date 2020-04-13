package main.java.com.agh.introwertycznelosie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/static/**")
////                .addResourceLocations("/frontend/build");
////        registry.addResourceHandler("/*.js")
////                .addResourceLocations("/frontend/build/static/js", "/frontend/build");
////        registry.addResourceHandler("/*.json")
////                .addResourceLocations("/frontend/build");
////        registry.addResourceHandler("/*.ico")
////                .addResourceLocations("/frontend/build");
////        registry.addResourceHandler("/index.html")
////                .addResourceLocations("/frontend/build/index.html");
//    }
}