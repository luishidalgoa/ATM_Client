package interfaces;

import Service.BankAccount;

public interface iTransactionService {
    /**
     * Depositara una cantidad de dinero en la cuenta del usuario Loggeado
     * @param account
     * @param amount
     */
    public void deposit(BankAccount account, double amount);
}
