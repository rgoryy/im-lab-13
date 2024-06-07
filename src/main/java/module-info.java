module com.example.imlab13 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imlab13 to javafx.fxml;
    exports com.example.imlab13;
}