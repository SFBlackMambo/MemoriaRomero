import javax.swing.*;

public class ConnectionGUI extends JFrame {
    private JTextField nameTextField;

    public ConnectionGUI() {
        setTitle("Memorama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel instructionLabel = new JLabel("Ingresa tu nombre:");
        nameTextField = new JTextField(30);
        JButton connectButton = new JButton("Jugar");

        connectButton.addActionListener(e -> {
            String playerName = nameTextField.getText();
            if (!playerName.isEmpty()) { // Verificar si el nombre no está vacío
                // Lógica de conexión con el nombre de jugador
                // Aquí puedes crear la instancia de MemoramaGUI y pasar playerName como parámetro
                // ...
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre de jugador válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.add(instructionLabel);
        panel.add(nameTextField);
        panel.add(connectButton);

        add(panel);

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionGUI().setVisible(true);
            }
        });
    }
}
