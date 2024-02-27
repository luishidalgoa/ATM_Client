package dev.iesfranciscodelosrios.atm_client.interfaces;

import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

public interface iBankAccountService {
    /**
     * Este metodo enviara los datos de la cuenta al servidor para que se verifique si el usuario existe y si la contraseña es correcta
     * @param account cuenta bancaria existente
     * @return true si el usuario se loggeo correctamente
     */
    public boolean login(BankAccount account);

    /**
     * Este metodo setea el valor de la cuenta actual a null
     * @param account cuenta bancaria
     * @return true si el usuario se deslogeo correctamente
     */
    public boolean logout(BankAccount account);

    /**
     * Le enviara los datos rellenados del formulario de registro a la aplicacion servidor para que se registre el usuario
     * @param account objeto cuenta bancaria con los datos basicos para registrarse
     * @return objeto cuenta bancaria con los datos completos como el identificador
     */
    public BankAccount register(BankAccount account);
}
