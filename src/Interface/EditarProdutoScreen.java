package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarProdutoScreen extends JFrame {
    private JTextField fieldNome;
    private JTextField fieldQuantidade;

    public EditarProdutoScreen() {
        setTitle("Editar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelNome, gbc);

        fieldNome = new JTextField(20);
        gbc.gridx = 1;
        add(fieldNome, gbc);

        JLabel labelQuantidade = new JLabel("Nova Quantidade:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelQuantidade, gbc);

        fieldQuantidade = new JTextField(20);
        gbc.gridx = 1;
        add(fieldQuantidade, gbc);

        JButton btnSalvar = new JButton("Salvar Alterações");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnSalvar, gbc);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                int quantidade;

                try {
                    quantidade = Integer.parseInt(fieldQuantidade.getText());

                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
                    dispose(); // Fecha a janela após a edição
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para a quantidade.");
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarProdutoScreen()); // Executa na Event Dispatch Thread
    }
}
