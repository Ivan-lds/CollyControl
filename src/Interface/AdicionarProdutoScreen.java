package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarProdutoScreen extends JFrame {
    public AdicionarProdutoScreen() {
        setTitle("Adicionar Produto");
        setSize(400, 400);
        setLayout(null);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(50, 50, 100, 30);
        add(labelNome);

        JTextField fieldNome = new JTextField();
        fieldNome.setBounds(150, 50, 200, 30);
        add(fieldNome);

        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setBounds(50, 100, 100, 30);
        add(labelQuantidade);

        JTextField fieldQuantidade = new JTextField();
        fieldQuantidade.setBounds(150, 100, 200, 30);
        add(fieldQuantidade);

        JLabel labelPreco = new JLabel("Preço:");
        labelPreco.setBounds(50, 150, 100, 30);
        add(labelPreco);

        JTextField fieldPreco = new JTextField();
        fieldPreco.setBounds(150, 150, 200, 30);
        add(fieldPreco);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(150, 200, 200, 30);
        add(btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                int quantidade = Integer.parseInt(fieldQuantidade.getText());
                double preco = Double.parseDouble(fieldPreco.getText());

                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                dispose();
            }
        });

        setVisible(true);
    }
}
