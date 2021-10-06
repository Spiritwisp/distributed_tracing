package com.godel.presenation.tracing.config;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;

import javax.servlet.http.HttpServletRequest;

public class TracingFilter extends ClientFilter {

  private static final String PARENT_SPAN_ID = "X-B3-ParentSpanId";
  private static final String TRACE_ID = "X-B3-TraceId";
  private static final String SPAN_ID = "X-B3-SpanId";
  private static final String SAMPLED = "X-B3-Sampled";
  private static final Integer SAMPLED_VALUE = 1;

  private final HttpServletRequest httpRequest;

  public TracingFilter(HttpServletRequest httpRequest) {
    this.httpRequest = httpRequest;
  }

  @Override
  public ClientResponse handle(ClientRequest clientRequest) throws ClientHandlerException {
    Tracer tracer = GlobalTracer.get();
    Span activeSpan = tracer.activeSpan();

    activeSpan.setTag(Tags.PEER_SERVICE, getPeerService());

    addTracingParametersToRequest(clientRequest, activeSpan);
    return getNext().handle(clientRequest);
  }

  private void addTracingParametersToRequest(ClientRequest clientRequest, Span activeSpan) {
    clientRequest.getHeaders().putSingle(PARENT_SPAN_ID, activeSpan.context().toSpanId());
    clientRequest.getHeaders().putSingle(TRACE_ID, activeSpan.context().toTraceId());
    clientRequest.getHeaders().putSingle(SPAN_ID, activeSpan.context().toSpanId());
    clientRequest.getHeaders().putSingle(SAMPLED, SAMPLED_VALUE);
  }

  private String getPeerService() {
    int port = httpRequest.getServerPort() == -1 ? 80 : httpRequest.getServerPort();
    return httpRequest.getServerName() + ":" + port;
  }
}
