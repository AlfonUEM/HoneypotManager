package com.proyectoinformaticaii.honeypotmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {

    @FXML
    private TextField textfield_ip;
    @FXML
    private TextField textfield_bbdd;
    @FXML
    private TextField textfield_usuario;
    @FXML
    private TextField textfield_pass;
    @FXML
    private CheckBox check_recordar_datos;

    private StoredCredentials almacenCredenciales;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        almacenCredenciales = new StoredCredentials();
        textfield_ip.setText( almacenCredenciales.getIp());
        textfield_bbdd.setText( almacenCredenciales.getDatabase());
        textfield_usuario.setText( almacenCredenciales.getUser());
        textfield_pass.setText( almacenCredenciales.getPass());
    }   

    @FXML
    private void onClickOlvidarDatos(ActionEvent event) {
        textfield_ip.setText( "");
        textfield_bbdd.setText("");
        textfield_usuario.setText("");
        textfield_pass.setText("");   
        this.almacenCredenciales.setIp("");
        this.almacenCredenciales.setDatabase("");
        this.almacenCredenciales.setUser("");
        this.almacenCredenciales.setPass("");
        this.almacenCredenciales.writeFile();
    }

    @FXML
    private void onClickConectar(ActionEvent event) throws IOException {
        Database baseDeDatos = new Database(textfield_ip.getText(),
                                                  textfield_bbdd.getText(),
                                                  textfield_usuario.getText(),
                                                  textfield_pass.getText());
        if(baseDeDatos.connect()){
            if(check_recordar_datos.isSelected()){
                this.almacenCredenciales.setIp(textfield_ip.getText());
                this.almacenCredenciales.setDatabase(textfield_bbdd.getText());
                this.almacenCredenciales.setUser(textfield_usuario.getText());
                this.almacenCredenciales.setPass(textfield_pass.getText());
                this.almacenCredenciales.writeFile();
            }
            App.switchToMainWindow(baseDeDatos);
        }else{
            this.mostrarErrorConexion(baseDeDatos.getLastError());
        }
    }
    
    private void mostrarErrorConexion(String razon){
        Alert alert = new Alert(AlertType.ERROR);
        alert.getDialogPane().setStyle("-fx-font: 13 \"Arial\"; ");
        alert.setTitle("Error de conexión");
        alert.setHeaderText(null);
        alert.setContentText("Ha sido imposible conectarse a la base de datos. Mensaje de error:\n" + razon);
        alert.showAndWait();
    }
}
