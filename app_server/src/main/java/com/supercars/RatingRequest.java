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

    public RatingRequest(int manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    private int manufacturerId;
}
