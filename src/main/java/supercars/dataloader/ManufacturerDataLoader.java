/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import supercars.Manufacturer;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManufacturerDataLoader {
    
    Statement statement = null;
    ResultSet resultSet = null;
    
    public Collection getManufacturers() {
        
        Manufacturer manufacturer = null;
        List manufacturers = new ArrayList();
        
        try {
            String sql = "SELECT MANUFACTURER_ID, NAME, WEB, EMAIL, LOGO FROM MANUFACTURER ORDER BY NAME";
            Connection connection = Constants.getDBConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setManufacturerId(resultSet.getLong("MANUFACTURER_ID"));
                manufacturer.setName(resultSet.getString("NAME"));
                manufacturer.setWeb(resultSet.getString("WEB"));
                manufacturer.setEmail(resultSet.getString("EMAIL"));
                manufacturer.setLogo(resultSet.getString("LOGO"));
                manufacturers.add(manufacturer);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
        
        return manufacturers;
    }
    
    public Manufacturer getManufacturer(String manufacturerId) {
        
        Manufacturer manufacturer = null;
        
        try {
            String sql = "SELECT MANUFACTURER_ID, NAME, WEB, EMAIL, LOGO FROM MANUFACTURER WHERE MANUFACTURER_ID = "+manufacturerId;
            Connection connection = Constants.getDBConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setManufacturerId(resultSet.getLong("MANUFACTURER_ID"));
                manufacturer.setName(resultSet.getString("NAME"));
                manufacturer.setWeb(resultSet.getString("WEB"));
                manufacturer.setEmail(resultSet.getString("EMAIL"));
                manufacturer.setLogo(resultSet.getString("LOGO"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
        
        return manufacturer;
    }
    
}
