module dev.iesfranciscodelosrios.atm_client {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.iesfranciscodelosrios.atm_client to javafx.fxml;
    opens dev.iesfranciscodelosrios.atm_client.Controller to javafx.fxml; // Agregado para abrir el paquete que contiene HomeController

    exports dev.iesfranciscodelosrios.atm_client;
}