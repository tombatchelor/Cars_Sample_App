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
import com.supercars.tracing.TracingHelper;

/**
 *
 * @author tom.batchelor
 */
@Path("/enquiry")
public class EnquiryService {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Enquiry getEnqury(@PathParam("id") int id) {
        Enquiry enquiry = new EnquiryDataLoader().getEnquiry(id);
        
        return enquiry;
    }
    
    @Path("{carId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Enquiry> getEnquiryForCar(@PathParam("carId") int carId) {
        List<Enquiry> enquiries = new EnquiryDataLoader().getEnquirysForCar(carId);
        
                
        // Add number of cars to span
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.EnquiryCount", enquiries.size());
        return enquiries;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveEnquiry(Enquiry enquiry) {
        new EnquiryDataLoader().saveEnquiry(enquiry);
    }
    
}
