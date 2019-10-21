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
public class InsuranceQuoteRequest {

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the excess
     */
    public int getExcess() {
        return excess;
    }

    /**
     * @param excess the excess to set
     */
    public void setExcess(int excess) {
        this.excess = excess;
    }

    /**
     * @return the liability
     */
    public int getLiability() {
        return liability;
    }

    /**
     * @param liability the liability to set
     */
    public void setLiability(int liability) {
        this.liability = liability;
    }
    
    private int price;
    private int year;
    private String manufacturer;
    private int excess;
    private int liability;
    
    public InsuranceQuoteRequest(int price, int year, String manufacturer, int excess, int liability) {
        this.price = price;
        this.year = year;
        this.manufacturer = manufacturer;
        this.excess = excess;
        this.liability = liability;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Manufacturer ")
                .append(manufacturer)
                .append(" Year: ")
                .append(year)
                .append(" Price: ")
                .append(price)
                .append(" Excess: ")
                .append(excess)
                .append(" Liability: ")
                .append(liability);
        return sb.toString();
    }
}
