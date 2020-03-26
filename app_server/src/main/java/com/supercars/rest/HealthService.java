/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.dataloader.Constants;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tombatchelor
 */
@Path("/health")
public class HealthService {

    private final static Logger logger = Logger.getLogger(HealthService.class.getName());

    private static boolean shouldSendUnhealthy = false;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHealth() {
        logger.fine("GET Returning service health");
        if (shouldSendUnhealthy) {
            for (int i = 0; i < 100; i++) {
                logger.log(Level.SEVERE, "Out of Memory", new java.lang.OutOfMemoryError("Out of Memory"));
            }
            return Response.serverError().entity("Out of Memory").build();
        }
        
        boolean dbError = false;
        for (int i = 0; i < 20; i++) {
            try ( Connection connection = Constants.getDBConnectionStandardPool()) {
                if (connection == null) {
                    logger.severe("Could not get DB connection");
                    dbError = true;
                } else {
                    logger.fine("Got DB Connection Okay");
                    break;
                }
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                dbError = true;
            }
        }
        if (dbError)
            return Response.serverError().entity("EXCEPTION_GETTING_DB_CONNECTION").build();

        logger.fine("Service okay");
        return Response.ok("OK").build();
    }

    public static void setAsUnhealthy() {
        shouldSendUnhealthy = true;
    }
}
