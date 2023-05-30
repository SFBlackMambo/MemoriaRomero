import javax.swing.JFrame;

public class MemoramaGUI extends JFrame {
    // Aquí puedes agregar variables y componentes de la interfaz

    public MemoramaGUI() {
        // Configuración de la ventana
        setTitle("Memorama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Aquí puedes agregar la inicialización de componentes y la configuración de diseño

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemoramaGUI().setVisible(true);
            }
        });
    }
}
