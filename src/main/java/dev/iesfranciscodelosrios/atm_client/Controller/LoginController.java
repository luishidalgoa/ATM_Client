package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import dev.iesfranciscodelosrios.atm_client.mockup.BankAccount_Service;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;
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

    private BankAccount_Service bankAccountService = BankAccount_Service.getInstance();

    @FXML
    private void login() {
        // Retrieve data from TextFields
        String dni = dni_input.getText();
        String pinText = pin_input.getText();

        // Validate and convert pinText to an integer
        int pin = 0;
        try {
            pin = Integer.parseInt(pinText);
        } catch (NumberFormatException e) {
            // Handle invalid PIN input
            System.out.println("Invalid PIN format");
            return;
        }

        // Create a BankAccount object with the retrieved data
        BankAccount loginAccount = new BankAccount();
        loginAccount.setDni(dni);
        loginAccount.setPin(pin);

        // Call the login method from BankAccount_Service
        boolean loginSuccessful = bankAccountService.login(loginAccount);

        // Check the login result
        if (loginSuccessful) {
            // Navigate to the next screen (assuming success)
            System.out.println("Login successful");
            // Main.setRoot("NextScreen");
        } else {
            // Handle login failure
            System.out.println("Login failed");
        }
    }

    @FXML
    private void register() throws IOException {
        Main.setRoot("register");
    }
}
