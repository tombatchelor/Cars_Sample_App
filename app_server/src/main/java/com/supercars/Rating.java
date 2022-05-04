/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author tombatchelor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    private int rating;
    private String errorMessage;

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the rating
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param rating the rating to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
