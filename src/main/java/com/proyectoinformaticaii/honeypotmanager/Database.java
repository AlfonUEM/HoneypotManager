package com.proyectoinformaticaii.honeypotmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

public class Database {

  private Connection mysqlConnection = null;
  private String srcIP = null; 
  private String database = null; 
  private String user = null; 
  private String pass = null; 
  private String lastError = "";
  private Logger logger = null;
  
  public Database(String srcIP, String database, String user, String pass) {
    this.srcIP = srcIP;
    this.database = database;
    this.user = user;
    this.pass = pass;
    this.logger = Logger.getLogger(Database.class.getName());
  }


  public boolean connect() {
    boolean returnValue = false;
    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.srcIP + ":3306/" 
                                                    + this.database, this.user, this.pass);
      this.mysqlConnection = conn;
      this.logger.log(Level.INFO, "Conectado a la BBDD");
      returnValue = true;
    } catch (SQLException e) {
      this.lastError = e.getMessage();
    }
    return returnValue;
  }
  
  public String getLastError() {
    return this.lastError;
  }
  
  
  public String getVirusTotalAPIKey() {
    String virusTotalAPIKey = "";
    if (this.mysqlConnection != null) {
      try {
        Statement statement = mysqlConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT valor from configuracion where clave = \"VirusTotalAPIKey\"");
        this.logger.log(Level.INFO, "virusTotalAPIKey obtenida");
        if (resultSet.next()) {
          virusTotalAPIKey = resultSet.getString("valor");
        }
        resultSet.close();
      } catch (SQLException ex) {
        this.logger.log(Level.SEVERE, null, ex);
      }
    }
    return virusTotalAPIKey;
  }
  
  public boolean setVirusTotalAPIKey(String virusTotalAPIKey) {
    boolean returnValue = false;
    if (this.mysqlConnection != null) {
      try {
        PreparedStatement preparedStatement = mysqlConnection.prepareStatement(
                "UPDATE configuracion set valor = ? where clave = \"VirusTotalAPIKey\"");
        preparedStatement.setString(1, virusTotalAPIKey);
        int resultado = preparedStatement.executeUpdate();
        if (resultado == 1) {
          this.logger.log(Level.INFO, "virusTotalAPIKey actualizada");
          returnValue = true;
        }
      } catch (SQLException ex) {
        this.logger.log(Level.SEVERE, null, ex);
      }
    }
    return returnValue;
  }

  
  public ArrayList<Sample> getSamples() {
    ArrayList<Sample> samples = new ArrayList();
    if (this.mysqlConnection != null) {
      try {
        Statement statement = mysqlConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT id, nombre_fichero, hash, fecha_llegada, remitente_ip, analizado,"
                        + " malicioso from muestras");
        this.logger.log(Level.INFO, "Datos de ficheros adjuntos obtenidos");
        while (resultSet.next()) {
          Sample muestra = new Sample(resultSet.getInt("id"),
                                      new SimpleStringProperty(resultSet.getString("nombre_fichero")),
                                      new SimpleStringProperty(resultSet.getString("hash")),
                                      new SimpleStringProperty(resultSet.getString("remitente_ip")),
                                      new SimpleStringProperty(resultSet.getString("fecha_llegada")),
                                      resultSet.getBoolean("analizado"),
                                      resultSet.getBoolean("malicioso"));
          samples.add(muestra);     
        }
        resultSet.close();
      } catch (SQLException ex) {
        this.logger.log(Level.SEVERE, null, ex);
      }
    }
    return samples;
  }   

  public void disconnect() {
    if (this.mysqlConnection != null) {
      try {
        this.mysqlConnection.close();
        this.logger.log(Level.INFO, "Desconectado de la BBDD");
        this.mysqlConnection = null;
      } catch (SQLException ex) {
        this.logger.log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public boolean deleteSample(Sample muestra) {
    boolean returnValue = false;
    if (this.mysqlConnection != null) {
      try {
        PreparedStatement preparedStatement = mysqlConnection.prepareStatement(
                                                              "DELETE FROM muestras where id = ?");
        preparedStatement.setInt(1, muestra.getId());
        int resultado = preparedStatement.executeUpdate();
        if (resultado == 1) {
          this.logger.log(Level.INFO, "Fichero adjunto borrado de la base de datos");
          returnValue = true;
        }
      } catch (SQLException ex) {
        this.logger.log(Level.SEVERE, null, ex);
      }
    }
    return returnValue;
  }
}
    

