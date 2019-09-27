/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tombatchelor
 */
@XmlRootElement
public class Quote {

    private String company;
    private float price;

    public Quote() {
        company = "";
        price = 0;
    }

    public Quote(String company, float price) {
        this.company = company;
        this.price = price;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
