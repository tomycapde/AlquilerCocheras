package com.example.alquilercocheras.controllers;

import com.example.alquilercocheras.AlquilerCocherasApp;
import com.example.alquilercocheras.database.DatabaseManager;
import com.example.alquilercocheras.database.UserTypeDAO;
import com.example.alquilercocheras.database.UsersDAO;
import com.example.alquilercocheras.models.User;
import com.example.alquilercocheras.models.UserType;
import com.example.alquilercocheras.utils.AlertPanel;
import com.example.alquilercocheras.utils.PasswordHasher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Do registry controller
public class RegisterController {

    public Button registerButton;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField passwordConfirmTextField;
    @FXML
    private TextField surname;
    @FXML
    private TextField DNI;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<String> userTypeID;
    private User user;

    public void clearFields() {
        userTextField.clear();
        passwordTextField.clear();
        passwordConfirmTextField.clear();
        surname.clear();
        DNI.clear();
        phone.clear();
    }



    @FXML
    private void handleRegisterButtononAction() {

        try {
            User newUser = new User();
            if (userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()
                    || passwordConfirmTextField.getText().isEmpty()
                    || surname.getText().isEmpty()
                    || DNI.getText().isEmpty() ||
                    phone.getText().isEmpty()) {
                AlertPanel.showSimpleAlert("Error", "Debe rellenar todos los campos");
                System.out.println("Please fill all fields");
                return;
            }
            if (!passwordTextField.getText().equals(passwordConfirmTextField.getText())) {
                AlertPanel.showSimpleAlert("Error", "Las contraseñas no coinciden");
                System.out.println("Passwords do not match");
                clearFields();
                return;

            }
            newUser.setUsername(userTextField.getText());
            newUser.setPassword(PasswordHasher.hashPassword(passwordTextField.getText()));
            newUser.setSurname(surname.getText());
            newUser.setDNI(DNI.getText());
            newUser.setPhone(phone.getText());
            newUser.setUserTypeId(1);

            System.out.println(newUser.toString());
            UsersDAO.registerUser(newUser);

            AlertPanel.showSimpleAlert("Registro", "Usuario registrado correctamente");
            clearFields();

            // Load the login view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/alquilercocheras/fxml/login-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the register window
            registerButton.getScene().getWindow().hide();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
