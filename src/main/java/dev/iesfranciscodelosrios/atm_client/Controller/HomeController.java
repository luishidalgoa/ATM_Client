package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import dev.iesfranciscodelosrios.atm_client.Service.BankAccount_Service;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    Main main = new Main();
    @FXML
    private TableView<?> back;
    @FXML
    private TableColumn<BankAccount, String> columDate;

    @FXML
    private TableColumn<BankAccount, Integer> columnIBAN;

    @FXML
    private TableColumn<BankAccount, String> columnType;
    @FXML
    private Button Activate_extract;
    @FXML
    private Button Activate_get_into;
    @FXML
    private Button insert_money;
    @FXML
    private Button withdrawmoney;

    @FXML
    private ImageView icon;

    @FXML
    private AnchorPane panelInsert;
    @FXML
    private AnchorPane panelWithdraw;
    @FXML
    private TextField insertMoney;
    @FXML
    private TextField withdrawMoney;
    @FXML
    private Label name;
    @FXML
    private Label dni;
    @FXML
    private Label iban;
    @FXML
    private Label message;
    @FXML
    private Label saldo;
    private BankAccount_Service bankAccountService;

    public HomeController(){
        bankAccountService = BankAccount_Service.getInstance();
    }

    @FXML
    public void initialize() {
        this.name.setText(this.bankAccountService.currentAccount.name + " " + this.bankAccountService.currentAccount.surname);
        this.dni.setText(this.bankAccountService.currentAccount.dni);
        this.iban.setText(String.valueOf(this.bankAccountService.currentAccount.IBAN));
        this.saldo.setText(String.valueOf(this.bankAccountService.currentAccount.balance));
    }

    @FXML
    public void handleActivateExtract() {
        // Mostrar el panelWithdraw y desactivar el panelInsert
        panelWithdraw.setVisible(true);
        panelWithdraw.setManaged(true);
        panelInsert.setVisible(false);
        panelInsert.setManaged(false);
    }

    @FXML
    public void handleLogout(){
        if(this.bankAccountService.logout()){
            try {
                Main.setRoot("Login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void handleActivateGetInto() {
        // Mostrar el panelInsert y desactivar el panelWithdraw
        panelInsert.setVisible(true);
        panelInsert.setManaged(true);
        panelWithdraw.setVisible(false);
        panelWithdraw.setManaged(false);
    }


    @FXML
    public void insetMoney() {
        String amountText = insertMoney.getText();
        if (!amountText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                if (verifyPIN()) {
                    bankAccountService.deposit(amount);
                    // Actualizar la vista o mostrar mensaje de éxito
                    updateView("Inserción de dinero con éxito");
                    this.saldo.setText(String.valueOf(bankAccountService.currentAccount.balance));
                } else {
                    showError("PIN incorrecto");
                }
            } catch (NumberFormatException e) {
                showError("Formato de cantidad incorrecto (Debe ser un número)");
            }
        } else {
            showError("Por favor, introduzca una cantidad");
        }
    }

    @FXML
    public void withdrawmoney() {
        String amountText = withdrawMoney.getText();
        if (!amountText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                BankAccount currentAccount = bankAccountService.currentAccount;
                if (currentAccount != null) {
                    if (verifyPIN()) {
                        boolean success = bankAccountService.withdraw(amount);
                        if (success) {
                            // Actualizar la vista o mostrar mensaje de éxito
                            updateView("Dinero extraído con éxito");
                            this.saldo.setText(String.valueOf(bankAccountService.currentAccount.balance));
                        } else {
                            showError("Saldo insuficiente");
                        }
                    } else {
                        showError("PIN incorrecto");
                    }
                }
            } catch (NumberFormatException e) {
                showError("Formato de cantidad incorrecto (Debe ser un número)");
            }
        } else {
            showError("Por favor, introduzca una cantidad");
        }
    }

    private boolean verifyPIN() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Verificar PIN");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el PIN de la cuenta:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String enteredPIN = result.get();
            BankAccount currentAccount = bankAccountService.currentAccount;
            return currentAccount != null && String.valueOf(currentAccount.getPin()).equals(enteredPIN);
        }
        return false;
    }


    private void updateView(String successMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(successMessage);
        alert.showAndWait();
        // Aquí puedes agregar código para actualizar la vista, por ejemplo, actualizar el saldo mostrado
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name.setText(this.bankAccountService.currentAccount.name + " " + this.bankAccountService.currentAccount.surname);
        this.dni.setText(this.bankAccountService.currentAccount.dni);
        this.iban.setText(String.valueOf(this.bankAccountService.currentAccount.IBAN));
        this.saldo.setText(String.valueOf(this.bankAccountService.currentAccount.balance));
    }
}