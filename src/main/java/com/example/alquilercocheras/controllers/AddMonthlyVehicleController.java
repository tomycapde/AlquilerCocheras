package com.example.alquilercocheras.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddMonthlyVehicleController {
    @FXML
    private TextField plateTextField;
    @FXML
    private TextField vehicleTextField;
    @FXML
    private TextField dniTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private DatePicker entryDatePicker;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private TextField monthlyAmountTextField;
    @FXML
    private TextField nameAndSurnameTextField;

    public void handleRegisterButton() {
        
        
        
        if (plateTextField.getText().isEmpty() || vehicleTextField.getText().isEmpty() || dniTextField.getText().isEmpty()
                || telefonoTextField.getText().isEmpty() || entryDatePicker.getValue() == null
                || departureDatePicker.getValue() == null || monthlyAmountTextField.getText().isEmpty()
                || nameAndSurnameTextField.getText().isEmpty()) {
            System.out.println("Campos incompletos");
            return;
        }
        try {

            System.out.println("Patente:"+plateTextField.getText());
            System.out.println("Vehiculo:"+vehicleTextField.getText());
            System.out.println("DNI:"+dniTextField.getText());
            System.out.println("Telefono:"+telefonoTextField.getText());
            System.out.println("Monto mensual:"+monthlyAmountTextField.getText());
            System.out.println("Nombre y apellido:"+nameAndSurnameTextField.getText());
            getEntryDate();
            getDepartureDate();
            System.out.println("Mensual registrado correctamente");
            clearFields();
        } catch (Exception e) {
            System.out.println("Error");
            clearFields();
        }
    }

    public void clearFields() {
        plateTextField.clear();
        vehicleTextField.clear();
        dniTextField.clear();
        telefonoTextField.clear();
        entryDatePicker.getEditor().clear();
        departureDatePicker.getEditor().clear();
        monthlyAmountTextField.clear();
        nameAndSurnameTextField.clear();
    }

    public void getEntryDate(){
        // Get the entry date
        LocalDate myDate = entryDatePicker.getValue();
        String formattedDate=myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println(formattedDate);
    }
    public void getDepartureDate(){
        // Get the departure date
        LocalDate myDate = departureDatePicker.getValue();
        String formattedDate=myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println(formattedDate);
    }

}
