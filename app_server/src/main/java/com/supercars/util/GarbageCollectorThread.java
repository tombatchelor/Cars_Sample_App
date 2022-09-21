package com.supercars.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GarbageCollectorThread implements Runnable {

  private final Logger logger = Logger.getLogger(GarbageCollectorThread.class.getName());

  public void run() {
    while(true) {
      try {
        Thread.sleep(300000);
        logger.log(Level.FINE, "Running GC");
        System.gc();
      } catch (Exception ex) {

      }
    }
  }
}
