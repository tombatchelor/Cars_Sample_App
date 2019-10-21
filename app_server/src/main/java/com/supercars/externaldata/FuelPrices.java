/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.externaldata;

import brave.Tracing;
import brave.jaxrs2.TracingClientFilter;
import com.supercars.logging.CarLogger;
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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom.batchelor
 */
@XmlRootElement
public class FuelPrices {

    private double cng;
    private double diesel;
    private double e85;
    private double electric;
    private double lpg;
    private double midgrade;
    private double premium;
    private double regular;

    static FuelPrices prices = null;
    static long lastUpdate = 0l;
    static int timeout = 0;

    static Tracing tracing = TracingHelper.getTracing(TracingHelper.FUEL_PRICES_NAME);

    private final static Logger logger = Logger.getLogger(FuelPrices.class.getName());
    
    static {
        CarLogger.setup(FuelPrices.class.getName());
    }
    
    public static FuelPrices getFuelPrices() {
        try {
            if (timeout == 0) {
                timeout = Integer.parseInt(PreferenceManager.getPreference("FUEL_CACHE_TIMEOUT").getValue());
                timeout = timeout * 60 * 1000;
            }
            if (prices == null || lastUpdate + timeout > System.currentTimeMillis()) {
                Preference preference = PreferenceManager.getPreference("REST_CLIENT");
                switch (preference.getValue()) {
                    case "Jersey_Sync":
                        prices = getFuelPricesJerseySync();
                    case "Jersey_Async":
                        prices = getFuelPriceJerseysAsync().get();
                }
            }
        } catch (PreferenceException | InterruptedException | ExecutionException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return prices;
    }

    private static FuelPrices getFuelPricesJerseySync() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.fueleconomy.gov/ws/rest/fuelprices");
        target.register(TracingClientFilter.create(tracing));
        return target.request(MediaType.APPLICATION_XML)
                .get(FuelPrices.class);
    }

    private static Future<FuelPrices> getFuelPriceJerseysAsync() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.fueleconomy.gov/ws/rest/fuelprices");
        Future<FuelPrices> response = target.request(MediaType.APPLICATION_XML).async().get(FuelPrices.class);
        return response;
    }

    /**
     * @return the cng
     */
    public double getCng() {
        return cng;
    }

    /**
     * @param cng the cng to set
     */
    public void setCng(double cng) {
        this.cng = cng;
    }

    /**
     * @return the diesel
     */
    public double getDiesel() {
        return diesel;
    }

    /**
     * @param diesel the diesel to set
     */
    public void setDiesel(double diesel) {
        this.diesel = diesel;
    }

    /**
     * @return the e85
     */
    public double getE85() {
        return e85;
    }

    /**
     * @param e85 the e85 to set
     */
    public void setE85(double e85) {
        this.e85 = e85;
    }

    /**
     * @return the electric
     */
    public double getElectric() {
        return electric;
    }

    /**
     * @param electric the electric to set
     */
    public void setElectric(double electric) {
        this.electric = electric;
    }

    /**
     * @return the lpg
     */
    public double getLpg() {
        return lpg;
    }

    /**
     * @param lpg the lpg to set
     */
    public void setLpg(double lpg) {
        this.lpg = lpg;
    }

    /**
     * @return the midgrade
     */
    public double getMidgrade() {
        return midgrade;
    }

    /**
     * @param midgrade the midgrade to set
     */
    public void setMidgrade(double midgrade) {
        this.midgrade = midgrade;
    }

    /**
     * @return the premium
     */
    public double getPremium() {
        return premium;
    }

    /**
     * @param premium the premium to set
     */
    public void setPremium(double premium) {
        this.premium = premium;
    }

    /**
     * @return the regular
     */
    public double getRegular() {
        return regular;
    }

    /**
     * @param regular the regular to set
     */
    public void setRegular(double regular) {
        this.regular = regular;
    }
}
