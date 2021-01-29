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
import zipkin2.reporter.brave.AsyncZipkinSpanHandler;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 *
 * @author tombatchelor
 */
public class TracingBuilder {

    private static final TracingBuilder traceBuilder = new TracingBuilder();

    private final OkHttpSender sender;
    private final AsyncZipkinSpanHandler spanReporter;
    private final Map<String, Tracing> tracings;

    private TracingBuilder() {

        sender = OkHttpSender.create(getZipkinEndpoint());
        //sender = URLConnectionSender.create(getZipkinSink());
        ZipkinMetricReporter metrics = new ZipkinMetricReporter();
        spanReporter = AsyncZipkinSpanHandler.newBuilder(sender)
                .metrics(metrics)
                .messageMaxBytes(2000 * 1024)
                .queuedMaxSpans(20000)
                .queuedMaxBytes((int) (Runtime.getRuntime().totalMemory() * 0.05))
                .build();
        Thread t = new Thread(metrics);
        t.start();
        tracings = new HashMap();
    }

    private static String getZipkinEndpoint() {
        String proxyEndpoint = System.getenv("PROXY_ENDPOINT");
        if (proxyEndpoint == null) {
            proxyEndpoint = "http://localhost:9411/";
        }
        return proxyEndpoint + ":9411/api/v2/spans";
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
                    .addSpanHandler(spanReporter)
                    .build();
            tracings.put(serviceName, tracing);
            return tracing;
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    protected void finalize() {
        for (Tracing tracing : tracings.values()) {
            tracing.close();
        }
        spanReporter.close();
        sender.close();
    }
}
