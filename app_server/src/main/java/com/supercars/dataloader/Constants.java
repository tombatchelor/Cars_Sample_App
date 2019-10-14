/*
 * Constants.java
 *
 * Created on 31 July 2007, 09:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.supercars.dataloader;

import com.supercars.logging.Logger;
import com.supercars.preferences.Preference;
import com.supercars.preferences.PreferenceException;
import com.supercars.preferences.PreferenceManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                case 3:
                    upgradeToSchema_4();
                case 4:
                    upgradeToSchema_5();
                default:
            }
        } catch (SQLException | PreferenceException ex) {
            Logger.log(ex);
        }
    }

    /**
     * Creates a new instance of Constants
     */
    public Constants() {
    }

    public static Connection getDBConnectionStandardPool() {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup("jdbc/standard");
            Connection connection = ds.getConnection();
            return connection;
        } catch (NamingException | SQLException ex) {
            Logger.log(ex);
        }

        return null;
    }

    public static Connection getDBConnection() {
        try {
            String jndiName = PreferenceManager.getPreference("CONNECTION_POOL").getValue();
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup(jndiName);
            Connection dbCon = ds.getConnection();
            return dbCon;
        } catch (NamingException | SQLException | PreferenceException ex) {
            Logger.log(ex);
        }

        return null;
    }

    private static boolean checkPropertiesTableExist() throws SQLException {
        boolean exists = false;
        try (Connection connection = getDBConnectionStandardPool(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'supercars' AND table_name = 'PREFERENCES'")) {
            exists = resultSet.next();
        } catch (NullPointerException ex) {
            Logger.log(ex);
            throw new SQLException("NullPointerException when getting DB connection");
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
            Logger.log(ex);
        }
    }

    private static boolean upgradeToSchema_2() throws SQLException {
        try (Connection connection = getDBConnectionStandardPool(); Statement statement = connection.createStatement()) {
            statement.execute(PREFERENCES_TABLE);
            updateSchemaVersion(2);
        }
        return true;
    }

    private static void upgradeToSchema_3() throws PreferenceException, SQLException {
        PreferenceManager.updatePreference(new Preference("REST_CLIENT", "Jersey_Sync", "Client to call fueleconomy.gov, either 'Jersey_Sync' or 'Jersey_Async'", false));
        updateSchemaVersion(3);
    }

    private static void upgradeToSchema_4() throws PreferenceException, SQLException {
        PreferenceManager.updatePreference(new Preference("CONNECTION_POOL", "jdbc/standard", "Connection pool to use, either 'jdbc/standard' or 'jdbc/c3p0'", false));
        updateSchemaVersion(4);
    }
    
    private static void upgradeToSchema_5() throws PreferenceException, SQLException {
        PreferenceManager.updatePreference(new Preference("FUEL_CACHE_TIMEOUT", "30", "Cache timeout for external fuel prices in minutes", false));
        updateSchemaVersion(5);
    }
}
