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
import com.supercars.Quote;
import com.supercars.QuoteRequest;
import com.supercars.dataloader.CarDataLoader;
import com.supercars.logging.Logger;
import com.supercars.preferences.Preference;
import com.supercars.preferences.PreferenceException;
import com.supercars.preferences.PreferenceManager;
import com.supercars.tracing.TracingHelper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tombatchelor
 */
public class InsuranceQuotes {

    static Tracing tracing = TracingHelper.getTracing(TracingHelper.INSURANCE_NAME);

    public static Quote getQuote(int carID) {
        Car car = new CarDataLoader().getCar(carID);
        Manufacturer manufacturer = car.getManufacturer();
        QuoteRequest quoteRequest = new QuoteRequest(car.getPrice(), car.getYear(), manufacturer.getName(), 500, 100000);
        Quote quote = null;
        try {
            Preference preference = PreferenceManager.getPreference("REST_CLIENT");
            switch (preference.getValue()) {
                case "Jersey_Sync":
                    quote = getQuoteJerseySync(quoteRequest);
                case "Jersey_Async":
                    quote = getQuoteJerseysAsync(quoteRequest).get();
            }
        } catch (PreferenceException | InterruptedException | ExecutionException ex) {
            Logger.log(ex);
        }

        return quote;
    }

    private static Quote getQuoteJerseySync(QuoteRequest quoteRequest) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8000/insurance/simpleQuote");
        target.register(TracingClientFilter.create(tracing));
        return target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(quoteRequest, MediaType.APPLICATION_JSON), Quote.class);
    }

    private static Future<Quote> getQuoteJerseysAsync(QuoteRequest quoteRequest) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8000/insurance/simpleQuote");
        Future<Quote> response = target.request(MediaType.APPLICATION_JSON).async()
                .post(Entity.entity(quoteRequest, MediaType.APPLICATION_JSON), Quote.class);
        return response;
    }
}
