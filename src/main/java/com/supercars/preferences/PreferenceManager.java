/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.preferences;

import static com.supercars.dataloader.Constants.getDBConnection;
import com.supercars.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 *
 * @author tom.batchelor
 */
public class PreferenceManager {

    public static String getPreference(String name) throws PreferenceException {
        String value = "";
        try {
            try (Connection connection = getDBConnection(); PreparedStatement statement = connection.prepareStatement("SELECT VALUE FROM PREFERENCES WHERE NAME=?")) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        value = resultSet.getString(1);
                    } else {
                        throw new PreferenceException("No preference found for: " + name);
                    }
                }
            }
            
            return value;
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error getting preference: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }
    }

    public static boolean doesPreferenceExist(String name) throws PreferenceException {
        try {
            try (Connection connection = getDBConnection()) {
                boolean exists;
                try (PreparedStatement statement = connection.prepareStatement("SELECT VALUE FROM PREFERENCES WHERE NAME=?")) {
                    statement.setString(1, name);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        exists = resultSet.next();
                    }
                }
                return exists;
            }
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error getting preference: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }
    }

    public void updatePreference(String name, String value) throws PreferenceException {
        try {
            try (Connection connection = getDBConnection()) {
                if (doesPreferenceExist(name)) {
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE PREFERENCES SET VALUE = ? WHERE NAME = ?")) {
                        statement.setString(1, name);
                        statement.setString(2, value);
                        statement.execute();
                    }
                } else {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO PREFERENCES (NAME, VALUE) SELECT ?, ?")) {
                        statement.setString(1, name);
                        statement.setString(2, value);
                        statement.execute();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error getting preference: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }
    }
}
