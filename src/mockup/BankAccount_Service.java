package mockup;

import interfaces.iBankAccountService;
import interfaces.iTransactionService;
import model.BankAccount;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public model.BankAccount currentAccount; // Objeto con la información de la cuenta actual

    public BankAccount_Service _instance;

    private BankAccount_Service(){}
    @Override
    public boolean login(model.BankAccount account) {
        this.currentAccount = account;
        this.currentAccount.IBAN = account.IBAN;
        this.currentAccount.dni = "12345678A";
        this.currentAccount.name = "Pepe";
        this.currentAccount.surname = "Perez";
        this.currentAccount.Pin = 1234;
        return true;
    }

    @Override
    public boolean logout(model.BankAccount account) {
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
    public void deposit(BankAccount account, double amount) {
        this.currentAccount.balance += amount;

    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        this.currentAccount.balance -= amount;
    }

    public BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}