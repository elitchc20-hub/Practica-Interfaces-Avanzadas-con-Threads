module com.mycompany.contar {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.contar to javafx.fxml;
    exports com.mycompany.contar;
}
