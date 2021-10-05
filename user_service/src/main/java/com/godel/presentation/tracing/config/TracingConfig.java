package com.godel.presentation.tracing.config;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    private final Tracing tracing;

    @Autowired
    public TracingConfig(Tracing tracing) {
        this.tracing = tracing;
    }

    @Bean
    Tracer globalTracer(Tracing tracing) {
        BraveTracer globalTracer = BraveTracer.create(tracing);
        GlobalTracer.registerIfAbsent(globalTracer);
        return globalTracer;
    }
}
