package ControleEstoque;

import java.time.LocalDate;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarProduto(String nome, int quantidade, LocalDate dataValidade, double preco) {
        Produto novoProduto = new Produto(nome, quantidade, dataValidade, preco);
        produtos.add(novoProduto);
        System.out.println("Produto " + nome + " adicionado com sucesso!");
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto; // Retorna o produto encontrado
            }
        }
        return null;
    }

    // Função verificar e enviar notificações de validade e estoque
    public void verificarProdutos(String numeroWhatsApp) {
        for (Produto produto : produtos) {
            if (produto.estaVencendo()) {
                String mensagem = "Atenção! O produto " + produto.getNome() + " está prestes a vencer em "
                        + produto.getDataValidade() + ". Verifique o estoque.";
                enviarMensagemWhatsApp(mensagem, numeroWhatsApp);
            }

            if (produto.estoqueBaixo()) {
                String mensagem = "Atenção! O produto " + produto.getNome() + " está com o estoque baixo: "
                        + produto.getQuantidade() + " unidades restantes.";
                enviarMensagemWhatsApp(mensagem, numeroWhatsApp);
            }
        }
    }

    // Função para enviar mensagem via WhatsApp
    void enviarMensagemWhatsApp(String mensagem, String numeroWhatsApp) {
        try {
            WhatsAppNotification.enviarMensagem(mensagem, numeroWhatsApp);
            System.out.println("Mensagem enviada com sucesso: " + mensagem);

            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Erro ao enviar a mensagem: " + e.getMessage());
        }
    }

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

    public boolean excluirProduto(String nome) {
        produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nome));
        System.out.println("Produto " + nome + " excluído com sucesso!");
        return true;
    }

    public boolean removerProduto(String nome) {
        Produto produtoParaRemover = buscarProduto(nome); // Busca o produto pelo nome
        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover); // Remove o produto da lista
            System.out.println("Produto " + nome + " removido com sucesso!");
            return true;
        } else {
            System.out.println("Produto não encontrado!");
            return false;
        }
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
