import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final String playerName;

    public ClientHandler(Socket clientSocket, String playerName) {
        this.clientSocket = clientSocket;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            // Obtener el flujo de salida para enviar datos al cliente
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Generar la matriz de cartas
            int[][] cardsMatrix = generateCardsMatrix();

            // Enviar la matriz de cartas al cliente
            objectOutputStream.writeObject(cardsMatrix);
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

    private int[][] generateCardsMatrix() {
        int[][] cardsMatrix = new int[4][5];
        List<Integer> cardNumbers = new ArrayList<>();

        // Generar una lista de números del 1 al 52
        for (int i = 1; i <= 52; i++) {
            cardNumbers.add(i);
        }

        // Mezclar la lista de números
        Collections.shuffle(cardNumbers);

        // Asignar los números a la matriz de cartas
        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (index >= cardNumbers.size()) {

                    index = 0;
                }
                cardsMatrix[row][col] = cardNumbers.get(index);
                index++;
            }
        }

        return cardsMatrix;
    }
}
