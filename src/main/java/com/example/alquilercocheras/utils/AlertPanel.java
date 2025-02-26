package com.example.alquilercocheras.utils;

import javafx.scene.control.Alert;

public class AlertPanel {

    public static void showSimpleAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
