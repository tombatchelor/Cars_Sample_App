package com.supercars.util;

public class GarbageCollectorThread implements Runnable {
  public void run() {
    while(true) {
      try {
        Thread.sleep(300000);
        System.gc();
      } catch (Exception ex) {

      }
    }
  }
}
