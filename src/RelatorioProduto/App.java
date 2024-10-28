package RelatorioProduto;

import java.time.LocalDate;
import java.util.List;

import CobrançaCliente.Cliente;
import ControleEstoque.Produto;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Produto produto1 = new Produto("Whey Protein", 10, LocalDate.now().plusMonths(12), 150.00);
        Produto produto2 = new Produto("Creatina", 15, LocalDate.now().plusMonths(6), 80.00);
        Produto produto3 = new Produto("BCAA", 20, LocalDate.now().plusMonths(9), 50.00);

        Cliente cliente1 = new Cliente("João", "7598855230", LocalDate.now(), 200);

        Venda venda1 = new Venda(produto1, cliente1, 150.00, LocalDate.now());
        Venda venda2 = new Venda(produto1, cliente1, 150.00, LocalDate.now());
        Venda venda3 = new Venda(produto2, cliente1, 80.00, LocalDate.now());
        Venda venda4 = new Venda(produto3, cliente1, 50.00, LocalDate.now());

        // Lista de vendas
        List<Venda> vendas = new ArrayList<>(Arrays.asList(venda1, venda2, venda3, venda4));

        // Lista de produtos
        List<Produto> produtos = Arrays.asList(produto1, produto2, produto3);

        RelatorioVendas relatorio = new RelatorioVendas();
        String relatorioTexto = relatorio.gerarRelatorio(vendas);

        System.out.println(relatorioTexto);
    }
}
