package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField dni_input;

    @FXML
    private TextField pin_input;

    @FXML
    private Button login_btn;

    @FXML
    private Button register_btn;

    @FXML
    private void login(){

    }
    @FXML
    private void register() throws IOException {
        Main.setRoot("register");
    }
}
