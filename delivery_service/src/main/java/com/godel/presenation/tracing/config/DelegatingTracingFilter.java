package com.godel.presenation.tracing.config;

import brave.Tracer;
import brave.Tracing;
import brave.baggage.BaggagePropagation;
import brave.opentracing.BraveTracer;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.StrictScopeDecorator;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.servlet.TracingFilter;
import io.opentracing.util.GlobalTracer;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.brave.ZipkinSpanHandler;
import zipkin2.reporter.urlconnection.URLConnectionSender;

import javax.servlet.*;
import java.io.IOException;

public class DelegatingTracingFilter implements Filter {

  private static final String SERVICE_NAME = "delivery_service";
  private static final String TRACING_URL = "http://localhost:10483/api/v2/spans";

  private Sender sender;
  private AsyncReporter<Span> spanReporter;
  private Tracing tracing;
  private Filter delegate;

  @Override
  public void init(FilterConfig filterConfig) {

    sender = URLConnectionSender.create(TRACING_URL);
    spanReporter = AsyncReporter.create(sender);

    CurrentTraceContext currentTraceContext =
        ThreadLocalCurrentTraceContext.newBuilder()
            .addScopeDecorator(StrictScopeDecorator.create())
            .build();

    tracing =
        Tracing.newBuilder()
            .localServiceName(SERVICE_NAME)
            .currentTraceContext(currentTraceContext)
            .propagationFactory(BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY).build())
            .addSpanHandler(ZipkinSpanHandler.create(spanReporter))
            .build();

    BraveTracer braveOpenTracing = BraveTracer.create(tracing);

    GlobalTracer.registerIfAbsent(braveOpenTracing);

    delegate = TracingFilter.create(tracing);
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    delegate.doFilter(servletRequest, servletResponse, filterChain);
  }

  @Override
  public void destroy() {

    try {
      tracing.close(); // disables Tracing.current()
      spanReporter.close(); // stops reporting thread and flushes data
      sender.close(); // closes any transport resources
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
