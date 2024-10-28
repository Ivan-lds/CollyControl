package Interface;

import javax.swing.*;

public class ChecarProdutosScreen extends JFrame {
    public ChecarProdutosScreen() {
        setTitle("Verificar Produtos");
        setSize(400, 400);
        setLayout(null);

        JLabel labelVerificacao = new JLabel("Verificando produtos...");
        labelVerificacao.setBounds(50, 50, 300, 30);
        add(labelVerificacao);

        setVisible(true);
    }
}
