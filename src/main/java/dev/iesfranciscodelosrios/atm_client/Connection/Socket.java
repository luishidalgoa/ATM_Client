package dev.iesfranciscodelosrios.atm_client.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Socket {
    // Dirección IP del servidor
    private static final String SERVER_ADDRESS = "172.16.16.224";
    // Puerto del servidor
    private static final int SERVER_PORT = 8080;
    // Flujo de salida hacia el servidor
    private static ObjectOutputStream out;
    // Flujo de entrada desde el servidor
    private static ObjectInputStream in;

    // Constructor de la clase
    public Socket() {
        try {
            // Imprime un mensaje indicando que la conexión se está preparando
            System.out.println("Conexion preparandose...");
            // Crea un nuevo socket y establece la conexión con el servidor
            java.net.Socket socket = new java.net.Socket(SERVER_ADDRESS, SERVER_PORT);
            // Inicializa el flujo de salida hacia el servidor
            out = new ObjectOutputStream(socket.getOutputStream());
            // Inicializa el flujo de entrada desde el servidor
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // Manejo de excepciones en caso de error de E/S
            e.printStackTrace();
        }
    }
}
