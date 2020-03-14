/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.logging;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author tombatchelor
 */
public class CarLogFormatter extends SimpleFormatter {

    @Override
    public String format​(LogRecord record) {
        String sessionID = SessionIDHolder.getSessionID();
        record.setMessage(sessionID + " " + getTraceID() + " " + getSpanID() + " " + record.getMessage());
        return super.format(record);
    }

    private String getSpanID() {
        Tracer tracer = Tracing.currentTracer();
        if (tracer != null) {
            Span span = tracer.currentSpan();
            if (span != null) {
                return span.context().spanIdString();
                
            }
        }
        return "BLANK";
    }
    
    private String getTraceID() {
        Tracer tracer = Tracing.currentTracer();
        if (tracer != null) {
            Span span = tracer.currentSpan();
            if (span != null) {
                return span.context().traceIdString();
            }
        }
        return "BLANK";
    }
}
