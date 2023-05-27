module com.proyectoinformaticaii.honeypotmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.proyectoinformaticaii.honeypotmanager to javafx.fxml;
    exports com.proyectoinformaticaii.honeypotmanager;
}
