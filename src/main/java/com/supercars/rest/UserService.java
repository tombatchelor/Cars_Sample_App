/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.usermanagement.User;
import com.supercars.usermanagement.UserManager;
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
    
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(User user, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        return UserManager.login(user, session);
    }
    
    @Path("/logout")
    @GET
    public boolean logout(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        return UserManager.logout(session);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getLoggedInUser(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        return UserManager.getUserForSession(session);
    }
}
