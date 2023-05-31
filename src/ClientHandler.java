import java.io.*;
import java.net.Socket;
import java.util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final String playerName;
    private final String otherPlayerName;
    private final int[][] cardMatrix;


    public ClientHandler(Socket clientSocket, String playerName, String otherPlayerName, int[][] cardMatrix) {
        this.clientSocket = clientSocket;
        this.playerName = playerName;
        this.otherPlayerName = otherPlayerName;
        this.cardMatrix = cardMatrix;
    }

    @Override
    public void run() {
        try {
            // Envío del nombre del otro jugador al cliente
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(otherPlayerName);
            outputStream.flush();

            // Recepción del nombre del otro jugador desde el cliente
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            String receivedOtherPlayerName = (String) inputStream.readObject();

            System.out.println("Jugador 1: " + playerName);
            System.out.println("Jugador 2: " + receivedOtherPlayerName);


                    // Aquí puedes continuar con la lógica del juego y la comunicación con los clientes

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}