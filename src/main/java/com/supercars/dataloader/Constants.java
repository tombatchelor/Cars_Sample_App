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
import java.sql.SQLException;
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
    
    
    /** Creates a new instance of Constants */
    public Constants() {
    }
    
    public static Connection getDBConnection() {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup("jdbc/supercars");
            Connection dbCon = ds.getConnection();
            return dbCon;
        } catch (NamingException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
