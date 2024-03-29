package com.webagesolutions.records.sox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webagesolutions.records.Record;
import com.webagesolutions.records.jdbc.ModelJdbcManaged;

public class ModelSoxDaemon implements Runnable
{
  private Socket socket;

  public ModelSoxDaemon(Socket socket)
  {
    super();
    this.socket = socket;
  }

  @Override
  public void run()
  {
    try {
      // Negotiate
      ObjectOutputStream output = new ObjectOutputStream(
          socket.getOutputStream());
      output.writeObject("Welcome to acme records.");
      output.flush();
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      String command = "";
      do {
        command = (String) input.readObject();
        System.out.println(Thread.currentThread().getName() + " received "
            + command);
        String response = "Received '" + command + "'. ";
        Object responseObject = null;
        if ("bye".equals(command)) {
          response += "Thanks for using acme records, bye!";
        } else if ("get".equals(command)) {
          String email = (String) input.readObject();
          responseObject = ModelJdbcManaged.getInstance().getRecord(email);
          if (responseObject == null) {
            response += "No record found for: " + email + ".";
          }
        } else if ("put".equals(command)) {
          Record record = (Record) input.readObject();
          ModelJdbcManaged.getInstance().putRecord(record);
          response += "Put record: " + record;
        }
        if (responseObject == null) {
          responseObject = response;
        }
        output.writeObject(responseObject);
        output.flush();
      } while (!"bye".equals(command));
    } catch (Exception e) {
      System.out.println("err" + e.getMessage());
    } finally {
      if (socket != null) {
        try {
          socket.close();
          System.out.println(Thread.currentThread().getName()
              + " closing socket...");
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public static void main(String[] args)
  {
    ServerSocket serverSocket = null;
    ExecutorService executorService = Executors.newCachedThreadPool();
    try {
      // Connect
      serverSocket = new ServerSocket(10002);
      System.out.println(Thread.currentThread().getName()
          + " awaiting connections on port " + serverSocket.getLocalPort());

      while (true) {
        // Dispatching the daemon threads
        Socket socket = serverSocket.accept();
        System.out.println(Thread.currentThread().getName()
            + " connection from " + socket.getRemoteSocketAddress());
        executorService.execute(new ModelSoxDaemon(socket));
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      executorService.shutdown();
      if (serverSocket != null) {
        try {
          serverSocket.close();
          System.out.println(Thread.currentThread().getName()
              + " closing server socket...");
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }

    }
  }
}
