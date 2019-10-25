/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom.batchelor
 */
public class Leak {

    public static List<byte[]> leakyCollection = new LinkedList<>();

    private final static Logger logger = Logger.getLogger(Leak.class.getName());

    public static void addToCollection(int number, int size) {
        for (int i = 0; i < number; i++) {
            leakyCollection.add(new byte[size]);
        }
    }

    public static long getSize() {
        long size = 0L;
        try {
            for (byte[] bytes : leakyCollection) {
                size += bytes.length;
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
}
