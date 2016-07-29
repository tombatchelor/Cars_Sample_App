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
import supercars.Manufacturer;
import supercars.dataloader.ManufacturerDataLoader;

/**
 *
 * @author tom.batchelor
 */
@Path("/manufacturer")
public class ManufacturerService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Manufacturer> getManufacturers() {
        List<Manufacturer> manufacturers = new ManufacturerDataLoader().getManufacturers();
        
        return manufacturers;
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Manufacturer getManufacturer(@PathParam("id") int id) {
        Manufacturer manufacturer = new ManufacturerDataLoader().getManufacturer(id);
        
        return manufacturer;
    }
}
