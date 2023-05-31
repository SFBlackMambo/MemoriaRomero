import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Lógica de comunicación con el cliente
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Mensaje recibido del cliente: " + clientMessage);

                // Lógica de procesamiento y respuesta al cliente
                String serverResponse = processMessage(clientMessage);

                // Enviar la respuesta al cliente
                writer.println(serverResponse);
            }
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

    private String processMessage(String message) {
        // Lógica de procesamiento del mensaje recibido del cliente
        // Aquí puedes implementar la lógica del juego de Memorama

        // Retorna la respuesta del servidor al cliente
        return "Respuesta del servidor";
    }
}
