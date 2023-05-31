import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoramaServer {
    private static final int MAX_THREADS = 2; // Número máximo de conexiones simultáneas
    private  static  int ROWS= 4, COLS=5;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        try {
            int port = 12345; // Puerto a utilizar
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            int[][] cardMatrix = generateCardMatrix(); // Crear la matriz de cartas

            // Aceptar dos conexiones de clientes
            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado: " + clientSocket1.getInetAddress().getHostAddress());
            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado: " + clientSocket2.getInetAddress().getHostAddress());

// Obtener los nombres de los jugadores desde los clientes
            ObjectInputStream inputStream1 = new ObjectInputStream(clientSocket1.getInputStream());
            String playerName1 = (String) inputStream1.readObject();
            ObjectInputStream inputStream2 = new ObjectInputStream(clientSocket2.getInputStream());
            String playerName2 = (String) inputStream2.readObject();

// Crear una instancia de Runnable para manejar la comunicación con cada cliente
            Runnable clientHandler1 = new ClientHandler(clientSocket1, playerName1, playerName2, cardMatrix);
            executor.execute(clientHandler1);
            Runnable clientHandler2 = new ClientHandler(clientSocket2, playerName2, playerName1, cardMatrix);
            executor.execute(clientHandler2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    private static int[][] generateCardMatrix() {
        int[][] cardMatrix = new int[ROWS][COLS];
        int totalCards = ROWS * COLS;
        int[] cards = new int[totalCards];

        // Generar valores de las cartas en pares
        for (int i = 0; i < totalCards / 2; i++) {
            cards[i * 2] = i + 1;
            cards[i * 2 + 1] = i + 1;
        }

        // Barajar los valores de las cartas
        Random random = new Random();
        for (int i = totalCards - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        // Asignar los valores barajados a la matriz de cartas
        int cardIndex = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cardMatrix[i][j] = cards[cardIndex];
                cardIndex++;
            }
        }

        return cardMatrix;
    }

}
