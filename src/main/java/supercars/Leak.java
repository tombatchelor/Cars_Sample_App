/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercars;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tom.batchelor
 */
public class Leak {
    public static List<byte[]> leakyCollection = new LinkedList<>();
    
    public static void addToCollection(int number, int size) {
        for (int i = 0; i < number; i++) {
            leakyCollection.add(new byte[size]);
        }
    }
    
    public static long getSize() {
        long size = 0L;
        for(byte[] bytes : leakyCollection) {
            size += bytes.length;
        }
        
        return size;
    }
    
    public static void drainCollection() {
        leakyCollection = new LinkedList<>();
    }
}
