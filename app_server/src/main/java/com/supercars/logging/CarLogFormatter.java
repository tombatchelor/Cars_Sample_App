/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.logging;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import io.github.devatherock.json.formatter.JSONFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author tombatchelor
 */
public class CarLogFormatter extends JSONFormatter {

    @Override
    public String format​(LogRecord record) {
        String message = record.getMessage();
        Map<String, String> obj = new HashMap<>();
        obj.put("sessionID", SessionIDHolder.getSessionID());
        obj.put("spanID", getSpanID());
        obj.put("traceID", getTraceID());
        obj.put("message", message);
        try {
            message = (new ObjectMapper()).writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        record.setMessage(message);
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
