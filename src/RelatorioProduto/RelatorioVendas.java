package RelatorioProduto;

import ControleEstoque.Produto;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioVendas {

    public void gerarRelatorio(List<Produto> produtos) {
        // Ordena a lista de produtos com base no número de vendas (do maior para o menor)
        Collections.sort(produtos, new Comparator<Produto>() {
            @Override
            public int compare(Produto p1, Produto p2) {
                return Integer.compare(p2.getVendas(), p1.getVendas()); // Ordem decrescente
            }
        });

        // Exibe todos os produtos em ordem
        System.out.println("Relatório de vendas - Produtos mais vendidos:");
        for (Produto produto : produtos) {
            System.out.println("Produto: " + produto.getNome() + " | Vendas: " + produto.getVendas());
        }

        // Exibe o mais vendido separadamente
        if (!produtos.isEmpty()) {
            Produto maisVendido = produtos.get(0); // O primeiro da lista já será o mais vendido
            System.out.println("\nO produto mais vendido foi: " + maisVendido.getNome() + 
                " com " + maisVendido.getVendas() + " vendas.");
        } else {
            System.out.println("Nenhuma venda foi registrada.");
        }
    }
}
