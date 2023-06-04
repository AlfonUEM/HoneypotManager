
package com.proyectoinformaticaii.honeypotmanager;

import javafx.beans.property.SimpleStringProperty;

public class Sample {
    
    private int id;
    private SimpleStringProperty nombre_fichero;
    private SimpleStringProperty hash;
    private SimpleStringProperty remitenteIp;
    private SimpleStringProperty fechaDeteccion;
    private boolean analizado;
    private boolean malicioso;
    
    
    public Sample(int id, SimpleStringProperty nombre_fichero, SimpleStringProperty hash, SimpleStringProperty remitenteIp, SimpleStringProperty fechaDeteccion, boolean analizado, boolean malicioso){
        this.id = id;
        this.nombre_fichero = nombre_fichero;
        this.hash = hash;
        this.remitenteIp = remitenteIp;
        this.fechaDeteccion = fechaDeteccion;
        this.analizado = analizado;
        this.malicioso = malicioso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_fichero() {
        return nombre_fichero.get();
    }

    public void setNombre_fichero(SimpleStringProperty nombre_fichero) {
        this.nombre_fichero = nombre_fichero;
    }

    public String getHash() {
        return hash.get();
    }

    public void setHash(SimpleStringProperty hash) {
        this.hash = hash;
    }
    
    public String getRemitenteIp(){
        return remitenteIp.get();
    }
    
    public void setRemitenteIp(SimpleStringProperty remitenteIp){
        this.remitenteIp = remitenteIp;
    }
    
    public String getFechaDeteccion(){
        return fechaDeteccion.get();
    }
    
    public void setFechaDeteccion(SimpleStringProperty fechaDeteccion){
        this.fechaDeteccion = fechaDeteccion;
    }

    public boolean isAnalizado() {
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }

    public boolean isMalicioso() {
        return malicioso;
    }

    public void setMalicioso(boolean malicioso) {
        this.malicioso = malicioso;
    }
    

    
    
}
