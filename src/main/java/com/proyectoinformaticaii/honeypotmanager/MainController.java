/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyectoinformaticaii.honeypotmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alfon
 */
public class MainController implements Initializable{

    private BaseDeDatos baseDeDatos = null;
    
    @FXML private TableView<Muestra> tableView;
    @FXML private TableColumn<Muestra, Integer> columnaID;
    @FXML private TableColumn<Muestra, SimpleStringProperty> columnaHash;
    @FXML private TableColumn<Muestra, SimpleStringProperty> columnaNombreFichero;
    @FXML private TableColumn<Muestra, SimpleStringProperty> columnaIP;
    @FXML private TableColumn<Muestra, SimpleStringProperty> columnaFechaDeteccion;
    @FXML private TableColumn<Muestra, Boolean> columnaAnalizado;
    @FXML private TableColumn<Muestra, Boolean> columnaMalicioso;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        columnaID.setCellValueFactory(new PropertyValueFactory<Muestra, Integer>("id"));
        columnaHash.setCellValueFactory(new PropertyValueFactory<Muestra, SimpleStringProperty>("hash"));
        columnaNombreFichero.setCellValueFactory(new PropertyValueFactory<Muestra, SimpleStringProperty>("nombre_fichero"));
        columnaIP.setCellValueFactory(new PropertyValueFactory<Muestra, SimpleStringProperty>("remitenteIp"));      
        columnaFechaDeteccion.setCellValueFactory(new PropertyValueFactory<Muestra, SimpleStringProperty>("fechaDeteccion"));
        columnaAnalizado.setCellValueFactory(new PropertyValueFactory<Muestra, Boolean>("analizado"));
        columnaMalicioso.setCellValueFactory(new PropertyValueFactory<Muestra, Boolean>("malicioso"));
    }


    @FXML
    private void onClickMenuConfiguracion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyectoinformaticaii/honeypotmanager/configuracion.fxml"));
        Parent root = loader.load();
        root.setStyle("-fx-font: 13 \"Arial\"; ");
        ConfiguracionController configuracionControlador = loader.getController();
        configuracionControlador.setBaseDeDatos(this.baseDeDatos);
        configuracionControlador.refrecarDesdeBaseDeDatos();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void onClickMenuDesconectar(ActionEvent event) throws IOException {
        this.baseDeDatos.desconectar();
        Platform.exit();
        
    }
    
    public void setBaseDeDatos(BaseDeDatos baseDeDatos){
        this.baseDeDatos = baseDeDatos;
    }
    
    public void refrescarTabla(){
        if(this.baseDeDatos != null){
            tableView.setItems(convertir_muestras(this.baseDeDatos.getMuestras()));
        }
    }    
    
    public ObservableList<Muestra> convertir_muestras(ArrayList<Muestra> listaMuestras){
         ObservableList<Muestra> muestras = FXCollections.observableArrayList();
         
         //muestras.add(new Muestra(1,new SimpleStringProperty("a"),new SimpleStringProperty("b"),true,false));
         muestras.addAll(listaMuestras);
         
         return muestras;
    }

    @FXML
    private void onClickBorrar(ActionEvent event) {
        Muestra muestra = this.tableView.getSelectionModel().getSelectedItem();
        this.baseDeDatos.borrarMuestra(muestra);
        this.refrescarTabla();
    }
    
    @FXML
    private void onClickRefrescar(ActionEvent event) {
        this.refrescarTabla();
    }
    
}
