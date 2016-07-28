/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import supercars.Car;
import supercars.dataloader.CarDataLoader;
import supercars.logging.Logger;

/**
 *
 * @author tom.batchelor
 */
@Path("/car")
public class CarService {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("id") int id) {
        Car car = new CarDataLoader().getCar(id);

        return car;
    }

    @Path("{query}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> searchCars(@PathParam("query") String query) {

        List<Car> cars = new CarDataLoader().getCarsBySearch(query);
        return cars;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        new CarDataLoader().saveCar(car);
    }
}
