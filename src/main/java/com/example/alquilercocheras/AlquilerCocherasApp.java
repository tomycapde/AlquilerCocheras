package com.example.alquilercocheras;

import com.example.alquilercocheras.database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlquilerCocherasApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DatabaseManager.initializeDatabase();

        if (DatabaseManager.databaseHasUsers()) {
            FXMLLoader fxmlLoader = new FXMLLoader(AlquilerCocherasApp.class.getResource("/com/example/alquilercocheras/fxml/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setTitle("Inicio");
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No hay usuarios en la base de datos");
            FXMLLoader fxmlLoader = new FXMLLoader(AlquilerCocherasApp.class.getResource("/com/example/alquilercocheras/fxml/register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.show();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}