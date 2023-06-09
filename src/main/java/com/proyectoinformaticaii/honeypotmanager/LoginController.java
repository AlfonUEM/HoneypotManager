package com.proyectoinformaticaii.honeypotmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {

  @FXML
  private TextField textfieldIp;
  @FXML
  private TextField textfieldDatabase;
  @FXML
  private TextField textfieldUser;
  @FXML
  private TextField textfieldPass;
  @FXML
  private CheckBox checkRememberData;

  private StoredCredentials storedDredentials;

  @Override
  public void initialize(URL url, ResourceBundle rb) {    
    storedDredentials = new StoredCredentials();
    textfieldIp.setText(storedDredentials.getIp());
    textfieldDatabase.setText(storedDredentials.getDatabase());
    textfieldUser.setText(storedDredentials.getUser());
    textfieldPass.setText(storedDredentials.getPass());
  }   

  @FXML
  private void onClickForgetData(ActionEvent event) {
    Logger.getLogger(LoginController.class.getName()).log(Level.INFO, 
            "click onClickForgetData");    
    textfieldIp.setText("");
    textfieldDatabase.setText("");
    textfieldUser.setText("");
    textfieldPass.setText("");   
    this.storedDredentials.setIp("");
    this.storedDredentials.setDatabase("");
    this.storedDredentials.setUser("");
    this.storedDredentials.setPass("");
    this.storedDredentials.writeFile();
  }

  @FXML
  private void onClickConnect(ActionEvent event) {
    Logger.getLogger(LoginController.class.getName()).log(Level.INFO, 
            "click onClickConnect");        
    Database baseDeDatos = new Database(textfieldIp.getText(),
                                        textfieldDatabase.getText(),
                                        textfieldUser.getText(),
                                        textfieldPass.getText());
    if (baseDeDatos.connect()) {
      if (checkRememberData.isSelected()) {
        this.storedDredentials.setIp(textfieldIp.getText());
        this.storedDredentials.setDatabase(textfieldDatabase.getText());
        this.storedDredentials.setUser(textfieldUser.getText());
        this.storedDredentials.setPass(textfieldPass.getText());
        this.storedDredentials.writeFile();
      }
      try {
        App.switchToMainWindow(baseDeDatos);
      } catch (IOException ex) {
        this.showConnectionError(ex.getMessage());
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      this.showConnectionError(baseDeDatos.getLastError());
    }
  }
  
  private void showConnectionError(String razon) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.getDialogPane().setStyle("-fx-font: 13 \"Arial\"; ");
    alert.setTitle("Error de conexión");
    alert.setHeaderText(null);
    alert.setContentText("Ha sido imposible conectarse a la base de datos. Error:\n" + razon);
    alert.showAndWait();
  }
}
