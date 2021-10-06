package com.godel.presentation.tracing.config;

import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(javax.servlet.ServletContext servletContext) {

    AnnotationConfigWebApplicationContext applicationContext =
        new AnnotationConfigWebApplicationContext();
    applicationContext.register(WebConfig.class);
    applicationContext.setServletContext(servletContext);

    ServletRegistration.Dynamic dispatcherServlet =
        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext));
    dispatcherServlet.setLoadOnStartup(1);
    dispatcherServlet.addMapping("/");

    servletContext
        .addFilter("tracingFilter", TracingFilter.class)
        .addMappingForServletNames(null, false, "dispatcherServlet");
  }
}
