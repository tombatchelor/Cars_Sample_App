/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.supercars;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Car {

    private int carId;
    private String name;
    private String model;
    private int manufacturerId;
    private String colour;
    private int year;
    private int price;
    private String summary;
    private String description;
    private int wheelSize;
    private int tyreSize;
    private boolean isManual;
    private String photo;
    private Manufacturer manufacturer;
    private int rating;

    public String getPhoto() {
        // do this as a hack, there is only 1 car photo per manufacturer DEMO-18
        return Integer.toString(getManufacturerId());
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean isManual) {
        this.isManual = isManual;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTyreSize() {
        return tyreSize;
    }

    public void setTyreSize(int tyreSize) {
        this.tyreSize = tyreSize;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * @return the manufacturer
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
        /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("carID: ")
                .append(carId)
                .append(" manufacturerID: ")
                .append(manufacturerId)
                .append(" Name: ")
                .append(name)
                .append(" Model: ")
                .append(model)
                .append(" Price: ")
                .append(price)
                .append(" Colour: ")
                .append(colour)
                .append(" Rating: ")
                .append(rating);
        return sb.toString();
    }
}
