package com.proyectoinformaticaii.honeypotmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class AlmacenCredenciales {
    private String ip = ""; 
    private String bbdd = "";
    private String usuario = "";
    private String pass = "";
    private String ruta_fichero = "honeypot_almacen_credenciales";
    
    public AlmacenCredenciales(){
        this.leer_de_fichero();
    }
    
    public boolean leer_de_fichero(){
        boolean valor_retorno = false;
        FileReader fileReader = null;
        BufferedReader bufferReader;
        try {
            fileReader = new FileReader (new File (this.ruta_fichero));
            bufferReader = new BufferedReader(fileReader);
            this.ip = bufferReader.readLine();
            this.bbdd = bufferReader.readLine();
            this.usuario = bufferReader.readLine();
            this.pass = bufferReader.readLine();
            valor_retorno = true;
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
        return valor_retorno;
    }
    
    public String get_ip(){
        return this.ip;
    }
    
    public String get_bbdd(){
        return this.bbdd;
    }    
    
    public String get_usuario(){
        return this.usuario;
    }
    
    public String get_pass(){
        return this.pass;
    }
    
    public void set_ip(String ip){
        this.ip = ip;
    }
    
    public void set_bbdd(String bbdd){
        this.bbdd = bbdd;
    }    
    
    public void set_usuario(String usuario){
        this.usuario = usuario;
    }
    
    public void set_pass(String pass){
        this.pass = pass;
    }    
    
    public boolean escribir_fichero(){
        boolean valor_retorno = false;
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try
        {
            fileWriter = new FileWriter(this.ruta_fichero);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(this.ip);
            printWriter.println(this.bbdd);
            printWriter.println(this.usuario);
            printWriter.println(this.pass);
            valor_retorno = true;

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
        return valor_retorno;
    }

}

