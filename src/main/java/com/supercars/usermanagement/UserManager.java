/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.usermanagement;

import javax.servlet.http.HttpSession;

/**
 *
 * @author tom.batchelor
 */
public class UserManager {
    
    private static User bob = new User("bob@test.com", "password");
    
    private static final String USER_ATTRIBUTE = "user";
    
    public static boolean login(User user, HttpSession session) {
        if (user.getUsername().equals(bob.getUsername()) && user.getPassword().equals(bob.getPassword())) {
            session.setAttribute(USER_ATTRIBUTE, bob);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean logout(HttpSession session) {
        session.setAttribute(USER_ATTRIBUTE, null);
        return true;
    }

    public static User getUserForSession(HttpSession session) {
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user == null) {
            user = new User();
        } else {
            user.setPassword(null);
        }
        
        return user;
    }
}
