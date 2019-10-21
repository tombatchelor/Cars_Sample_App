/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.supercars.Enquiry;
import com.supercars.dataloader.EnquiryDataLoader;
import com.supercars.logging.CarLogger;
import com.supercars.tracing.TracingHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom.batchelor
 */
@Path("/enquiry")
public class EnquiryService {

    private final static Logger logger = Logger.getLogger(EnquiryService.class.getName());

    static {
        CarLogger.setup(EnquiryService.class.getName());
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Enquiry getEnqury(@PathParam("id") int id) {
        logger.log(Level.FINE, "GET Getting enquiry ID: {0}", id);
        Enquiry enquiry = new EnquiryDataLoader().getEnquiry(id);

        logger.log(Level.FINE, "Returing enquiry {0}", enquiry.toString());
        return enquiry;
    }

    @Path("{carId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Enquiry> getEnquiryForCar(@PathParam("carId") int carId) {
        logger.log(Level.FINE, "POST Getting enquiries for carID: {0}", carId);
        List<Enquiry> enquiries = new EnquiryDataLoader().getEnquirysForCar(carId);

        // Add number of cars to span
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.EnquiryCount", enquiries.size());
        logger.log(Level.FINE, "Returing {0} enquiries", enquiries.size());
        return enquiries;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveEnquiry(Enquiry enquiry) {
        logger.fine("PUT Saving enquiry");
        new EnquiryDataLoader().saveEnquiry(enquiry);
        logger.log(Level.FINE, "Saved enquiry {0}", enquiry.toString());
    }

}
