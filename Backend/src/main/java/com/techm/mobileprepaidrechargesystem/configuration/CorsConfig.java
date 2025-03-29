//package com.techm.mobileprepaidrechargesystem.configuration;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//	@Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // Set your frontend origin
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
//        config.setAllowedHeaders(List.of("*"));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}
