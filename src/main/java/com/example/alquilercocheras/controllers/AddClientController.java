package com.example.alquilercocheras.controllers;

import com.example.alquilercocheras.database.ClientDAO;
import com.example.alquilercocheras.models.Client;
import com.example.alquilercocheras.utils.AlertPanel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddClientController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private TextField dniTextField;
    @FXML
    private TextField telefonoTextField;

    public void handleRegisterButtonAction() {

        if (nombreTextField.getText().isEmpty() || apellidoTextField.getText().isEmpty() || dniTextField.getText().isEmpty() || telefonoTextField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
            return;
        }
        try {
            Client newClient = new Client(nombreTextField.getText(), apellidoTextField.getText(), dniTextField.getText(), telefonoTextField.getText());
            ClientDAO.registerClient(newClient);
            AlertPanel.showSimpleAlert("Cliente Registrado", "Cliente registrado correctamente");
            clearFields();

        } catch (Exception e) {
            AlertPanel.showSimpleAlert("Error", e.getMessage());
            clearFields();
        }







        /*
        // Obtener los valores de los campos de texto
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String dni = dniTextField.getText();
        String telefono = telefonoTextField.getText();

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
            // Mostrar un mensaje de error si algún campo está vacío
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
        } else {
            // Crear un nuevo objeto Cliente
            Client cliente = new Client(nombre, apellido, dni, telefono);

            // Mostrar un mensaje de éxito (opcional)
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cliente Registrado");
            alert.setHeaderText("Cliente registrado correctamente");
            alert.setContentText(cliente.toString());
            alert.showAndWait();

            // Aquí puedes agregar lógica para guardar el cliente en la base de datos si lo necesitas.
        }

         */
    }

    public void clearFields() {
        nombreTextField.clear();
        apellidoTextField.clear();
        dniTextField.clear();
        telefonoTextField.clear();
    }
}
