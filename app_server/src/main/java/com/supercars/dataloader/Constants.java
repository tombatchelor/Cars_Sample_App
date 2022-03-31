/*
 * Constants.java
 *
 * Created on 31 July 2007, 09:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.supercars.dataloader;

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
    
    private final static Logger logger = Logger.getLogger(Constants.class.getName());

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
            logger.log(Level.SEVERE, "Exception getting connection from the pool", ex);
        }
        
        return null;
    }
    
    public static Connection getDBConnection() {
        return getDBConnectionStandardPool();
    }
}
