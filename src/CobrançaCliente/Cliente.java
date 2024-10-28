package CobrançaCliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String numeroWhatsapp;
    private LocalDate dataPagamento;
    private float valorPagamento;
    private static List<Cliente> clientes = new ArrayList<>();

    public Cliente(String nome, String numeroWhatsapp, LocalDate dataPagamento, float valorPagamento) {
        this.nome = nome;
        this.numeroWhatsapp = numeroWhatsapp;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public float getValorPagamento() {
        return valorPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroWhatsapp(String numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setValorPagamento(float valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    // Verifica se o pagamento é (hoje)
    public boolean pagamentoPendente() {
        return LocalDate.now().isEqual(dataPagamento);
    }

    // Verifica se o pagamento está próximo (em 3 dias)
    public boolean pagamentoProximo() {
        return LocalDate.now().isAfter(dataPagamento.minusDays(3)) && LocalDate.now().isBefore(dataPagamento);
    }

    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public static Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public static boolean excluirCliente(String nome) {
        Cliente cliente = buscarClientePorNome(nome);
        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }
}
