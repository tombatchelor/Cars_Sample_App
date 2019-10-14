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
public class LoanQuoteRequest {

    
    private int price;
    private int loanAmount;
    private int term;
    
    public LoanQuoteRequest(int price, int loanAmount, int term) {
        this.price = price;
        this.loanAmount = loanAmount;
        this.term = term;
    }
    
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
     * @return the loanAmount
     */
    public int getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the term
     */
    public int getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }
}
