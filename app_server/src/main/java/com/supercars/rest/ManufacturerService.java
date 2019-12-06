/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.supercars.Manufacturer;
import com.supercars.dataloader.ManufacturerDataLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom.batchelor
 */
@Path("/manufacturer")
public class ManufacturerService {
    
    private final static Logger logger = Logger.getLogger(ManufacturerService.class.getName());
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Manufacturer> getManufacturers() {
        logger.fine("GET Getting manufacturers");
        List<Manufacturer> manufacturers = new ManufacturerDataLoader().getManufacturers();
        
        logger.log(Level.FINE, "Returning {0} manufacturers", manufacturers.size());
        return manufacturers;
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Manufacturer getManufacturer(@PathParam("id") int id) {
        logger.log(Level.FINE, "GET Getting manufactureID: {0}", id);
        Manufacturer manufacturer = new ManufacturerDataLoader().getManufacturer(id);
        
        logger.log(Level.FINE, "Returning manufacturer {0}", manufacturer.toString());
        return manufacturer;
    }
}
