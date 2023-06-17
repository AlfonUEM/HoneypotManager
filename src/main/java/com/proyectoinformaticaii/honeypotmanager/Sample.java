
package com.proyectoinformaticaii.honeypotmanager;

import javafx.beans.property.SimpleStringProperty;

public class Sample {
    
    private int id;
    private SimpleStringProperty filename;
    private SimpleStringProperty hash;
    private SimpleStringProperty srcIP;
    private SimpleStringProperty detectionDate;
    private boolean analized;
    private boolean malicious;
    
    
    public Sample(int id, SimpleStringProperty filename, SimpleStringProperty hash, SimpleStringProperty srcIp, SimpleStringProperty detectionDate, boolean analized, boolean malicious){
        this.id = id;
        this.filename = filename;
        this.hash = hash;
        this.srcIP = srcIp;
        this.detectionDate = detectionDate;
        this.analized = analized;
        this.malicious = malicious;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename.get();
    }

    public void setFilename(SimpleStringProperty filename) {
        this.filename = filename;
    }

    public String getHash() {
        return hash.get();
    }

    public void setHash(SimpleStringProperty hash) {
        this.hash = hash;
    }
    
    public String getSrcIp(){
        return srcIP.get();
    }
    
    public void setSrcIp(SimpleStringProperty srcIp){
        this.srcIP = srcIp;
    }
    
    public String getDetectionDate(){
        return detectionDate.get();
    }
    
    public void setDetectionDate(SimpleStringProperty detectionDate){
        this.detectionDate = detectionDate;
    }

    public boolean getAnalyzed() {
        return analized;
    }

    public void setAnalized(boolean analized) {
        this.analized = analized;
    }

    public boolean getMalicious() {
        return malicious;
    }

    public void setMalicious(boolean malicious) {
        this.malicious = malicious;
    }
    

    
    
}
