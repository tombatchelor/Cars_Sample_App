package com.supercars.dataloader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.supercars.tracing.TracingHelper;
import com.supercars.usermanagement.User;
import com.supercars.logging.SessionIDHolder;

/**
 * @author tombatchelor
 *
 */
public class UserDataLoader {

  private final static Logger logger = Logger.getLogger(UserDataLoader.class.getName());

  public User getUser(User user) {
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

    //TODO: Find a more elegant way of doing this
    StringBuilder sb = new StringBuilder();
    sb.append("{\"spanID\":\"");
    sb.append(TracingHelper.getSpanID());
    sb.append("\",\"traceID\":\"0000000000000000");
    sb.append(TracingHelper.getTraceID());
    sb.append("\",\"@timestamp\":");
    sb.append(System.currentTimeMillis() * 1000);
    sb.append(",\"method\":\"getUser\",\"level\":\"DEBUG\",\"logger_name\":\"com.supercars.dataloader.UserDataLoader\",\"sessionID\":\"");
    sb.append(SessionIDHolder.getSessionID());
    sb.append("\",\"message\":");
    sb.append(user.toJSON());
    sb.append(",\"class\":\"com.supercars.dataloader.UserDataLoader\"}");
    System.out.println(sb);
    return user;
  }
}