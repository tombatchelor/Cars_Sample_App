/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom.batchelor
 */
public class CarLogger {
    
    public static void setup(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(Level.FINEST);
    }
}
