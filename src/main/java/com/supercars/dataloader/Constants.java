/*
 * Constants.java
 *
 * Created on 31 July 2007, 09:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.supercars.dataloader;

import com.supercars.preferences.Preference;
import com.supercars.preferences.PreferenceException;
import com.supercars.preferences.PreferenceManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Thomas_Batchelor
 */
public class Constants {

    private static final String PREFERENCES_TABLE = "CREATE TABLE PREFERENCES (\n"
            + " PREFERENCE_ID MEDIUMINT NOT NULL AUTO_INCREMENT,\n"
            + " NAME VARCHAR(30),"
            + " VALUE VARCHAR(50),"
            + " DESCRIPTION VARCHAR(100), "
            + " HIDDEN INT(1), "
            + " PRIMARY KEY (PREFERENCE_ID) "
            + ");";

    static {
        try {
            int schemaVersion = getSchemaVersion();
            switch (schemaVersion) {
                case 1:
                    upgradeToSchema_2();
                case 2:
                    upgradeToSchema_3();
                default:
            }
        } catch (SQLException | PreferenceException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new instance of Constants
     */
    public Constants() {
    }

    public static Connection getDBConnection() {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup("jdbc/supercars");
            Connection connection = ds.getConnection();
            return connection;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static boolean checkPropertiesTableExist() throws SQLException {
        boolean exists = false;
        try (Connection connection = getDBConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'supercars' AND table_name = 'PREFERENCES'")) {
            exists = resultSet.next();
        }
        return exists;
    }

    private static int getSchemaVersion() throws SQLException, PreferenceException {
        int version = 1;
        if (checkPropertiesTableExist()) {
            return Integer.parseInt(PreferenceManager.getPreference("SCHEMA_VERSION").getValue());
        }
        return version;
    }

    private static void updateSchemaVersion(int version) throws SQLException {
        try {
            PreferenceManager.updatePreference("SCHEMA_VERSION", String.valueOf(version), "Schema Version", true);
        } catch (PreferenceException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean upgradeToSchema_2() throws SQLException {
        try (Connection connection = getDBConnection(); Statement statement = connection.createStatement()) {
            statement.execute(PREFERENCES_TABLE);
            updateSchemaVersion(2);
        }
        return true;
    }

    private static void upgradeToSchema_3() throws PreferenceException, SQLException {
        PreferenceManager.updatePreference(new Preference("REST_CLIENT", "Jersey_Sync", "Client to call fueleconomy.gov, either 'Jersey_Sync' or 'Jersey_Async'", false));
        updateSchemaVersion(3);
    }
}
