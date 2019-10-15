/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.dataloader.Constants;
import com.supercars.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHealth() {
        try (Connection connection = Constants.getDBConnectionStandardPool()) {
            if (connection == null) {
                return Response.serverError().entity("NO_DB_CONNECTION").build();
            } else {
            }
        } catch (Exception ex) {
            Logger.log(ex);
            return Response.serverError().entity("EXCEPTION_GETTING_DB_CONNECTION").build();
        }
        
        return Response.ok("OK").build();
    }
}
