/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.supercars.Leak;
import com.supercars.dataloader.CarDataLoader;
import com.supercars.externaldata.CarRating;
import com.supercars.externaldata.S3Images;
import com.supercars.externaldata.Zendesk;
import com.supercars.tracing.TracingHelper;
import com.supercars.usermanagement.UserManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 *
 * @author tom.batchelor
 */
@Path("/car")
public class CarService {

    private final static Logger logger = Logger.getLogger(CarService.class.getName());

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar(@PathParam("id") int carID,  @javax.ws.rs.core.Context HttpServletRequest request) {
        logger.log(Level.FINE, "GET request for carID: {0}", carID);
        Car car = new CarDataLoader().getCar(carID);

        if (car != null) {
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Manufacturer", car.getManufacturer().getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Name", car.getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Model", car.getModel());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Price", car.getPrice());
        }

        S3Images.getImage("IMG_" + car.getCarId() + ".jpeg");
        car.setRating(CarRating.getCarRating(car.getCarId()).getRating());

        try {
            if (!HealthService.isHealthy()) {
                String username = UserManager.getUserForSession(request.getSession()).getUsername();
                Zendesk.sendZendeskTicket(username, car.getManufacturer().getName());
                throw new OutOfMemoryError("Out of Memory");
            }
        } catch (OutOfMemoryError ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return Response.status(503).build();
        }

        logger.log(Level.FINE, "Returing car {0}", car.toString());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(car);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (JsonProcessingException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return Response.status(503).build();
        }
    }

    @Path("/manufacturer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCarsForManufacturer(@PathParam("id") int id) {
        logger.log(Level.FINE, "GET request for cars for manufacturerID: {0}", id);
        List<Car> cars = new CarDataLoader().getCarsByManufacturer(id);

        for (Car car : cars) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        // Add number of cars to span
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.CarCount", cars.size());

        logger.log(Level.FINE, "Returning {0} cars for manufacturerID: {1}", new Object[]{cars.size(), id});
        return cars;
    }

    @Path("{query}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> searchCars(@PathParam("query") String query) {
        logger.log(Level.FINE, "POST request search for cars for query: {0}", query);
        List<Car> cars = new CarDataLoader().getCarsBySearch(query);

        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.SearchQuery", query);
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.CarCount", cars.size());

        logger.log(Level.FINE, "Returning {0} cars for query: {1}", new Object[]{cars.size(), query});
        return cars;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        logger.log(Level.FINE, "PUT request for car");
        if (car != null) {
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Name", car.getName());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Model", car.getModel());
            TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.Price", car.getPrice());

            int carId = new CarDataLoader().saveCar(car);
            car.setCarId(carId);

            if (carId > 0) {
                try {
                    Context initContext = new InitialContext();
                    Context webContext = (Context) initContext.lookup("java:/comp/env");
                    String imageBase64 = (String) webContext.lookup("image_base64");
                    byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
                    File file = File.createTempFile("IMG_" + carId, ".jpeg");
                    OutputStream os = new FileOutputStream(file);
                    os.write(imageBytes);
                    os.close();
                    S3Images.saveImage(file, "IMG_" + carId + ".jpeg");
                } catch (NamingException | IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }

            logger.log(Level.FINE, "Saved car {0}", car.toString());
        }
    }
}
