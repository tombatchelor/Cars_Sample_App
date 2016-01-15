/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.dataloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import supercars.Car;
import supercars.Engine;
import supercars.XMLException;
import supercars.form.CarForm;
import supercars.logging.Logger;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CarDataLoader {

    Statement statement = null;
    ResultSet resultSet = null;

    public void saveCar(CarForm carForm) {
        try (Connection connection = Constants.getDBConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CARS(NAME, MODEL, DESCRIPTION, MANUFACTURER_ID, COLOUR, YEAR, PRICE, SUMMARY, PHOTO) SELECT ?, ?, ?, ?, ?, ?, ?, ?, 0");
            pstmt.setString(1, carForm.getName());
            pstmt.setString(2, carForm.getModel());
            pstmt.setString(3, carForm.getDescription());
            pstmt.setInt(4, carForm.getManufacturer());
            pstmt.setString(5, carForm.getColour());
            pstmt.setInt(6, carForm.getYear());
            pstmt.setFloat(7, carForm.getPrice());
            pstmt.setString(8, carForm.getSummary());
            pstmt.execute();
            pstmt.close();
            connection.close();
            throw new XMLException("XML Example Exception Thrown");
        } catch (SQLException | XMLException e) {
            Logger.log(e);
        }
    }

    public Car getCar(int carId) throws SQLException {

        Car car = new Car();
        Engine engine = new Engine();
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
            car.setManufacturer(resultSet.getString("MANUFACTURER_ID"));
            car.setColour(resultSet.getString("COLOUR"));
            car.setYear(resultSet.getInt("YEAR"));
            car.setPrice(resultSet.getInt("PRICE"));
            car.setPhoto(resultSet.getString("PHOTO"));

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            Logger.log(e);
        }

        return car;
    }

    public Collection getCarsSummary() {

        List cars = new ArrayList();
        Car car = null;
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT NAME, MODEL, SUMMARY, DESCRIPTION, PHOTO FROM CARS";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
            while (resultSet.next()) {
                car = new Car();
                car.setName(resultSet.getString("NAME"));
                car.setModel(resultSet.getString("MODEL"));
                car.setSummary(resultSet.getString("SUMMARY"));
                car.setDescription(resultSet.getString("DESCRIPTION"));
                car.setPhoto(resultSet.getString("PHOTO"));
                cars.add(car);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            Logger.log(e);
        }
        return cars;
    }

    public Collection getCarsByManufacturer(String manufacturerId) {

        List cars = new ArrayList();
        Car car = null;
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
        } catch (Exception e) {
            Logger.log(e);
        }
        return cars;
    }

    public Collection getCarsBySearch(String query) {

        List cars = new ArrayList();
        Car car = null;
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
                car.setManufacturer(resultSet.getString("MANUFACTURER_ID"));
                cars.add(car);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            Logger.log(e);
        }

        return cars;
    }

}
