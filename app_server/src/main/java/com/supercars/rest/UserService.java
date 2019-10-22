/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.usermanagement.User;
import com.supercars.usermanagement.UserManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tom.batchelor
 */
@Path("/user")
public class UserService {
    
    private final static Logger logger = Logger.getLogger(UserService.class.getName());
    
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(User user, @Context HttpServletRequest request) {
        logger.log(Level.FINE, "POST Logging in user: {0}", user.getUsername());
        HttpSession session = request.getSession();
        boolean loggedIn = UserManager.login(user, session);
        logger.log(Level.FINE, "User: {0} logged in: {1}", new Object[]{user.getUsername(), loggedIn});
        return loggedIn;
    }
    
    @Path("/logout")
    @GET
    public boolean logout(@Context HttpServletRequest request) {
        logger.log(Level.FINE, "GET Logging out user: {0}", UserManager.getUserForSession(request.getSession()).getUsername());
        HttpSession session = request.getSession();
        boolean loggedOut = UserManager.logout(session);
        logger.log(Level.FINE, "Logged out result: {0}", loggedOut);
        return loggedOut;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getLoggedInUser(@Context HttpServletRequest request) {
        logger.fine("GET Get logged in user");
        HttpSession session = request.getSession();
        User user = UserManager.getUserForSession(session);
        logger.log(Level.FINE, "Logged in user is: {0}", user.getUsername());
        return user;
    }
}
