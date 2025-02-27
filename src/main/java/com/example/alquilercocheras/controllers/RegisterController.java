package com.example.alquilercocheras.controllers;

import com.example.alquilercocheras.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

// Do registry controller
public class RegisterController implements Initializable {

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


    public void initialize(URL location, ResourceBundle resources) {
        userTypeID.getItems().addAll("1(USUARIO ADMINISTRAR)", "2(USUARIO NORMAL)");
    }

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
            user = new User();
            int userTypeId = 0;
            String userName = userTextField.getText();
            String password = passwordTextField.getText();
            String passwordConfirm = passwordConfirmTextField.getText();
            String surname = this.surname.getText();
            String DNI = this.DNI.getText();
            String phone = this.phone.getText();
            String userTypeID = this.userTypeID.getValue();
            userTypeId = Integer.parseInt(userTypeID);
            user.setUsername(userName);
            user.setPassword(password);
            user.setSurname(surname);
            user.setDNI(DNI);
            user.setPhone(phone);
            user.setUserTypeId(userTypeId);

            if (userName.isEmpty() || password.isEmpty()
                    || passwordConfirm.isEmpty()
                    || surname.isEmpty()
                    || DNI.isEmpty() ||
                    phone.isEmpty()) {
                System.out.println("Please fill all fields");
                return;
            }
            if (!password.equals(passwordConfirm)) {
                System.out.println("Passwords do not match");
                clearFields();
                return;

            }

            System.out.println("Username: " + userName);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + passwordConfirm);
            System.out.println("Surname: " + surname);
            System.out.println("DNI: " + DNI);
            System.out.println("Phone: " + phone);
            System.out.println("User Type: " + userTypeID);
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Rellena todos los campos");
        }


    }

}
