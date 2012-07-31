package com.webagesolutions.digest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.webagesolutions.xmlparser.dom.DOMNodeCount;
import com.webagesolutions.xmlparser.sax.SAXNodeCount;

public class MDExample
{
  public MessageDigest curAlgorithm;
  
  public MDExample(String fileName)
  {
    super();
    
    // Set the algorithm
    try {
      curAlgorithm = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // Load the file
    FileInputStream in = null;
    ByteArrayOutputStream buffer = null;
    
    try {
      in = new FileInputStream(fileName);
      buffer = new ByteArrayOutputStream();
      int tmp;
      while((tmp = in.read()) != -1) {
        buffer.write(tmp);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found!");
    } catch (IOException e) {
      System.out.println("File could not be read.");
    }
    
    try {
      in.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    curAlgorithm.reset();
    curAlgorithm.update(buffer.toByteArray());
    byte[] digestHash = curAlgorithm.digest();
    
    System.out.print(fileName + ": ");
    for (int i = 0; i<digestHash.length; i++) {
      System.out.print(Integer.toString(digestHash[i]&0xFF, 16));
    }
    System.out.println();
  }
  
  public static void main(String[] args)
  {
    for (String s : args) {
      new MDExample(s);
    }

    System.out.println("Main done!");
  }
}