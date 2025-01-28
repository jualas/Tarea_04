module es.jualas.filmoteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires javafx.base;
    requires java.logging;

    opens es.jualas.filmoteca to javafx.fxml, com.fasterxml.jackson.databind;
    exports es.jualas.filmoteca;
}