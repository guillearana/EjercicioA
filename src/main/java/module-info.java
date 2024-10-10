module es.guillearana.ejercicioa {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.guillearana.ejercicioa to javafx.fxml;
    exports es.guillearana.ejercicioa;
}