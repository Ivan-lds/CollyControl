package Interface;

import javax.swing.*;

public class ListarProdutosScreen extends JFrame {
    public ListarProdutosScreen() {
        setTitle("Listar Produtos");
        setSize(400, 400);
        setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 50, 300, 250);
        add(textArea);

        setVisible(true);
    }
}
