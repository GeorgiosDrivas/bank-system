module com.example.banksystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.banksystem to javafx.fxml;
    exports com.example.banksystem;
}