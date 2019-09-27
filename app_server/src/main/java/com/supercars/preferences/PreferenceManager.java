/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.preferences;

import static com.supercars.dataloader.Constants.getDBConnectionStandardPool;
import com.supercars.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tom.batchelor
 */
public class PreferenceManager {

    public static Preference getPreference(String name) throws PreferenceException {
        Preference preference = new Preference(name);
        try {
            try (Connection connection = getDBConnectionStandardPool(); PreparedStatement statement = connection.prepareStatement("SELECT VALUE, DESCRIPTION, HIDDEN FROM PREFERENCES WHERE NAME=?")) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        preference.setValue(resultSet.getString(1));
                        preference.setDescription(resultSet.getString(2));
                        preference.setHidden((resultSet.getInt(3) != 0));
                    } else {
                        throw new PreferenceException("No preference found for: " + name);
                    }
                }
            }

            return preference;
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error getting preference: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }
    }

    public static boolean doesPreferenceExist(String name) throws PreferenceException {
        try {
            try (Connection connection = getDBConnectionStandardPool()) {
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

    public static void updatePreference(String name, String value) throws PreferenceException {
        updatePreference(name, value, null);
    }

    public static void updatePreference(String name, String value, String description) throws PreferenceException {
        updatePreference(name, value, description, false);
    }

    public static void updatePreference(String name, String value, String description, boolean hidden) throws PreferenceException {
        updatePreference(new Preference(name, value, description, hidden));
    }

    public static void updatePreference(Preference preference) throws PreferenceException {
        int hidden = 0;
        if (preference.isHidden()) {
            hidden = 1;
        }
        try {
            try (Connection connection = getDBConnectionStandardPool()) {
                if (doesPreferenceExist(preference.getName())) {
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE PREFERENCES SET VALUE = ?, DESCRIPTION = ?, HIDDEN = ? WHERE NAME = ?")) {
                        statement.setString(1, preference.getValue());
                        statement.setString(2, preference.getDescription());
                        statement.setInt(3, hidden);
                        statement.setString(4, preference.getName());
                        statement.execute();
                    }
                } else {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO PREFERENCES (NAME, VALUE, DESCRIPTION, HIDDEN) SELECT ?, ?, ?, ?")) {
                        statement.setString(1, preference.getName());
                        statement.setString(2, preference.getValue());
                        statement.setString(3, preference.getDescription());
                        statement.setInt(4, hidden);
                        statement.execute();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error setting preference: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }
    }

    public static List<Preference> getAllPreferences(boolean includeHidden) throws PreferenceException {
        List<Preference> preferences = new LinkedList<>();
        try (Connection connection = getDBConnectionStandardPool(); PreparedStatement statement = connection.prepareStatement("SELECT NAME, VALUE, DESCRIPTION, HIDDEN FROM PREFERENCES")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Preference preference = new Preference();
                preference.setName(resultSet.getString(1));
                preference.setValue(resultSet.getString(2));
                preference.setDescription(resultSet.getString(3));
                preference.setHidden((resultSet.getInt(4) > 0));
                if (includeHidden || !preference.isHidden()) {
                    preferences.add(preference);
                }
            }
        } catch (SQLException ex) {
            Logger.log(ex);
            PreferenceException pe = new PreferenceException("DB error getting all preferences: " + ex.getMessage());
            pe.addSuppressed(ex);
            throw pe;
        }

        return preferences;
    }

    public static void setPreferences(List<Preference> preferences) throws PreferenceException {
        for (Preference preference : preferences) {
            updatePreference(preference);
        }
    }
}
