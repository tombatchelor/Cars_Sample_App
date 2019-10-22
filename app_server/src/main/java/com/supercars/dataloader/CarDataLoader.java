/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.supercars.dataloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.supercars.Car;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CarDataLoader {

    Statement statement = null;
    ResultSet resultSet = null;
    
    private final static Logger logger = Logger.getLogger(CarDataLoader.class.getName());
    
    public int saveCar(Car car) {
        int carId = -1;
        try (Connection connection = Constants.getDBConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CARS(NAME, MODEL, DESCRIPTION, MANUFACTURER_ID, COLOUR, YEAR, PRICE, SUMMARY, PHOTO) SELECT ?, ?, ?, ?, ?, ?, ?, ?, 0", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, car.getName());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getDescription());
            pstmt.setInt(4, car.getManufacturerId());
            pstmt.setString(5, car.getColour());
            pstmt.setInt(6, car.getYear());
            pstmt.setFloat(7, car.getPrice());
            pstmt.setString(8, car.getSummary());
            
            pstmt.execute();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                carId = generatedKeys.getInt(1);
            }
            pstmt.close();
            connection.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return carId;
    }

    public Car getCar(int carId) {

        Car car = new Car();
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT CARS.CAR_ID, NAME, MODEL, SUMMARY, DESCRIPTION, MANUFACTURER_ID, COLOUR, YEAR, PRICE, PHOTO";
            sql += " FROM CARS WHERE CARS.CAR_ID = " + carId;

            //connection = Constants.getDBConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            // Create Car Object
            car.setCarId(resultSet.getInt("CAR_ID"));
            car.setName(resultSet.getString("NAME"));
            car.setModel(resultSet.getString("MODEL"));
            car.setSummary(resultSet.getString("SUMMARY"));
            car.setDescription(resultSet.getString("DESCRIPTION"));
            car.setManufacturerId(Integer.parseInt(resultSet.getString("MANUFACTURER_ID")));
            car.setColour(resultSet.getString("COLOUR"));
            car.setYear(resultSet.getInt("YEAR"));
            car.setPrice(resultSet.getInt("PRICE"));
            car.setPhoto(resultSet.getString("PHOTO"));
            car.setManufacturer(new ManufacturerDataLoader().getManufacturer(car.getManufacturerId()));
            
            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return car;
    }

    public List<Car> getCarsByManufacturer(int manufacturerId) {

        List cars = new ArrayList();
        Car car;
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT CAR_ID, NAME, MODEL, SUMMARY, DESCRIPTION, PRICE, PHOTO FROM CARS WHERE MANUFACTURER_ID = " + manufacturerId;
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                car = new Car();
                car.setCarId(resultSet.getInt("CAR_ID"));
                car.setName(resultSet.getString("NAME"));
                car.setModel(resultSet.getString("MODEL"));
                car.setSummary(resultSet.getString("SUMMARY"));
                car.setDescription(resultSet.getString("DESCRIPTION"));
                car.setPrice(resultSet.getInt("PRICE"));
                car.setPhoto(resultSet.getString("PHOTO"));
                cars.add(car);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return cars;
    }

    public List<Car> getCarsBySearch(String query) {

        List cars = new ArrayList();
        Car car;
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT CAR_ID, C.NAME, MODEL, SUMMARY, DESCRIPTION, PRICE, PHOTO, M.MANUFACTURER_ID FROM CARS C, MANUFACTURER M WHERE C.MANUFACTURER_ID = M.MANUFACTURER_ID AND (C.NAME LIKE '%" + query + "%' OR C.MODEL LIKE '%" + query + "%' OR M.NAME LIKE '%" + query + "%')";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                car = new Car();
                car.setCarId(resultSet.getInt("CAR_ID"));
                car.setName(resultSet.getString("NAME"));
                car.setModel(resultSet.getString("MODEL"));
                car.setSummary(resultSet.getString("SUMMARY"));
                car.setDescription(resultSet.getString("DESCRIPTION"));
                car.setPrice(resultSet.getInt("PRICE"));
                car.setPhoto(resultSet.getString("PHOTO"));
                car.setManufacturerId(Integer.parseInt(resultSet.getString("MANUFACTURER_ID")));
                car.setManufacturer(new ManufacturerDataLoader().getManufacturer(car.getManufacturerId()));
                cars.add(car);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return cars;
    }
}
