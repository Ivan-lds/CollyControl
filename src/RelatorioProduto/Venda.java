package RelatorioProduto;

import CobrançaCliente.Cliente;
import ControleEstoque.Produto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    private Produto produto;
    private Cliente cliente;
    private double valor;
    private LocalDate dataVenda;

    public Venda(Produto produto, Cliente cliente, double valor, LocalDate dataVenda) {
        this.produto = produto;
        this.cliente = cliente;
        this.valor = valor;
        this.dataVenda = dataVenda;
        produto.incrementarVendas();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente string) {
        this.cliente = string;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public static void registrarVenda(List<Venda> vendas, List<Produto> produtos, String nomeProduto,
            String nomeCliente, double valorVenda, String dataVenda) throws Exception {
        Produto produto = produtos.stream().filter(p -> p.getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (produto == null) {
            throw new Exception("Produto não encontrado.");
        }

        Cliente cliente = new Cliente(nomeCliente, "", LocalDate.now(), 0);
        LocalDate data = LocalDate.parse(dataVenda, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Venda venda = new Venda(produto, cliente, valorVenda, data);
        vendas.add(venda);
    }

    // Função para buscar uma venda pelo nome do produto ou do cliente
    public static Venda buscarVenda(List<Venda> vendas, String nomeProduto, String nomeCliente) {
        for (Venda venda : vendas) {
            if (venda.getProduto().getNome().equalsIgnoreCase(nomeProduto)
                    && venda.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                return venda;
            }
        }
        return null;
    }

    // Função para excluir uma venda pelo nome do produto ou do cliente
    public static boolean excluirVenda(List<Venda> vendas, String nomeProduto, String nomeCliente) {
        Venda venda = buscarVenda(vendas, nomeProduto, nomeCliente);
        if (venda != null) {
            vendas.remove(venda);
            return true;
        }
        return false;
    }

    public static boolean editarVenda(List<Venda> vendas, String nomeProduto, String novoCliente, double novoValor,
            String novaDataVenda) {
        Venda venda = buscarVenda(vendas, nomeProduto, novaDataVenda);
        if (venda != null) {
            venda.setCliente(new Cliente(novoCliente, "", LocalDate.now(), 0));
            venda.setValor(novoValor);
            venda.setDataVenda(LocalDate.parse(novaDataVenda, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return true;
        }
        return false;
    }

    public void setCliente(String text) {
        this.cliente = new Cliente(text, "", LocalDate.now(), 0);
    }
}
