/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.logging;

import com.supercars.tracing.TracingHelper;
import io.github.devatherock.json.formatter.JSONFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author tombatchelor
 */
public class CarLogFormatter extends Formatter {

    public enum ExceptionKeys {
        exception_class, exception_message, stack_trace
    }

    @Override
    public String format​(LogRecord record) {
        String message = super.formatMessage(record);
        Map<String, Object> obj = new HashMap<>();
        obj.put("@timestamp", record.getMillis() * 1000000);
        obj.put("logger_name", record.getLoggerName());
        obj.put("level", renameLogLevel(record.getLevel()));
        obj.put("class", record.getSourceClassName());
        obj.put("method", record.getSourceMethodName());
        obj.put("sessionID", SessionIDHolder.getSessionID());
        obj.put("spanID", TracingHelper.getSpanID());
        obj.put("traceID", TracingHelper.getTraceID());
        obj.put("message", message);

        if (null != record.getThrown()) {
            Map<ExceptionKeys, Object> exceptionInfo = new EnumMap<>(ExceptionKeys.class);
            exceptionInfo.put(ExceptionKeys.exception_class, record.getThrown().getClass().getName());

            if (record.getThrown().getMessage() != null) {
                exceptionInfo.put(ExceptionKeys.exception_message, record.getThrown().getMessage());
            }

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            record.getThrown().printStackTrace(pw);
            pw.close();
            exceptionInfo.put(ExceptionKeys.stack_trace, sw.toString());
            obj.put("exception", exceptionInfo);
        }

        try {
            message = (new ObjectMapper()).writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        record.setMessage(message);
        return message;
    }

    private String renameLogLevel(Level logLevel) {
        switch (logLevel.getName()) {
        case "FINEST":
            return "TRACE";
        case "FINER":
        case "FINE":
            return "DEBUG";
        case "INFO":
        case "CONFIG":
            return "INFO";
        case "WARNING":
            return "WARN";
        case "SEVERE":
            return "ERROR";
        default:
            return "INFO";
        }
    }
}
