module com.example.alquilercocheras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.alquilercocheras to javafx.fxml;
    opens com.example.alquilercocheras.controllers to javafx.fxml;
    exports com.example.alquilercocheras.controllers to javafx.fxml;
    exports com.example.alquilercocheras to javafx.graphics;
}