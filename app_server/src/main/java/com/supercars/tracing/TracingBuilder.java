/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.tracing;

import brave.Tracing;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.urlconnection.URLConnectionSender;

/**
 *
 * @author tombatchelor
 */
public class TracingBuilder {

    private static final TracingBuilder traceBuilder = new TracingBuilder();

    private final Sender sender;
    private final AsyncReporter<Span> spanReporter;
    private final Map<String, Tracing> tracings;

    private TracingBuilder() {
        
        sender = URLConnectionSender.newBuilder().endpoint(getZipkinSink()).build();
        //sender = URLConnectionSender.create(getZipkinSink());
        spanReporter = AsyncReporter.create(sender);
        tracings = new HashMap();
    }

    private static String getZipkinSink() {
        String zipkinSink = "";
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            zipkinSink = (String) webContext.lookup("zipkinSink");
        } catch (NamingException ex) {
            Logger.getLogger(TracingBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return zipkinSink;
    }

    public static TracingBuilder getInstance() {
        return traceBuilder;
    }

    public Tracing getTracing(String serviceName) {
        if (tracings.containsKey(serviceName)) {
            return tracings.get(serviceName);
        } else {
            Tracing tracing = Tracing.newBuilder()
                    .localServiceName(serviceName)
                    .spanReporter(spanReporter).build();
            tracings.put(serviceName, tracing);
            return tracing;
        }
    }

    @Override
    protected void finalize() {
        try {
            for (Tracing tracing : tracings.values()) {
                tracing.close();
            }
            spanReporter.close();
            sender.close();
        } catch (IOException ex) {
            Logger.getLogger(TracingBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
