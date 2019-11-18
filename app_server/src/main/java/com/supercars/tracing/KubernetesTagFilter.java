/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.tracing;

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
public class KubernetesTagFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "kubernetes.pod.name", System.getenv("POD_NAME"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
