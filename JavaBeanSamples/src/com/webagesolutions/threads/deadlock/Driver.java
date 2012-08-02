package com.webagesolutions.threads.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver
{
  public static void main(String[] args)
  {
    int n = 5;
    List<Philosopher> philosophers = new ArrayList<Philosopher>();
    ExecutorService e = Executors.newCachedThreadPool();
    ChopStick chopStickLeft = new ChopStick("chopstick"+1);
    ChopStick chopStickRight = null;
    ChopStick chopStickFirst = chopStickLeft;
    
    for (int i = 1; i < n+1; i++) {
      chopStickRight = (i == n) ? chopStickFirst : new ChopStick("chopstick"+(i+1));      
      Philosopher p = new Philosopher("Philosopher" + i, chopStickLeft, chopStickRight);
      philosophers.add(p);
      chopStickLeft = chopStickRight;
    }
    
    for (Philosopher philosopher : philosophers) {
      e.execute(philosopher);
    }
    System.out.println("driver done...");
  }
}
