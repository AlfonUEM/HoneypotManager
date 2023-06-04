package com.proyectoinformaticaii.honeypotmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ConfigurationController {

    @FXML
    private Button botonConfiguracionCancelar;
    @FXML
    private Button botonConfiguracionGuardar;    
    @FXML
    private TextField virusTotalAPIKey;

    private Database baseDeDatos = null;


    @FXML
    private void onActionConfiguracionCancelar(ActionEvent event) {
        Stage stage = (Stage) botonConfiguracionCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionConfiguracionGuardar(ActionEvent event) {
        if(this.baseDeDatos != null){
            this.baseDeDatos.setVirusTotalAPIKey(this.virusTotalAPIKey.getText());
        }
        Stage stage = (Stage) botonConfiguracionGuardar.getScene().getWindow();
        stage.close();        
        
    }
   
    public void setBaseDeDatos(Database baseDeDatos){
        this.baseDeDatos = baseDeDatos;
    }
    
    public void refrecarDesdeBaseDeDatos(){
        if(this.baseDeDatos != null){
            this.virusTotalAPIKey.setText(this.baseDeDatos.getVirusTotalAPIKey());
        }
    }
}
