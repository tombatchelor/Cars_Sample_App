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
import com.supercars.Car;
import com.supercars.dataloader.CarDataLoader;
import com.supercars.tracing.TracingHelper;

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

        if (car != null) {
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Manufacturer", car.getManufacturer().getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Name", car.getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Model", car.getModel());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Price", car.getPrice());
        }
        
        return car;
    }
    
    @Path("/manufacturer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCarsForManufacturer(@PathParam("id") int id) {
        List<Car> cars = new CarDataLoader().getCarsByManufacturer(id);
        
        // Add number of cars to span
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.CarCount", cars.size());
        
        return cars;
    }

    @Path("{query}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> searchCars(@PathParam("query") String query) {
        List<Car> cars = new CarDataLoader().getCarsBySearch(query);
        
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.SearchQuery", query);
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.CarCount", cars.size());
        
        return cars;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        if (car != null) {
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Name", car.getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Model", car.getModel());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Price", car.getPrice());
        }
        
        new CarDataLoader().saveCar(car);
    }
}
