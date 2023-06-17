package com.proyectoinformaticaii.honeypotmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ConfigurationController {

    @FXML
    private Button cancelConfigurationButton;
    @FXML
    private Button saveConfigurationButton;    
    @FXML
    private TextField virusTotalAPIKey;

    private Database baseDeDatos = null;


    @FXML
    private void onActionConfigurationCancel(ActionEvent event) {
        Stage stage = (Stage) cancelConfigurationButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionConfigurationSave(ActionEvent event) {
        if(this.baseDeDatos != null){
            this.baseDeDatos.setVirusTotalAPIKey(this.virusTotalAPIKey.getText());
        }
        Stage stage = (Stage) saveConfigurationButton.getScene().getWindow();
        stage.close();        
        
    }
   
    public void setDatabase(Database database){
        this.baseDeDatos = database;
    }
    
    public void refreshFromDatabase(){
        if(this.baseDeDatos != null){
            this.virusTotalAPIKey.setText(this.baseDeDatos.getVirusTotalAPIKey());
        }
    }
}
