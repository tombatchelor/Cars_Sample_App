/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

/**
 *
 * @author tombatchelor
 */
public class RatingRequest {

    public RatingRequest(int manufacturerId, String traceId) {
        this.manufacturerId = manufacturerId;
        this.traceId = traceId;
    }
    
    /**
     * @return the manufacturer
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    private int manufacturerId;
    private String traceId;
}
