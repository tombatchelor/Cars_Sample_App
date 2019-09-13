/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.tracing;

import brave.Tracing;
import brave.servlet.TracingFilter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.urlconnection.URLConnectionSender;

/**
 *
 * @author tombatchelor
 */
public class DelegatingTracingFilter implements Filter {

    Sender sender = URLConnectionSender.create("http://127.0.0.1:9411/api/v2/spans");
    AsyncReporter<Span> spanReporter = AsyncReporter.create(sender);
    Tracing tracing = Tracing.newBuilder()
            .localServiceName("cars-app")
            .spanReporter(spanReporter).build();
    Filter delegate = TracingFilter.create(tracing);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        delegate.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        try {
            tracing.close(); // disables Tracing.current()
            spanReporter.close(); // stops reporting thread and flushes data
            sender.close(); // closes any transport resources
        } catch (IOException e) {
            // do something real
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        return;
    }
    
    public Tracing getTracing() {
        return tracing;
    }
}
