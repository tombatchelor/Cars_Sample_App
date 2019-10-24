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
public class LoanQuote {

    private double rate;
    private double payment;
    private int term;

    public LoanQuote() {
        rate = 0.0;
        payment = 0.0;
        term = 0;
    }
    
    public LoanQuote(double rate, double payment, int term) {
        this.rate = rate;
        this.payment = payment;
        this.term = term;
    }
    
    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(double payment) {
        this.payment = payment;
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
    
    @Override
    public String toString() {
        return "Payment: " + payment + " Rate: " + rate + " Term: " + term;
    }
}
