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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.supercars.Enquiry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EnquiryDataLoader {

    Statement statement = null;
    ResultSet resultSet = null;

    private final static Logger logger = Logger.getLogger(EnquiryDataLoader.class.getName());

    public Enquiry getEnquiry(int enquiryId) {

        Enquiry enquiry = new Enquiry();
        try ( Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT ENQUIRY_ID, NAME, EMAIL, COMMENT, CAR_ID FROM ENQUIRIES WHERE ENQUIRY_ID = " + enquiryId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                enquiry.setEnquiryId(resultSet.getInt("ENQUIRY_ID"));
                enquiry.setName(resultSet.getString("NAME"));
                enquiry.setEmail(resultSet.getString("EMAIL"));
                enquiry.setComment(resultSet.getString("COMMENT"));
                enquiry.setCarId(resultSet.getInt("carId"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return enquiry;
    }

    public List<Enquiry> getEnquirysForCar(int carId) {

        List<Enquiry> enquiries = new ArrayList<Enquiry>();
        try ( Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT NAME, EMAIL, COMMENT FROM ENQUIRIES WHERE CAR_ID = " + carId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Enquiry enquiry = new Enquiry();
                enquiry.setName(resultSet.getString("NAME"));
                enquiry.setEmail(resultSet.getString("EMAIL"));
                enquiry.setComment(resultSet.getString("COMMENT"));
                enquiries.add(enquiry);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return enquiries;
    }

    public void saveEnquiry(Enquiry enquiry) {
        try ( Connection connection = Constants.getDBConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ENQUIRIES (NAME, EMAIL, COMMENT, CAR_ID, DUMMY) SELECT ?,?,?,?, SLEEP(1)");
            pstmt.setString(1, enquiry.getName());
            pstmt.setString(2, enquiry.getEmail());
            pstmt.setString(3, enquiry.getComment());
            pstmt.setInt(4, enquiry.getCarId());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
