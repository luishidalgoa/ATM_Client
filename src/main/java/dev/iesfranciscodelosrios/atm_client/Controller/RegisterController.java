package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import dev.iesfranciscodelosrios.atm_client.Service.BankAccount_Service;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;
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
    private TextField pin_text;

    @FXML
    private Button register_btn;

    @FXML
    private Button cancel_btn;

    private BankAccount_Service bankAccountService = BankAccount_Service.getInstance();

    @FXML
    private void User_register() throws IOException {
        // Retrieve data from TextFields
        String name = name_text.getText();
        String surname = surname_text.getText();
        String dni = dni_text.getText();
        String pinText = pin_text.getText();

        // Validate and convert pinText to an integer
        int pin = 0;
        try {
            pin = Integer.parseInt(pinText);
        } catch (NumberFormatException e) {
            // Handle invalid pin input
            System.out.println("Invalid PIN format");
            return;
        }

        // Create a BankAccount object with the retrieved data
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName(name);
        bankAccount.setSurname(surname);
        bankAccount.setDni(dni);
        bankAccount.setPin(pin);

        // Call the register method from BankAccount_Service
        BankAccount registeredAccount = bankAccountService.register(bankAccount);
        if(registeredAccount != null){
            bankAccountService.currentAccount = registeredAccount;
            Main.setRoot("Login");
        }
    }

    @FXML
    private void Exit() throws IOException {
        Main.setRoot("Login");
    }
}
