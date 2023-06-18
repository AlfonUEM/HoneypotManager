package com.proyectoinformaticaii.honeypotmanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("login"));
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
    // Ponemos Arial como fuente por defecto para evitar problemas de visualizacion en MacOS
    Parent root = scene.getRoot();
    root.setStyle("-fx-font: 13 \"Arial\"; ");
  }
    
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
    
  static void switchToMainWindow(Database baseDeDatos) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    MainController controller = fxmlLoader.getController();
    controller.setDatabase(baseDeDatos);
    controller.refreshTable();
    scene.setRoot(root);
    // Ponemos Arial como fuente por defecto para evitar problemas de visualizacion en MacOS
    root = scene.getRoot();
    root.setStyle("-fx-font: 13 \"Arial\"; ");
    scene.getWindow().setWidth(1300);
    scene.getWindow().setHeight(800);
  }    
}
