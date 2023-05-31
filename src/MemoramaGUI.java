import javax.swing.JFrame;
import java.awt.Color;

public class MemoramaGUI extends JFrame {


    public MemoramaGUI(String playerName) {
        setTitle("Memorama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GREEN); // Establecer el color de fondo a verde

        setSize(800, 600); // Establecer el tamaño de la ventana a 800x600 píxeles
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true); // Hacer visible la ventana
    }


}
