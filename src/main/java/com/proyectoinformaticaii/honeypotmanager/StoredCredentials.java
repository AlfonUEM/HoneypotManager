package com.proyectoinformaticaii.honeypotmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class StoredCredentials {
  private String ip = ""; 
  private String database = "";
  private String user = "";
  private String pass = "";
  private String filePath = "honeypot_almacen_credenciales";
  
  public StoredCredentials(){
    this.readFromFile();
  }
  
  public boolean readFromFile(){
    boolean returnValue = false;
    FileReader fileReader = null;
    BufferedReader bufferReader;
    try {
      fileReader = new FileReader (new File (this.filePath));
      bufferReader = new BufferedReader(fileReader);
      this.ip = bufferReader.readLine();
      this.database = bufferReader.readLine();
      this.user = bufferReader.readLine();
      this.pass = bufferReader.readLine();
      returnValue = true;
    }
    catch(IOException exceptionRead){
      exceptionRead.printStackTrace();
    }
    finally{
      try{
        if(fileReader != null){
          fileReader.close();
        }
      }catch (IOException exceptionClose){
        exceptionClose.printStackTrace();
      }
    }
    return returnValue;
  }
  
  public String getIp(){
    return this.ip;
  }
  
  public String getDatabase(){
    return this.database;
  }    
  
  public String getUser(){
    return this.user;
  }
  
  public String getPass(){
    return this.pass;
  }
  
  public void setIp(String ip){
    this.ip = ip;
  }
  
  public void setDatabase(String database){
    this.database = database;
  }    
  
  public void setUser(String user){
    this.user = user;
  }
  
  public void setPass(String pass){
    this.pass = pass;
  }    
  
  public boolean writeFile(){
    boolean returnValue = false;
    FileWriter fileWriter = null;
    PrintWriter printWriter = null;
    try{
      fileWriter = new FileWriter(this.filePath);
      printWriter = new PrintWriter(fileWriter);
      printWriter.println(this.ip);
      printWriter.println(this.database);
      printWriter.println(this.user);
      printWriter.println(this.pass);
      returnValue = true;

    } catch (IOException exceptionWrite) {
      exceptionWrite.printStackTrace();
    } finally {
      try {
        if (fileWriter != null){
          fileWriter.close();
        }
      } catch (IOException exceptionClose) {
        exceptionClose.printStackTrace();
      }
    }
    return returnValue;
  }

}

