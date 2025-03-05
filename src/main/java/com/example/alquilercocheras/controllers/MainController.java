package com.example.alquilercocheras.controllers;

import com.example.alquilercocheras.database.ClientDAO;
import com.example.alquilercocheras.database.DatabaseConnection;
import com.example.alquilercocheras.models.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Client> clientesTable;
    @FXML
    private TableColumn<Client, String> nombreColumn;
    @FXML
    private TableColumn<Client, String> apellidoColumn;
    @FXML
    private TableColumn<Client, String> dniColumn;
    @FXML
    private TableColumn<Client, String> vehiculoColumn;
    @FXML
    private TableColumn<Client, String> contactoColumn;
    @FXML
    private TableColumn<Client, String> patenteColumn;

    public void initialize() {
        // Configura las columnas de la tabla
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        contactoColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Llenar la tabla con los datos de la base de datos
        cargarClientes();
    }

    public void cargarClientes() {
        ObservableList<Client> listaClientes = FXCollections.observableArrayList();

        try {
            List<Client> clients = ClientDAO.getClients();
            listaClientes.addAll(clients);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Establecer la lista de clientes en la tabla
        clientesTable.setItems(listaClientes);
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
                    cargarClientes(); // Actualizar la tabla después de agregar un cliente
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
