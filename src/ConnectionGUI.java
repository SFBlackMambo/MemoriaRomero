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
            if (!playerName.isEmpty()) { // Verificar nombre no vacío
                // Lógica de conexión con el nombre de jugador
                dispose(); // Cerrar la ventana de conexión

                // Crear e iniciar la ventana de MemoramaGUI
                MemoramaGUI memoramaGUI = new MemoramaGUI(playerName);
                memoramaGUI.setVisible(true);
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
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionGUI().setVisible(true);
            }
        });
    }
}
