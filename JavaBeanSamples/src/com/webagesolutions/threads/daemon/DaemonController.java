package com.webagesolutions.threads.daemon;

public class DaemonController
{
  private Daemon d = new Daemon("daemon");
  Thread t = new Thread(d);

  public void start()
  {
    t.start();
  }
  
  public void stop()
  {
    d.kill();
  }

}
