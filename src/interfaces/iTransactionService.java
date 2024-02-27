package interfaces;

import Service.BankAccount_Service;
import model.BankAccount;

public interface iTransactionService {
    /**
     * Depositara una cantidad de dinero en la cuenta del usuario Loggeado
     * @param account
     * @param amount
     */
    public void deposit(BankAccount account, double amount);

    /**
     * Retirara una cantidad de dinero en la cuenta bancaria del usuario loggeado
     * @param account cuenta bancaria
     * @param amount cantidad a retirar
     */
    public void withdraw(BankAccount account, double amount);
}
