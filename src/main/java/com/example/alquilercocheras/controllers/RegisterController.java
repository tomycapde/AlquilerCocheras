package com.example.alquilercocheras.controllers;

import com.example.alquilercocheras.database.DatabaseManager;
import com.example.alquilercocheras.database.UserTypeDAO;
import com.example.alquilercocheras.database.UsersDAO;
import com.example.alquilercocheras.models.User;
import com.example.alquilercocheras.models.UserType;
import com.example.alquilercocheras.utils.AlertPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
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
        List<UserType> userTypeList = UserTypeDAO.getUserTypes();
        if (userTypeList == null) {
            System.out.println("No se pudo obtener la lista de tipos de usuario");
            return;
        }
        for (UserType userType : userTypeList) {
            userTypeID.getItems().add(userType.getIdUserType() + "(" + userType.getDescription() + ")");
        }
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
            if (userTypeID.getValue() == null) {
                AlertPanel.showSimpleAlert("Error", "Debe seleccionar un tipo de usuario");
                System.out.println("Please select a user type");
                return;
            }
            newUser.setUsername(userTextField.getText());
            newUser.setPassword(passwordTextField.getText());
            newUser.setSurname(surname.getText());
            newUser.setDNI(DNI.getText());
            newUser.setPhone(phone.getText());
            String userType = userTypeID.getValue();
            newUser.setUserTypeId(Integer.parseInt(userType.substring(0, userType.indexOf("("))));

            System.out.println(newUser.toString());
            UsersDAO.registerUser(newUser);

            AlertPanel.showSimpleAlert("Registro", "Usuario registrado correctamente");
            clearFields();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}
