package com.supercars.dataloader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.supercars.usermanagement.User;

/**
 * @author tombatchelor
 *
 */
public class UserDataLoader {

  private final static Logger logger = Logger.getLogger(UserDataLoader.class.getName());

  public User getUser(User user) {
    // SELECT USERS.FIRST_NAME, USERS.LAST_NAME, USERS.COUNTRY, COMPANIES.NAME, COMPANIES.TIER, COMPANIES.ACV, COMPANIES.OWNER, COMPANIES.TECH_OWNER FROM USERS LEFT JOIN COMPANIES ON USERS.COMPANY_NAME = COMPANIES.NAME WHERE USERS.EMAIL = 'alison.peterson@thetake.com';
    try (Connection connection = Constants.getDBConnection()) {
      String sql = "SELECT USERS.FIRST_NAME, USERS.LAST_NAME, USERS.COUNTRY, COMPANIES.COMPANY_ID, COMPANIES.NAME, COMPANIES.TIER, COMPANIES.ACV, COMPANIES.OWNER, COMPANIES.TECH_OWNER";
      sql += " FROM USERS LEFT JOIN COMPANIES ON USERS.COMPANY_NAME = COMPANIES.NAME";
      sql += " WHERE USERS.EMAIL = '" + user.getUsername() + "'";

      try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
        if (resultSet.next()) {
          // Populate User Object
          user.setFirstName(resultSet.getString("FIRST_NAME"));
          user.setLastName(resultSet.getString("LAST_NAME"));
          user.setLocation(resultSet.getString("COUNTRY"));
          user.setCompanyId(resultSet.getInt("COMPANY_ID"));
          user.setCompanyName(resultSet.getString("NAME"));
          user.setTier(resultSet.getString("TIER"));
          user.setAcv(resultSet.getString("ACV"));
          user.setAccountOwner(resultSet.getString("OWNER"));
          user.setTechOwner(resultSet.getString("TECH_OWNER"));
        }

      }
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, "SQLException getting user: " + user.getUsername(), ex);
    }

    return user;
  }
}