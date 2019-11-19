/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.usermanagement;

import java.io.Serializable;

/**
 *
 * @author tom.batchelor
 */
public class User implements Serializable {
    
    private String username;
    private String password;

    public User() {
        username = null;
        password = null;
    }
    
    protected User (String username, String password) {
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
    
    public User clone() {
        return new User(this.username, this.password);
    }
}
