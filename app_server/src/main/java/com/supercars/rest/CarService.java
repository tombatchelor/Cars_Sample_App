/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import java.util.Base64;
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
import com.supercars.externaldata.S3Images;
import com.supercars.logging.LogLevel;
import com.supercars.logging.Logger;
import com.supercars.tracing.TracingHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

        S3Images.getImage("IMG_" + car.getCarId() + ".jpeg");
        return car;
    }

    @Path("/manufacturer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCarsForManufacturer(@PathParam("id") int id) {
        List<Car> cars = new CarDataLoader().getCarsByManufacturer(id);

        for (Car car : cars) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.log(ex);
            }
        }
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

        int carId = new CarDataLoader().saveCar(car);

        if (carId > 0) {
            try {
                Context initContext = new InitialContext();
                Context webContext = (Context) initContext.lookup("java:/comp/env");
                String imageBase64 = (String) webContext.lookup("image_base64");
                byte [] imageBytes = Base64.getDecoder().decode(imageBase64);
                File file = File.createTempFile("IMG_" + carId, ".jpeg");
                OutputStream os = new FileOutputStream(file);
                os.write(imageBytes);
                Logger.log(file.getAbsolutePath(), LogLevel.DEBUG);
                os.close();
                S3Images.saveImage(file, "IMG_" + carId + ".jpeg");
            } catch (NamingException | IOException ex) {
                Logger.log(ex);
            }
        }
    }
}
