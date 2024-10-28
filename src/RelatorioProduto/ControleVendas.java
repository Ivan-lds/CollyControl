package RelatorioProduto;

import java.util.ArrayList;
import java.util.List;

public class ControleVendas {
    private List<Venda> vendas;

    public ControleVendas() {
        vendas = new ArrayList<>();
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
    }

    public Venda buscarVenda(String nomeProduto) {
        for (Venda venda : vendas) {
            if (venda.getProduto().getNome().equalsIgnoreCase(nomeProduto)) {
                return venda;
            }
        }
        return null;
    }

    public boolean excluirVenda(String nomeProduto) {
        Venda venda = buscarVenda(nomeProduto);
        if (venda != null) {
            vendas.remove(venda);
            return true;
        }
        return false;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
}
