/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

import com.supercars.rest.HealthService;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/**
 *
 * @author tom.batchelor
 */
public class Leak {

    private static List<int[]> leakyCollection = new LinkedList<>();
    private static long keepAliveTime = 0;

    private final static Logger logger = Logger.getLogger(Leak.class.getName());

    public static void addToCollection(int number, int size) {
        checkShouldKill();
        for (int i = 0; i < number; i++) {
            leakyCollection.add(new int[size]);
        }
    }

    public static long getSize() {
        long size = 0L;
        try {
            for (int[] ints : leakyCollection) {
                size += ints.length;
            }
        } catch (ConcurrentModificationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return size;
    }

    public static void drainCollection() {
        leakyCollection = new LinkedList<>();
        System.gc();
    }
    
    private static void checkShouldKill() {
        if (keepAliveTime == 0) {
            Random random = new Random();
            keepAliveTime = random.nextInt(5)+20;
            keepAliveTime *= 60;
            keepAliveTime *= 1000;
            keepAliveTime += System.currentTimeMillis();
            logger.log(Level.FINE, "Setting JVM kill time to: {0} current time is: {1}", new Object[]{keepAliveTime, System.currentTimeMillis()});
        }
        
        if (keepAliveTime < System.currentTimeMillis()) {
            HealthService.setAsUnhealthy();
        }
    }
    
    public static boolean shouldLeak() {
        String shouldLeak = System.getenv("LEAKING");
        if (shouldLeak != null && shouldLeak.equals("TRUE")) {
            return true;
        }
        return false;
    }
}
