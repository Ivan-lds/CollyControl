package RelatorioProduto;

import CobrançaCliente.Cliente;
import ControleEstoque.Produto;

public class Venda {
    private Produto produto;
    private Cliente cliente;
    private double valor;

    public Venda(Produto produto, Cliente cliente, double valor) {
        this.produto = produto;
        this.cliente = cliente;
        this.valor = valor;
        produto.incrementarVendas(); // Incrementa a contagem de vendas do produto
    }

    public Produto getProduto() {
        return produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }
}
