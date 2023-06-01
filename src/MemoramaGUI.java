import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MemoramaGUI extends JFrame {

    private static final int ROWS = 4;
    private static final int COLS = 5;
    private static final String IMAGES_PATH = "img/";
    private int[][] cardMatrix;
    private ObjectInput inputStream;


    public MemoramaGUI(String playerName) {
        this.cardMatrix = cardMatrix;

        setBounds(100, 100, 900, 700);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setTitle("Memorama");
        getContentPane().setBackground(new Color(61, 124, 9)); // Establecer el color de fondo a verde

        // Crear el panel que contendrá la cuadrícula de cartas
        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS));


        // Establecer la conexión con el servidor y recibir la matriz de cartas inicial
        connectToServer();
        receiveCardMatrixFromServer();

        // Recorrer la cuadrícula de cartas
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Obtener el valor de la carta en la posición correspondiente de la matriz
                int cardValue = cardMatrix[ROWS][COLS];
                String cardValueString = String.valueOf(cardValue);


                // Crear una etiqueta y cargar la imagen de la carta
                JLabel cardLabel = new JLabel();
                try {
                    String fileName = cardValueString + ".png";
                    Image cardImage = ImageIO.read(new File(IMAGES_PATH + fileName));
                    cardLabel.setIcon(new ImageIcon(cardImage));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Agregar la etiqueta al panel de la cuadrícula
                gridPanel.add(cardLabel);
            }
        }

        // Agregar el panel de la cuadrícula a la ventana principal
        getContentPane().add(gridPanel, BorderLayout.CENTER);

        // Mostrar la ventana
        setVisible(true);
    }


    public void connectToServer() {
        try {
            String serverIP = "127.0.0.1"; // Dirección IP del servidor
            int serverPort = 12345; // Puerto del servidor

            Socket socket = new Socket(serverIP, serverPort);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Realizar operaciones de comunicación con el servidor
            // ...

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void receiveCardMatrixFromServer() {
        try {
            Object receivedObject = inputStream.readObject();
            if (receivedObject instanceof String[][]) {
                cardMatrix = (int[][]) receivedObject;
            } else {
                // Manejar el caso en que el objeto recibido no sea de tipo String[][]
                System.out.println("El objeto recibido no es de tipo String[][]");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
