package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarProdutoScreen extends JFrame {
    public DeletarProdutoScreen() {
        setTitle("Excluir Produto");
        setSize(400, 400);
        setLayout(null);

        JLabel labelNome = new JLabel("Nome do Produto:");
        labelNome.setBounds(50, 50, 100, 30);
        add(labelNome);

        JTextField fieldNome = new JTextField();
        fieldNome.setBounds(150, 50, 200, 30);
        add(fieldNome);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(150, 100, 200, 30);
        add(btnExcluir);

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();

                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                dispose(); // Fecha a janela após a exclusão
            }
        });

        setVisible(true);
    }
}
