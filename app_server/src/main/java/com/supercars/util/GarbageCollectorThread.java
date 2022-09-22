package com.supercars.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GarbageCollectorThread implements Runnable {

  private final Logger logger = Logger.getLogger(GarbageCollectorThread.class.getName());

  public void run() {
    logger.log(Level.FINE, "GC Thread Started");
    while(true) {
      try {
        Thread.sleep(300000);
        logger.log(Level.FINE, "Running GC");
        System.gc();
      } catch (Exception ex) {
        logger.log(Level.SEVERE, "GC Thread Error", ex);
      }
    }
  }
}
