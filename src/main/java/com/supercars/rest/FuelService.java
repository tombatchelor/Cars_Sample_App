/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.supercars.externaldata.FuelPrices;

/**
 *
 * @author tom.batchelor
 */
@Path("/fuel")
public class FuelService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FuelPrices getFuelPrices() {
        return FuelPrices.getFuelPrices();
    }
}
