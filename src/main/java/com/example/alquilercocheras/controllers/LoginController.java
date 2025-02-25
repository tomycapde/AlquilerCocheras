package com.example.alquilercocheras.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;

    @FXML
    protected void onLoginButtonClick() {
        try {
            String username = usernameText.getText();
            String password = passwordText.getText();

            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Login successful");

                // Load the main menu
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/alquilercocheras/fxml/main-menu.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Main Menu");
                stage.setScene(new Scene(root));
                stage.show();

                // Close the login window
                Window loginWindow = usernameText.getScene().getWindow();
                loginWindow.hide();

            } else {
                System.out.println("Login failed");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}