package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import dev.iesfranciscodelosrios.atm_client.mockup.BankAccount_Service;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Optional;

public class HomeController {
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
    private Label message;
    private BankAccount_Service bankAccountService;

    @FXML
    public void handleActivateExtract() {
        // Mostrar el panelWithdraw y desactivar el panelInsert
        panelWithdraw.setVisible(true);
        panelWithdraw.setManaged(true);
        panelInsert.setVisible(false);
        panelInsert.setManaged(false);
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
                BankAccount currentAccount = bankAccountService.currentAccount;
                if (currentAccount != null) {
                    if (verifyPIN()) {
                        bankAccountService.deposit(currentAccount, amount);
                        // Actualizar la vista o mostrar mensaje de éxito
                        updateView("Inserción de dinero con éxito");
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

    @FXML
    public void withdrawmoney() {
        String amountText = withdrawMoney.getText();
        if (!amountText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                BankAccount currentAccount = bankAccountService.currentAccount;
                if (currentAccount != null) {
                    if (verifyPIN()) {
                        boolean success = bankAccountService.withdraw(currentAccount, amount);
                        if (success) {
                            // Actualizar la vista o mostrar mensaje de éxito
                            updateView("Dinero extraído con éxito");
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

    @FXML
    public void initialize() {
        panelInsert.setVisible(false);
        panelInsert.setManaged(false);
        panelWithdraw.setVisible(false);
        panelWithdraw.setManaged(false);
    }
}