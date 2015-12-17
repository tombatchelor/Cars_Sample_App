/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import supercars.form.EnquireForm;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnquiryDataLoader {
    
    Statement statement = null;
    ResultSet resultSet = null;
    
    public EnquireForm getEnquiry(int enquiryId) {
        
        EnquireForm enquireForm = new EnquireForm();
        try {
            Connection connection = Constants.getDBConnection();
            String sql = "SELECT ENQUIRY_ID, NAME, EMAIL, COMMENT, CAR_ID FROM ENQUIRIES WHERE ENQUIRY_ID = "+enquiryId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            enquireForm.setEnquireFormId(resultSet.getLong("ENQUIRY_ID"));
            enquireForm.setName(resultSet.getString("NAME"));
            enquireForm.setEmail(resultSet.getString("EMAIL"));
            enquireForm.setComment(resultSet.getString("COMMENT"));
            enquireForm.setCarId(resultSet.getInt("carId"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return enquireForm;
    }
    
    public Collection getEnquirysForCar(int carId) {
        
        Collection enquiries = new ArrayList();
        try {
            Connection connection = Constants.getDBConnection();
            String sql = "SELECT NAME, EMAIL, COMMENT FROM ENQUIRIES WHERE CAR_ID = "+carId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                EnquireForm enquireForm = new EnquireForm();
                enquireForm.setName(resultSet.getString("NAME"));
                enquireForm.setEmail(resultSet.getString("EMAIL"));
                enquireForm.setComment(resultSet.getString("COMMENT"));
                enquiries.add(enquireForm);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return enquiries;
    }
    
    public void saveEnquireForm(EnquireForm enquireForm){
        try {
            Connection connection = Constants.getDBConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ENQUIRIES (NAME, EMAIL, COMMENT, CAR_ID) VALUES(?,?,?,?)");
            pstmt.setString(1, enquireForm.getName());
            pstmt.setString(2, enquireForm.getEmail());
            pstmt.setString(3, enquireForm.getComment());
            pstmt.setInt(4, enquireForm.getCarId());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
