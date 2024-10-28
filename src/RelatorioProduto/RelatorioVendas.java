package RelatorioProduto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioVendas {
    public String gerarRelatorio(List<Venda> vendas) {
        // Contagem de vendas por produto
        Map<String, Integer> contagemVendas = new HashMap<>();
        for (Venda venda : vendas) {
            String nomeProduto = venda.getProduto().getNome();
            contagemVendas.put(nomeProduto, contagemVendas.getOrDefault(nomeProduto, 0) + 1);
        }

        // Ordena a lista de produtos pelo número de vendas (do maior para o menor)
        List<Map.Entry<String, Integer>> produtosOrdenados = new ArrayList<>(contagemVendas.entrySet());
        produtosOrdenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // StringBuilder para armazenar o relatório
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de vendas - Produtos mais vendidos:\n");
        for (Map.Entry<String, Integer> entry : produtosOrdenados) {
            relatorio.append("Produto: ").append(entry.getKey())
                    .append(" | Vendas: ").append(entry.getValue())
                    .append("\n");
        }

        return relatorio.toString();
    }
}
