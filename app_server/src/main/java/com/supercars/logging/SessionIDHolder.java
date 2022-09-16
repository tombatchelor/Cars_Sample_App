/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.logging;

/**
 *
 * @author tombatchelor
 */
public class SessionIDHolder {
    private static ThreadLocal<String> sessionID = new ThreadLocal<String>();
    private static ThreadLocal<String> username = new ThreadLocal<String>();
    
    public static void setSessionID(String sessionID) {
        SessionIDHolder.sessionID.set(sessionID);
    }
    
    public static String getSessionID() {
        String sessionID = SessionIDHolder.sessionID.get();
        if (sessionID == null) {
            return "BLANK";
        } else {
            return sessionID;
        }
    }

    public static void setUserName(String username) {
        SessionIDHolder.username.set(username);
    }

    public static String getUsername() {
        String username = SessionIDHolder.username.get();
        if (username == null) {
            return "BLANK";
        } else {
            return username;
        }
    }
}
