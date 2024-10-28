package CobrançaCliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Criar alguns clientes para teste
        Cliente cliente1 = new Cliente("João", "+557196721541", LocalDate.now(), 150); // Pendente hoje
        Cliente cliente2 = new Cliente("Maria", "+557196721542", LocalDate.now().plusDays(2), 200); // Vence em 2 dias

        // Lista de clientes
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        // Verificar os clientes e enviar notificações
        Cobranca controle = new Cobranca();
        controle.verificarClientes(clientes);
    }
}
