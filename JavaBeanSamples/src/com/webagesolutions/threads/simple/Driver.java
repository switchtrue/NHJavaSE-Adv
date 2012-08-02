package com.webagesolutions.threads.simple;


public class Driver
{
  public static void main(String[] args)
  {
    Thread t = null;
    Worker[] workers = new Worker[5];
    for (int i = 0; i < 5; i++) {
      workers[i] = new Worker("Worker" + i);
      t = new Thread(workers[i]);
//      if (i == 2 || i == 4) {
//        System.out.println("worker" + i + ": priority: " + t.getPriority());
//        t.setPriority(t.getPriority() + 2);
//      }
      t.start();
    }
    try {
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < workers.length; i++) {
      workers[i].kill();
    }
    System.out.println("driver done...");
  }
}
