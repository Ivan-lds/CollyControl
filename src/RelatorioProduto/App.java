package RelatorioProduto;

import java.time.LocalDate;
import java.util.List;

import CobrançaCliente.Cliente;
import ControleEstoque.Produto;
import java.util.Arrays;


public class App {
    public static void main(String[] args) {
        // Criação de produtos
        Produto produto1 = new Produto("Whey Protein", 10, LocalDate.now().plusDays(30), 150.00);
        Produto produto2 = new Produto("Creatina", 15, LocalDate.now().plusDays(60), 80.00);
        Produto produto3 = new Produto("BCAA", 20, LocalDate.now().plusDays(90), 50.00);
        
        // Criação de cliente
        Cliente cliente1 = new Cliente("João", "+557196721541", LocalDate.now().plusDays(5));
        
        // Simulação de vendas
        Venda venda1 = new Venda(produto1, cliente1, 150.00);
        Venda venda2 = new Venda(produto1, cliente1, 150.00); // Mais uma venda de Whey Protein
        Venda venda3 = new Venda(produto2, cliente1, 80.00);
        Venda venda4 = new Venda(produto3, cliente1, 50.00); // Venda de BCAA

        // Lista de produtos
        List<Produto> produtos = Arrays.asList(produto1, produto2, produto3);
        
        // Gerando relatório
        RelatorioVendas relatorio = new RelatorioVendas();
        relatorio.gerarRelatorio(produtos);
    }
}
