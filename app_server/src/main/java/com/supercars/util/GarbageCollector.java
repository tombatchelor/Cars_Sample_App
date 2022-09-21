package com. supercars.util;

public class GarbageCollector {
  {
    Thread t = new Thread(new GarbageCollectorThread());
    t.run();
  }
}
