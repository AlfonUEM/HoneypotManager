
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

  private Database database = null;
  
  @FXML private TableView<Sample> tableView;
  @FXML private TableColumn<Sample, Integer> columnID;
  @FXML private TableColumn<Sample, SimpleStringProperty> columnHash;
  @FXML private TableColumn<Sample, SimpleStringProperty> columnFilename;
  @FXML private TableColumn<Sample, SimpleStringProperty> columnIP;
  @FXML private TableColumn<Sample, SimpleStringProperty> columnDetectionDate;
  @FXML private TableColumn<Sample, Boolean> columnAnalized;
  @FXML private TableColumn<Sample, Boolean> columnMalicious;
  
  
  @Override
  public void initialize(URL url, ResourceBundle rb){
    columnID.setCellValueFactory(new PropertyValueFactory<Sample, Integer>("id"));
    columnHash.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("hash"));
    columnFilename.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("filename"));
    columnIP.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("srcIp"));      
    columnDetectionDate.setCellValueFactory(new PropertyValueFactory<Sample, SimpleStringProperty>("detectionDate"));
    columnAnalized.setCellValueFactory(new PropertyValueFactory<Sample, Boolean>("analyzed"));
    columnMalicious.setCellValueFactory(new PropertyValueFactory<Sample, Boolean>("malicious"));
  }


  @FXML
  private void onClickMenuConfiguration(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proyectoinformaticaii/honeypotmanager/configuracion.fxml"));
    Parent root = loader.load();
    root.setStyle("-fx-font: 13 \"Arial\"; ");
    ConfigurationController configurationController = loader.getController();
    configurationController.setDatabase(this.database);
    configurationController.refreshFromDatabase();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
    stage.showAndWait();
  }
  
  @FXML
  private void onClickMenuDisconnect(ActionEvent event) throws IOException {
    this.database.disconnect();
    Platform.exit();
      
  }
  
  public void setDatabase(Database database){
    this.database = database;
  }
  
  public void refreshTable(){
    if(this.database != null){
      tableView.setItems(convertSamples(this.database.getSamples()));
    }
  }    
  
  public ObservableList<Sample> convertSamples(ArrayList<Sample> samplesList){
    ObservableList<Sample> samples = FXCollections.observableArrayList();
    samples.addAll(samplesList);
    return samples;
  }

  @FXML
  private void onClickDelete(ActionEvent event) {
    Sample muestra = this.tableView.getSelectionModel().getSelectedItem();
    this.database.deleteSample(muestra);
    this.refreshTable();
  }
  
  @FXML
  private void onClickRefresh(ActionEvent event) {
    this.refreshTable();
  }
  
}
