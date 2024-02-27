package dev.iesfranciscodelosrios.atm_client.Service;

import dev.iesfranciscodelosrios.atm_client.interfaces.iBankAccountService;
import dev.iesfranciscodelosrios.atm_client.interfaces.iTransactionService;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public dev.iesfranciscodelosrios.atm_client.model.BankAccount currentAccount; // Objeto con la información de la cuenta actual

    public BankAccount_Service _instance;

    private BankAccount_Service(){}
    @Override
    public boolean login(dev.iesfranciscodelosrios.atm_client.model.BankAccount account) {
        return false;
    }

    @Override
    public boolean logout(dev.iesfranciscodelosrios.atm_client.model.BankAccount account) {
        return false;
    }

    @Override
    public BankAccount register(BankAccount account) {
        return null;
    }

    @Override
    public boolean deposit(BankAccount account, double amount) {
        return false;
    }

    @Override
    public boolean withdraw(BankAccount account, double amount) {
        return false;
    }

    public BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}
