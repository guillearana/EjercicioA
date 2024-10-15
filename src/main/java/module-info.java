module es.guillearana.ejercicioa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens es.guillearana.ejercicioa to javafx.fxml;
    exports es.guillearana.ejercicioa;
}