
package com.proyectoinformaticaii.honeypotmanager;

import javafx.beans.property.SimpleStringProperty;

public class Sample {
    
    private int id;
    private SimpleStringProperty filename;
    private SimpleStringProperty hash;
    private SimpleStringProperty srcIP;
    private SimpleStringProperty detectionDate;
    private boolean isAnalized;
    private boolean isMalicious;
    
    
    public Sample(int id, SimpleStringProperty filename, SimpleStringProperty hash, SimpleStringProperty srcIp, SimpleStringProperty detectionDate, boolean analized, boolean malicious){
        this.id = id;
        this.filename = filename;
        this.hash = hash;
        this.srcIP = srcIp;
        this.detectionDate = detectionDate;
        this.isAnalized = analized;
        this.isMalicious = malicious;
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

    public boolean isAnalyzed() {
        return isAnalized;
    }

    public void setAnalized(boolean analized) {
        this.isAnalized = analized;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public void setMalicious(boolean malicious) {
        this.isMalicious = malicious;
    }
    

    
    
}
