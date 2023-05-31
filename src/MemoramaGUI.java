import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoramaGUI extends JFrame {

    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 4;
    private static final String IMAGES_PATH = "img/";

    public MemoramaGUI(String playerName) {
        setBounds(100, 100, 905, 660);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setTitle("Memorama");
        getContentPane().setBackground(new Color(61, 124, 9)); // Establecer el color de fondo a verde

        // Crear el panel que contendrá la cuadrícula de cartas
        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));

        // Obtener una lista de nombres de archivo de cartas aleatorias
        List<String> cardFileNames = getRandomCardFileNames();

        // Recorrer la cuadrícula de cartas
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                // Obtener el nombre de archivo correspondiente a la carta
                String fileName = cardFileNames.get(row * GRID_COLS + col);

                // Crear una etiqueta y cargar la imagen de la carta
                JLabel cardLabel = new JLabel();
                try {
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

    private List<String> getRandomCardFileNames() {
        List<String> cardFileNames = new ArrayList<>();

        // Agregar todas las combinaciones de nombres de archivo posibles a la lista
        for (int i = 1; i <= 13; i++) {
            cardFileNames.add("C" + i + ".png");
            cardFileNames.add("P" + i + ".png");
            cardFileNames.add("T" + i + ".png");
            cardFileNames.add("R" + i + ".png");
        }

        // Mezclar la lista de nombres de archivo para obtener un orden aleatorio
        Collections.shuffle(cardFileNames);

        return cardFileNames;
    }
}
