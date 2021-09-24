/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.usermanagement;

import com.supercars.tracing.TracingHelper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tom.batchelor
 */
public class UserManager {

    private static final String USER_ATTRIBUTE = "user";
    private static final String STANDARD_PASSWORD = "password";

    public static boolean login(User user, HttpSession session) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if (password.equals(STANDARD_PASSWORD)) {
            session.setAttribute(USER_ATTRIBUTE, user);
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "user.username", user.getUsername());
            return true;
        }
        
        return false;
    }

    public static boolean logout(HttpSession session) {
        session.setAttribute(USER_ATTRIBUTE, null);
        return true;
    }

    public static User getUserForSession(HttpSession session) {
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user == null) {
            user = new User();
        }

        return user;
    }
}
