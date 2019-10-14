/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.tracing;

import brave.Tracer;
import brave.Tracing;

/**
 *
 * @author tombatchelor
 */
public class TracingHelper {
    
    public static final String CARS_APP_NAME = "cars-app";
    public static final String FUEL_PRICES_NAME = "fuel-prices";
    public static final String INSURANCE_NAME = "insurance-service";
    public static final String RATING_NAME = "car-rating";
    public static final String LOAN_NAME = "car-loan";
    
    public static Tracing getTracing(String serviceName) {
        return TracingBuilder.getInstance().getTracing(serviceName);
    }
    
    public static Tracer getTracer(String serviceName) {
        return getTracing(serviceName).tracer();
    }
    
    public static void tag(String serviceName, String key, String value) {
        getTracer(serviceName).currentSpanCustomizer().tag(key, value);
    }
    
    public static void tag(String serviceName, String key, double value) {
        tag(serviceName, key, String.valueOf(value));
    }
    
    public static void tag(String serviceName, String key, int value) {
        tag(serviceName, key, String.valueOf(value));
    }
    
    public static void tag(String serviceName, String key, long value) {
        tag(serviceName, key, String.valueOf(value));
    }
}
