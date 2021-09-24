/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.usermanagement;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author tom.batchelor
 */
public class User implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String location;
    private int companyId;
    private String companyName;
    private String tier;
    private String acv;
    private String accountOwner;
    private String techOwner;

    public User() {
        username = null;
        password = null;
    }

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the companyId
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * @param tier the tier to set
     */
    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * @return the acv
     */
    public String getAcv() {
        return acv;
    }

    /**
     * @param acv the acv to set
     */
    public void setAcv(String acv) {
        this.acv = acv;
    }

    /**
     * @return the accountOwner
     */
    public String getAccountOwner() {
        return accountOwner;
    }

    /**
     * @param accountOwner the accountOwner to set
     */
    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    /**
     * @return the techOwner
     */
    public String getTechOwner() {
        return techOwner;
    }

    /**
     * @param techOwner the techOwner to set
     */
    public void setTechOwner(String techOwner) {
        this.techOwner = techOwner;
    }

    public String toJSON() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("email", username);
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("location", location);
        obj.put("companyId", companyId);
        obj.put("companyName", companyName);
        obj.put("tier", tier);
        obj.put("acv", acv);
        obj.put("accountOwner", accountOwner);
        obj.put("techOwner", techOwner);

        String jsonString = "";
        try {
            jsonString = (new ObjectMapper()).writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return jsonString;
    }
}
