module dev.iesfranciscodelosrios.atm_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.iesfranciscodelosrios.atm_client to javafx.fxml;
    exports dev.iesfranciscodelosrios.atm_client;
}