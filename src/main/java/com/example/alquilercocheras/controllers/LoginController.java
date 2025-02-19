package com.example.alquilercocheras.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameText;
    private PasswordField passwordText;

    @FXML
    protected void onLoginButtonClick() {
        System.out.println("Username: " + usernameText.getText());
    }

}
