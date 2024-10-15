package CobrançaCliente;

import java.time.LocalDate;
import java.util.List;
import ControleEstoque.WhatsAppNotification;

public class Cobranca {
    public void verificarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.pagamentoPendente()) {
                // Enviar notificação de pagamento pendente via WhatsApp
                String mensagem = "Olá " + cliente.getNome() + ", o prazo de pagamento vence hoje!";
                WhatsAppNotification.enviarMensagem(mensagem, cliente.getNumeroWhatsapp());
            } else if (cliente.pagamentoProximo()) {
                // Enviar notificação de pagamento próximo via WhatsApp
                String mensagem = "Olá " + cliente.getNome() + ", o prazo de pagamento vence em breve! Data: " + cliente.getDataPagamento();
                WhatsAppNotification.enviarMensagem(mensagem, cliente.getNumeroWhatsapp());
            }
        }
    }
}

