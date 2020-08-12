package com.carbooking.carbookingapi.config;

import com.carbooking.carbookingapi.interceptor.CarBookingInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  private CarBookingInterceptor carBookingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(carBookingInterceptor)
      .excludePathPatterns("/users/login");
  }

}

/* @Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
  
  @Autowired
  private CarBookingInterceptor carBookingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(carBookingInterceptor).excludePathPatterns("/users/login");
  }
} */
