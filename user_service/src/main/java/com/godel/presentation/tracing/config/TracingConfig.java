package com.godel.presentation.tracing.config;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    public Tracer globalTracer(Tracing tracing) {
        BraveTracer globalTracer = BraveTracer.create(tracing);
        GlobalTracer.registerIfAbsent(globalTracer);
        return globalTracer;
    }
}
