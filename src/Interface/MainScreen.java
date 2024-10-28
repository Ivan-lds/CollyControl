package Interface;

import javax.swing.*;
import ControleEstoque.Produto;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private static MainScreen instance;
    private static RelatorioVendasScreen relatorioVendasScreen;

    public MainScreen() {
        instance = this; // Define a instância estática
        setTitle("Sistema de Controle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Produto> produtos = new ArrayList<>();

        // Cria o painel com abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adiciona as telas como abas

        ControleEstoqueScreen controleEstoqueScreen = new ControleEstoqueScreen(produtos);
        tabbedPane.addTab("Controle de Estoque", controleEstoqueScreen);

        CobrancaScreen cobrancaScreen = new CobrancaScreen();
        tabbedPane.addTab("Cobrança de Clientes", cobrancaScreen);

        relatorioVendasScreen = new RelatorioVendasScreen(produtos);
        tabbedPane.addTab("Relatório de Vendas", relatorioVendasScreen);

        // Adiciona o painel com abas ao frame principal
        add(tabbedPane);
        setVisible(true);
    }

    public static MainScreen getInstance() {
        return instance;
    }

    public static RelatorioVendasScreen getRelatorioVendasScreen() {
        return relatorioVendasScreen;
    }
}
