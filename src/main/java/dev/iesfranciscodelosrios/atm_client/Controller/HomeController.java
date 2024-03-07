package dev.iesfranciscodelosrios.atm_client.Controller;

import dev.iesfranciscodelosrios.atm_client.Main;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;

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
        // Obtener el valor del campo insertMoney
        String amountText = insertMoney.getText();

        // Verificar si el campo insertMoney no está vacío
        if (!amountText.isEmpty()) {
            try {
                // Convertir el texto del campo insertMoney a double
                double amount = Double.parseDouble(amountText);

                // Realizar la operación de inserción en la cuenta bancaria aquí
                // (Por ejemplo, actualizar el saldo de la cuenta)

                // Mostrar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Inserción de dinero con éxito");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error si el formato del monto es incorrecto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Formato de cantidad incorrecto (Debe ser un número)");
                alert.showAndWait();
            }
        } else {
            // Mostrar un mensaje de error si el campo insertMoney está vacío
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, introduzca una cantidad");
            alert.showAndWait();
        }
    }
    @FXML
    public void withdrawmoney() { // Obtener el valor del campo insertMoney
        String amountText = withdrawMoney.getText();

        // Verificar si el campo insertMoney no está vacío
        if (!amountText.isEmpty()) {
            try {
                // Convertir el texto del campo insertMoney a double
                double amount = Double.parseDouble(amountText);

                // Realizar la operación de inserción en la cuenta bancaria aquí
                // (Por ejemplo, actualizar el saldo de la cuenta)

                // Mostrar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("dinero extraido con éxito");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error si el formato del monto es incorrecto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Formato de cantidad incorrecto (Debe ser un número)");
                alert.showAndWait();
            }
        } else {
            // Mostrar un mensaje de error si el campo insertMoney está vacío
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, introduzca una cantidad");
            alert.showAndWait();
        }
    }

    /**private void buttonExit() throws IOException {
     main.root("login.fxml");
     }*/
    @FXML
    public void initialize() {
        // Al iniciar, configurar la visibilidad de los AnchorPane a false
        panelInsert.setVisible(false);
        panelInsert.setManaged(false);
        panelWithdraw.setVisible(false);
        panelWithdraw.setManaged(false);
    }

}