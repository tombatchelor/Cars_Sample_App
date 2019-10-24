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
import com.supercars.InsuranceQuote;
import com.supercars.InsuranceQuoteRequest;
import com.supercars.dataloader.CarDataLoader;
import com.supercars.preferences.Preference;
import com.supercars.preferences.PreferenceException;
import com.supercars.preferences.PreferenceManager;
import com.supercars.tracing.TracingHelper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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
public class InsuranceQuotes {

    static Tracing tracing = TracingHelper.getTracing(TracingHelper.INSURANCE_NAME);

    private final static Logger logger = Logger.getLogger(InsuranceQuotes.class.getName());
    
    public static InsuranceQuote getQuote(int carID) {
        logger.log(Level.FINE, "Getting insurance quote for carID: {0}", carID);
        Car car = new CarDataLoader().getCar(carID);
        Manufacturer manufacturer = car.getManufacturer();
        InsuranceQuoteRequest quoteRequest = new InsuranceQuoteRequest(car.getPrice(), car.getYear(), manufacturer.getName(), 500, 100000);
        logger.log(Level.FINE, "Sending quote request: {0}", quoteRequest.toString());
        InsuranceQuote quote = null;
        try {
            Preference preference = PreferenceManager.getPreference("REST_CLIENT");
            switch (preference.getValue()) {
                case "Jersey_Sync":
                    quote = getQuoteJerseySync(quoteRequest);
                    break;
                case "Jersey_Async":
                    quote = getQuoteJerseysAsync(quoteRequest).get();
                    break;
            }
            logger.log(Level.FINE, "Success getting insurance quote for carID: {0}", carID);
            logger.log(Level.FINE, "Quote price of ${0} from: {1}", new Object[]{quote.getPrice(), quote.getCompany()});
        } catch (PreferenceException | InterruptedException | ExecutionException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return quote;
    }

    private static InsuranceQuote getQuoteJerseySync(InsuranceQuoteRequest quoteRequest) {
        logger.fine("Using sync HTTP call");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://insurance:8000/insurance/simpleQuote");
        target.register(TracingClientFilter.create(tracing));
        return target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(quoteRequest, MediaType.APPLICATION_JSON), InsuranceQuote.class);
    }

    private static Future<InsuranceQuote> getQuoteJerseysAsync(InsuranceQuoteRequest quoteRequest) {
        logger.fine("Using async HTTP call");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://insurance:8000/insurance/simpleQuote");
        Future<InsuranceQuote> response = target.request(MediaType.APPLICATION_JSON).async()
                .post(Entity.entity(quoteRequest, MediaType.APPLICATION_JSON), InsuranceQuote.class);
        return response;
    }
}
