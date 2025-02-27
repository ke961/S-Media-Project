module com.example.socialm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.socialm to javafx.fxml;
    exports com.example.socialm;
}