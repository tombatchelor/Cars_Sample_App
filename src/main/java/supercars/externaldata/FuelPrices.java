/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercars.externaldata;

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
    
    public static FuelPrices getFuelPrices() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.fueleconomy.gov/ws/rest/fuelprices");
        return target.request(MediaType.APPLICATION_XML)
                .get(FuelPrices.class);
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
