package ControleEstoque;

import java.time.LocalDate;

public class Produto {
    private String nome;
    private int quantidade;
    private LocalDate dataValidade;
    private double preco;
    private int vendas; // Contador de vendas

    public Produto(String nome, int quantidade, LocalDate dataValidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.preco = preco;
        this.vendas = 0; // Inicializa com 0 vendas
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public double getPreco() {
        return preco;
    }

    public int getVendas() {
        return vendas; // Retorna o número de vendas
    }

    public void incrementarVendas() {
        this.vendas++; // Incrementa as vendas em 1
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean estaVencendo() {
        return LocalDate.now().isAfter(dataValidade.minusDays(30));  // 30 dias antes da validade
    }

    public boolean estoqueBaixo() {
        return quantidade <= 5; // Avisa quando o estoque está em 5 unidades ou menos
}
}
