package Service;

import interfaces.iBankAccountService;
import interfaces.iTransactionService;
import model.BankAccount;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public model.BankAccount currentAccount; // Objeto con la información de la cuenta actual

    public BankAccount_Service _instance;

    private BankAccount_Service(){}
    @Override
    public boolean login(model.BankAccount account) {
        return false;
    }

    @Override
    public boolean logout(model.BankAccount account) {
        return false;
    }

    @Override
    public BankAccount register(BankAccount account) {
        return null;
    }

    @Override
    public void deposit(BankAccount account, double amount) {

    }

    @Override
    public void withdraw(BankAccount account, double amount) {

    }

    public BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}
