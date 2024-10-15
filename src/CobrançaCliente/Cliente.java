package CobrançaCliente;

import java.time.LocalDate;
import ControleEstoque.WhatsAppNotification;

public class Cliente {
    private String nome;
    private String numeroWhatsapp;
    private LocalDate dataPagamento;

    public Cliente(String nome, String numeroWhatsapp, LocalDate dataPagamento) {
        this.nome = nome;
        this.numeroWhatsapp = numeroWhatsapp;
        this.dataPagamento = dataPagamento;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    // Verifica se o pagamento é devido (hoje)
    public boolean pagamentoPendente() {
        return LocalDate.now().isEqual(dataPagamento);
    }

    // Verifica se o pagamento está próximo (em 3 dias)
    public boolean pagamentoProximo() {
        return LocalDate.now().isAfter(dataPagamento.minusDays(3)) && LocalDate.now().isBefore(dataPagamento);
    }
}
