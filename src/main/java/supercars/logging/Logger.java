/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercars.logging;

import java.io.PrintStream;

/**
 *
 * @author tom.batchelor
 */
public class Logger {
    
    private static PrintStream outputLocation = System.err;
       
    public static void setOutputLocation(PrintStream ps) {
        outputLocation = ps;
    }
    
    public static void log(Exception e) {
        e.printStackTrace(outputLocation);
    }
    
    public static void log(String s, LogLevel level) {
        outputLocation.println(s);
    }
}
