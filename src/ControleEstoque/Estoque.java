package ControleEstoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    // Construtor que recebe a lista de produtos
    public Estoque(List<Produto> produtos) {
        this.produtos = produtos;
    }

    // Função adicionar produto
    public void adicionarProduto(String nome, int quantidade, LocalDate dataValidade, double preco) {
        Produto novoProduto = new Produto(nome, quantidade, dataValidade, preco);
        produtos.add(novoProduto);
        System.out.println("Produto " + nome + " adicionado com sucesso!");
    }

    // Função verificar e enviar notificações de validade e estoque
    public void verificarProdutos(String numeroWhatsApp) {
        for (Produto produto : produtos) {
            if (produto.estaVencendo()) {
                String mensagem = "Atenção! O produto " + produto.getNome() + " está prestes a vencer em " + produto.getDataValidade() + ". Verifique o estoque.";
                WhatsAppNotification.enviarMensagem(mensagem, numeroWhatsApp);
            }

            if (produto.estoqueBaixo()) {
                String mensagem = "Atenção! O produto " + produto.getNome() + " está com o estoque baixo: " + produto.getQuantidade() + " unidades restantes.";
                WhatsAppNotification.enviarMensagem(mensagem, numeroWhatsApp);
            }
        }
    }

    // Função editar produto
    public void editarProduto(String nome, int novaQuantidade, LocalDate novaDataValidade, double novoPreco) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                produto.setQuantidade(novaQuantidade);
                produto.setDataValidade(novaDataValidade);
                produto.setPreco(novoPreco);
                System.out.println("Produto " + nome + " editado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado!");
    }

    // Função excluir produto
    public void excluirProduto(String nome) {
        produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nome));
        System.out.println("Produto " + nome + " excluído com sucesso!");
    }

    // Função listar os produtos no estoque
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getNome() + 
                                   " | Quantidade: " + produto.getQuantidade() +
                                   " | Validade: " + produto.getDataValidade() +
                                   " | Preço: R$ " + produto.getPreco());
            }
        }
    }
}
