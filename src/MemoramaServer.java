import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoramaServer {
    private static final int MAX_THREADS = 2; // Número máximo de conexiones simultáneas

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        try {
            int port = 12345; // Puerto a utilizar
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            // Aceptar dos conexiones de clientes
            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado: " + clientSocket1.getInetAddress().getHostAddress());
            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado: " + clientSocket2.getInetAddress().getHostAddress());

            // Crear una instancia de Runnable para manejar la comunicación con cada cliente
            Runnable clientHandler1 = new ClientHandler(clientSocket1, "Jugador 1");
            executor.execute(clientHandler1);
            Runnable clientHandler2 = new ClientHandler(clientSocket2, "Jugador 2");
            executor.execute(clientHandler2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
