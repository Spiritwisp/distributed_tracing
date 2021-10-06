package com.godel.presentation.tracing.config;

import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

  private static final String JAEGER_COLLECTOR_HOST = "localhost";
  private static final int JAEGER_COLLECTOR_PORT = 10484;
  private static final String SERVICE_NAME = "coking-service";

  @Bean
  public Tracer jaegerTracer() {

    io.jaegertracing.Configuration.SamplerConfiguration samplerConfig =
        io.jaegertracing.Configuration.SamplerConfiguration.fromEnv()
            .withType(ConstSampler.TYPE)
            .withParam(1);

    io.jaegertracing.Configuration.SenderConfiguration senderConfiguration =
        io.jaegertracing.Configuration.SenderConfiguration.fromEnv()
            .withAgentHost(JAEGER_COLLECTOR_HOST)
            .withAgentPort(JAEGER_COLLECTOR_PORT);

    io.jaegertracing.Configuration.ReporterConfiguration reporterConfig =
        io.jaegertracing.Configuration.ReporterConfiguration.fromEnv()
            .withSender(senderConfiguration)
            .withLogSpans(true);

    io.jaegertracing.Configuration.CodecConfiguration codecConfiguration =
        io.jaegertracing.Configuration.CodecConfiguration.fromEnv()
            .withPropagation(io.jaegertracing.Configuration.Propagation.B3);

    io.jaegertracing.Configuration config =
        new io.jaegertracing.Configuration(SERVICE_NAME)
            .withSampler(samplerConfig)
            .withReporter(reporterConfig)
            .withCodec(codecConfiguration);

    Tracer tracer = config.getTracer();

    GlobalTracer.registerIfAbsent(tracer);
    return tracer;
  }
}
