package com.example.alquilercocheras.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Hello, World!");
    }

    public void agregarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/alquilercocheras/fxml/add-client-form.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Agregar Cliente");
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal

            Platform.runLater(() -> { // Ejecutar en el hilo de JavaFX
                try {
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

