
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


public class MainController implements Initializable{

    private Database baseDeDatos = null;
    
    @FXML private TableView<Sample> tableView;
    @FXML private TableColumn<Sample, Integer> columnaID;
    @FXML private TableColumn<Sample, SimpleStringProperty> columnaHash;
    @FXML private TableColumn<Sample, SimpleStringProperty> columnaNombreFichero;
    @FXML private TableColumn<Sample, SimpleStringProperty> columnaIP;
    @FXML private TableColumn<Sample, SimpleStringProperty> columnaFechaDeteccion;
    @FXML private TableColumn<Sample, Boolean> columnaAnalizado;
    @FXML private TableColumn<Sample, Boolean> columnaMalicioso;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        columnaID.setCellValueFactory(new PropertyValueFactory<Sample, Integer>("id"));
        columnaHash.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("hash"));
        columnaNombreFichero.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("nombre_fichero"));
        columnaIP.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("remitenteIp"));      
        columnaFechaDeteccion.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("fechaDeteccion"));
        columnaAnalizado.setCellValueFactory(new PropertyValueFactory<Sample, Boolean>("analizado"));
        columnaMalicioso.setCellValueFactory(new PropertyValueFactory<Sample, Boolean>("malicioso"));
    }


    @FXML
    private void onClickMenuConfiguracion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyectoinformaticaii/honeypotmanager/configuracion.fxml"));
        Parent root = loader.load();
        root.setStyle("-fx-font: 13 \"Arial\"; ");
        ConfigurationController configuracionControlador = loader.getController();
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
        this.baseDeDatos.disconnect();
        Platform.exit();
        
    }
    
    public void setBaseDeDatos(Database baseDeDatos){
        this.baseDeDatos = baseDeDatos;
    }
    
    public void refrescarTabla(){
        if(this.baseDeDatos != null){
            tableView.setItems(convertir_muestras(this.baseDeDatos.getSamples()));
        }
    }    
    
    public ObservableList<Sample> convertir_muestras(ArrayList<Sample> listaMuestras){
         ObservableList<Sample> muestras = FXCollections.observableArrayList();
         
         muestras.addAll(listaMuestras);
         
         return muestras;
    }

    @FXML
    private void onClickBorrar(ActionEvent event) {
        Sample muestra = this.tableView.getSelectionModel().getSelectedItem();
        this.baseDeDatos.deleteSample(muestra);
        this.refrescarTabla();
    }
    
    @FXML
    private void onClickRefrescar(ActionEvent event) {
        this.refrescarTabla();
    }
    
}
