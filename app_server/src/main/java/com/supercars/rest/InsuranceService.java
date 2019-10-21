/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.InsuranceQuote;
import com.supercars.externaldata.InsuranceQuotes;
import com.supercars.logging.CarLogger;
import com.supercars.tracing.TracingHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tombatchelor
 */
@Path("/insurance")
public class InsuranceService {
    
    private final static Logger logger = Logger.getLogger(InsuranceService.class.getName());

    static {
        CarLogger.setup(InsuranceService.class.getName());
    }
    
    @Path("quote/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public InsuranceQuote getQuote(@PathParam("id") int id) {
        logger.log(Level.FINE, "GET Getting insurance quote for car ID: {0}", id);
        InsuranceQuote quote = InsuranceQuotes.getQuote(id);
        
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.insurance.Company", quote.getCompany());
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.insurance.Price", (long) quote.getPrice());
        
        logger.log(Level.FINE, "Returning Insurance quote: {0}", quote.toString());
        return quote;
    }
}
