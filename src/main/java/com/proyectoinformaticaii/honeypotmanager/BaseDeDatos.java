/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class BaseDeDatos {

    private Connection conexionMysql = null;
    private String IP = null; 
    private String baseDeDatos = null; 
    private String usuario = null; 
    private String pass = null; 
    private String ultimo_error = "";
    
    public BaseDeDatos(String IP, String baseDeDatos, String usuario, String pass){
        this.IP = IP;
        this.baseDeDatos = baseDeDatos;
        this.usuario = usuario;
        this.pass = pass;
    }


    public boolean conectar(){
        boolean valorRetorno = false;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.IP +":3306/" + this.baseDeDatos, this.usuario, this.pass);
            this.conexionMysql = conn;
            valorRetorno = true;
        } catch (SQLException e) {
            this.ultimo_error = e.getMessage();
        }
        return valorRetorno;
    }
    
    public String getUltimoError(){
        return this.ultimo_error;
    }
    
    
    public String getVirusTotalAPIKey(){
        String virusTotalAPIKey = "";
        try {
            Statement statement = conexionMysql.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT valor from configuracion where clave = \"VirusTotalAPIKey\"");
            if(resultSet.next()){
                virusTotalAPIKey = resultSet.getString("valor");
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return virusTotalAPIKey;
    }
    
    public boolean setVirusTotalAPIKey(String virusTotalAPIKey){
        boolean valorRetorno = false;
        try {
            PreparedStatement preparedStatement = conexionMysql.prepareStatement("UPDATE configuracion set valor = ? where clave = \"VirusTotalAPIKey\"");
            preparedStatement.setString(1, virusTotalAPIKey);
            int resultado = preparedStatement.executeUpdate();
            if(resultado == 1){
                valorRetorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valorRetorno;
    }

    
    public ArrayList<Muestra> getMuestras(){
        ArrayList<Muestra> muestras = new ArrayList();
        try {
            Statement statement = conexionMysql.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nombre_fichero, hash, fecha_llegada, remitente_ip, analizado, malicioso from muestras");
            while(resultSet.next()){
                Muestra muestra = new Muestra(resultSet.getInt("id"),
                                              new SimpleStringProperty(resultSet.getString("nombre_fichero")),
                                              new SimpleStringProperty(resultSet.getString("hash")),
                                              new SimpleStringProperty(resultSet.getString("remitente_ip")),
                                              new SimpleStringProperty(resultSet.getString("fecha_llegada")),
                                              resultSet.getBoolean("analizado"),
                                              resultSet.getBoolean("malicioso"));
                muestras.add(muestra);     
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muestras;
    }   

    public void desconectar(){
        try {
            this.conexionMysql.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean borrarMuestra(Muestra muestra){
        boolean valorRetorno = false;
        try {
            PreparedStatement preparedStatement = conexionMysql.prepareStatement("DELETE FROM muestras where id = ?");
            preparedStatement.setInt(1, muestra.getId());
            int resultado = preparedStatement.executeUpdate();
            if(resultado == 1){
                valorRetorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valorRetorno;
    }
}
    

