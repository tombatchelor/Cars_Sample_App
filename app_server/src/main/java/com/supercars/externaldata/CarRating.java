/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.externaldata;

import brave.Tracing;
import brave.jaxrs2.TracingClientFilter;
import com.supercars.Car;
import com.supercars.Manufacturer;
import com.supercars.Rating;
import com.supercars.RatingRequest;
import com.supercars.dataloader.CarDataLoader;
import com.supercars.preferences.Preference;
import com.supercars.preferences.PreferenceManager;
import com.supercars.tracing.TracingHelper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.supercars.preferences.PreferenceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tombatchelor
 */
public class CarRating {

    private static String carRatingEndpoint = System.getenv("RATING_ENDPOINT");
    
    private static Tracing tracing = TracingHelper.getTracing(TracingHelper.RATING_NAME);

    private final static Logger logger = Logger.getLogger(CarRating.class.getName());
    
    public static Rating getCarRating(int carID) {
        Rating rating = new Rating();
        Car car = new CarDataLoader().getCar(carID);
        Manufacturer manufacturer = car.getManufacturer();
        logger.log(Level.FINE, "Getting rating for carID: {0} manufacturerID: {1}", new Object[]{carID, manufacturer.getManufacturerId()});
        RatingRequest ratingRequest = new RatingRequest(manufacturer.getName(), car.getModel());
        try {
            Preference preference = PreferenceManager.getPreference("REST_CLIENT");
            switch (preference.getValue()) {
                case "Jersey_Sync":
                    rating = getCarRatingSync(ratingRequest);
                    break;
                case "Jersey_Async":
                    rating =  getCarRatingAsync(ratingRequest).get();
                    break;
            }
            logger.log(Level.FINE, "Success getting rating for carID: {0}", carID);
        } catch (InterruptedException | PreferenceException | ExecutionException ex) {
            logger.log(Level.SEVERE, "Error getting rating for carID: " + carID + " manufacturerID: " + manufacturer.getManufacturerId(), ex);
            return null;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error getting rating for carID: " + carID + " manufacturerID: " + manufacturer.getManufacturerId(), ex);
            return null;
        }

        return rating;
    }

    private static Rating getCarRatingSync(RatingRequest ratingRequest) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(carRatingEndpoint);
        target.register(TracingClientFilter.create(tracing));
        return target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(ratingRequest, MediaType.APPLICATION_JSON), Rating.class);
    }

    private static Future<Rating> getCarRatingAsync(RatingRequest ratingRequest) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(carRatingEndpoint);
        Future<Rating> response = target.request(MediaType.APPLICATION_JSON).async()
                .post(Entity.entity(ratingRequest, MediaType.APPLICATION_JSON), Rating.class);
        return response;
    }
}
