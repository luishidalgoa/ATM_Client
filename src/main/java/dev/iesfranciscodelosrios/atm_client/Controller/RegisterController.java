package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField name_text;

    @FXML
    private TextField surname_text;

    @FXML
    private TextField dni_text;

    @FXML
    private TextField iban_text;

    @FXML
    private TextField pin_text;

    @FXML
    private Button register_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private void User_register() throws IOException {
        Main.setRoot("Login");
    }

    @FXML
    private void Exit() throws IOException{
        Main.setRoot("Login");
    }


}
