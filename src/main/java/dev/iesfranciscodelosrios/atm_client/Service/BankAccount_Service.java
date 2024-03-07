package dev.iesfranciscodelosrios.atm_client.Service;


import dev.iesfranciscodelosrios.atm_client.interfaces.iBankAccountService;
import dev.iesfranciscodelosrios.atm_client.interfaces.iTransactionService;
import dev.iesfranciscodelosrios.atm_client.model.BankAccount;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BankAccount_Service implements iBankAccountService, iTransactionService {
    public dev.iesfranciscodelosrios.atm_client.model.BankAccount currentAccount; // Objeto con la información de la cuenta actual

    public BankAccount_Service _instance;
    public Socket socket;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    private BankAccount_Service(){
        this.socket = new Socket();
        try {
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
            outToServer.writeObject(account.IBAN);
            outToServer.writeObject(account.Pin);
            // Esperar respuesta del servidor
            return (boolean) inFromServer.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean logout(BankAccount account) {
        try {
            outToServer.writeObject("logout");
            // Esperar respuesta del servidor
            return (boolean) inFromServer.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BankAccount register(BankAccount account) {
        return null;
    }

    @Override
    public boolean deposit(BankAccount account, double amount) {
        try {
            outToServer.writeObject("deposit");
            outToServer.writeObject(account);
            outToServer.writeObject(amount);
            // Esperar respuesta del servidor
            String respuesta = (String) inFromServer.readObject();
            return respuesta.equals("OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean withdraw(BankAccount account, double amount) {
        try {
            outToServer.writeObject("withdraw");
            outToServer.writeObject(account);
            outToServer.writeObject(amount);
            // Esperar respuesta del servidor
            String respuesta = (String) inFromServer.readObject();
            return respuesta.equals("OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public BankAccount_Service getInstance(){
        if(_instance == null){
            _instance = new BankAccount_Service();
        }
        return _instance;
    }
}
