import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final String playerName;
    private final int[][] cardMatrix;

    public ClientHandler(Socket clientSocket, String playerName,int[][] cardMatrix) {
        this.clientSocket = clientSocket;
        this.playerName = playerName;
        this.cardMatrix = cardMatrix;
    }

    @Override
    public void run() {
        try {
            // Obtener el flujo de salida para enviar datos al cliente
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            // Enviar la matriz de cartas al cliente
            objectOutputStream.writeObject(cardMatrix);
            objectOutputStream.flush();

            // ...
            // Aquí puedes implementar la lógica adicional para comunicarte con el cliente y manejar el juego de Memorama
            // ...

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
