module com.example.alquilercocheras {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alquilercocheras to javafx.fxml;
    exports com.example.alquilercocheras;
}