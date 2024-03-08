package dev.iesfranciscodelosrios.atm_client.mockup;

import dev.iesfranciscodelosrios.atm_client.interfaces.iBankAccountService;
import dev.iesfranciscodelosrios.atm_client.interfaces.iTransactionService;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public dev.iesfranciscodelosrios.atm_client.model.BankAccount currentAccount; // Objeto con la información de la cuenta actual

    public static BankAccount_Service _instance;

    private BankAccount_Service(){}
    @Override
    public boolean login(dev.iesfranciscodelosrios.atm_client.model.BankAccount account) {
        this.currentAccount = account;
        this.currentAccount.IBAN = account.IBAN;
        this.currentAccount.dni = "12345678A";
        this.currentAccount.name = "Pepe";
        this.currentAccount.surname = "Perez";
        this.currentAccount.Pin = 1234;
        return true;
    }

    @Override
    public boolean logout() {
        this.currentAccount = null;
        return true;
    }

    @Override
    public BankAccount register(BankAccount account) {
        this.currentAccount = account;
        this.currentAccount.IBAN = 123456789;
        return this.currentAccount;
    }

    @Override
    public boolean deposit(double amount) {
        this.currentAccount.balance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (this.currentAccount.balance < amount) {
            return false;
        }
        this.currentAccount.balance -= amount;
        return true;
    }

    public static BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}