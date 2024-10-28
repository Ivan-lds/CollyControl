package ControleEstoque;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsAppNotification {
    // SID e Auth Token do Twilio
    public static final String ACCOUNT_SID = "ACf87c2e84f7c92ecfc094697ee96ecd25";
    public static final String AUTH_TOKEN = "330782177f656adbe52561b999df25c8";

    // Função enviar mensagem via WhatsApp
    public static void enviarMensagem(String mensagem, String numeroWhatsApp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            // Criando e enviando a mensagem via API do Twilio
            Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + numeroWhatsApp), // Número de destino
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),      // Número do Twilio
                mensagem                                                      // Conteúdo da mensagem
            ).create();

            // Mostra o SID da mensagem enviada como confirmação
            System.out.println("\nMensagem enviada com sucesso! SID: " + message.getSid());
        } catch (Exception e) {
            // Tratamento de erros
            System.out.println("\nErro ao enviar a mensagem: " + e.getMessage());
        }
    }
}
