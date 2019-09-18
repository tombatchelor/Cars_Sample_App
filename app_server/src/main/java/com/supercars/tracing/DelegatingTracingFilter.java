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

/**
 *
 * @author tombatchelor
 */
public class DelegatingTracingFilter implements Filter {

    Tracing tracing = TracingBuilder.getInstance().getTracing("cars-app");
    Filter delegate = TracingFilter.create(tracing);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        delegate.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        return;
    }
    
    public Tracing getTracing() {
        return tracing;
    }
}
