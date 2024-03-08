package dev.iesfranciscodelosrios.atm_client.Service;


import dev.iesfranciscodelosrios.atm_client.interfaces.iBankAccountService;
import dev.iesfranciscodelosrios.atm_client.interfaces.iTransactionService;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public BankAccount currentAccount; // Objeto con la información de la cuenta actual

    private static BankAccount_Service _instance;
    public Socket socket;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    private BankAccount_Service(){
        try {
            this.socket = new Socket("localhost",8080);
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
            this.inFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean login(BankAccount account) {
        try {
            outToServer.writeObject("login");
            outToServer.writeObject(account.dni);
            outToServer.writeObject(account.Pin);

            this.currentAccount = (BankAccount) inFromServer.readObject();
            System.out.println(this.currentAccount);
            // Esperar respuesta del servidor
            return currentAccount != null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean logout() {
        this.currentAccount = null;
        return true;
    }

    @Override
    public BankAccount register(BankAccount account) {
        try {
            outToServer.writeObject("register");
            outToServer.writeObject(account);
            // Esperar respuesta del servidor
            BankAccount result = (BankAccount) inFromServer.readObject();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deposit(double amount) {
        try {
            outToServer.writeObject("deposit");
            outToServer.writeObject(this.currentAccount);
            outToServer.writeObject(amount);
            // Esperar respuesta del servidor
            String respuesta = (String) inFromServer.readObject();
            if(respuesta.equals("OK")){
                this.currentAccount.balance += amount;
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean withdraw(double amount) {
        try {
            outToServer.writeObject("withdraw");
            outToServer.writeObject(this.currentAccount);
            outToServer.writeObject(amount);
            // Esperar respuesta del servidor
            String respuesta = (String) inFromServer.readObject();
            if(respuesta.equals("OK")){
                this.currentAccount.balance -= amount;
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}
