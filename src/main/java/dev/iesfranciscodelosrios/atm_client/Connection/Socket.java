package dev.iesfranciscodelosrios.atm_client.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Socket {
    private static final String SERVER_ADDRESS = "172.16.16.224";
    private static final int SERVER_PORT = 8080;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public Socket() {
        try {
            System.out.println("Conexion preparandose...");
            java.net.Socket socket = new java.net.Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
