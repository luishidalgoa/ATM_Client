package dev.iesfranciscodelosrios.atm_client.interfaces;

import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

public interface iTransactionService {
    /**
     * Depositara una cantidad de dinero en la cuenta del usuario Loggeado
     * @param account
     * @param amount
     */
    public boolean deposit(double amount);

    /**
     * Retirara una cantidad de dinero en la cuenta bancaria del usuario loggeado
     * @param account cuenta bancaria
     * @param amount cantidad a retirar
     */
    public boolean withdraw(double amount);
}
