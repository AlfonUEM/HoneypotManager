package com.proyectoinformaticaii.honeypotmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StoredCredentials {
  private String ip = ""; 
  private String database = "";
  private String user = "";
  private String pass = "";
  private String filePath = "honeypot_almacen_credenciales";
  private Encrypter encrypter = null;
  private Logger logger = null;
  
  public StoredCredentials() {
    this.logger = Logger.getLogger(StoredCredentials.class.getName());
    try {
      this.encrypter = new Encrypter();
    } catch (Exception ex) {
      this.logger.log(Level.SEVERE, null, ex);
    } 
    this.readFromFile();
  }
  
  public boolean readFromFile() {
    boolean returnValue = false;
    FileReader fileReader = null;
    BufferedReader bufferReader;
    try {
      fileReader = new FileReader(new File(this.filePath));
      bufferReader = new BufferedReader(fileReader);
      this.ip = bufferReader.readLine();
      this.database = bufferReader.readLine();
      this.user = bufferReader.readLine();
      this.pass = bufferReader.readLine();
      if (this.encrypter != null) {
        try {
          this.pass = this.encrypter.decryptString(this.pass);
        } catch (Exception ex) {
          this.logger.log(Level.SEVERE, null, ex);
        }
      }
      returnValue = true;
    } catch (IOException exceptionRead) {
      this.logger.log(Level.SEVERE, null, exceptionRead);
    } finally {
      try {
        if (fileReader != null) {
          fileReader.close();
        }
      } catch (IOException exceptionClose) {
        this.logger.log(Level.SEVERE, null, exceptionClose);
      }
    }
    return returnValue;
  }
  
  public String getIp() {
    return this.ip;
  }
  
  public String getDatabase() {
    return this.database;
  }    
  
  public String getUser() {
    return this.user;
  }
  
  public String getPass() {
    return this.pass;
  }
  
  public void setIp(String ip) {
    this.ip = ip;
  }
  
  public void setDatabase(String database) {
    this.database = database;
  }    
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public void setPass(String pass) {
    this.pass = pass;
  }    
  
  public boolean writeFile() {
    boolean returnValue = false;
    FileWriter fileWriter = null;
    PrintWriter printWriter = null;
    try {
      fileWriter = new FileWriter(this.filePath);
      printWriter = new PrintWriter(fileWriter);
      printWriter.println(this.ip);
      printWriter.println(this.database);
      printWriter.println(this.user);
      if (this.encrypter != null) {
        try {
          this.pass = this.encrypter.encryptString(this.pass);
        } catch (Exception ex) {
          this.logger.log(Level.SEVERE, null, ex);
        }
      }
      printWriter.println(this.pass);
      returnValue = true;

    } catch (IOException exceptionWrite) {
      this.logger.log(Level.SEVERE, null, exceptionWrite);
    } finally {
      try {
        if (fileWriter != null) {
          fileWriter.close();
        }
      } catch (IOException exceptionClose) {
        this.logger.log(Level.SEVERE, null, exceptionClose);
      }
    }
    return returnValue;
  }

}

