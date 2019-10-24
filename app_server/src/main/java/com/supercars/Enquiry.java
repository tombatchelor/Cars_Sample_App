/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

/**
 *
 * @author tom.batchelor
 */
public class Enquiry {

    private int enquiryId;
    private String name = null;
    private String email = null;
    private String comment = null;
    private int carId = 0;

    /**
     * @return the enquiryId
     */
    public int getEnquiryId() {
        return enquiryId;
    }

    /**
     * @param enquiryId the enquiryId to set
     */
    public void setEnquiryId(int enquiryId) {
        this.enquiryId = enquiryId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the carId
     */
    public int getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enquiry ID: ")
                .append(enquiryId)
                .append(" Car ID: ")
                .append(carId)
                .append(" Name: ")
                .append(name)
                .append(" email: ")
                .append(email);
        return sb.toString();
    }
}
