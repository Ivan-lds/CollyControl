package ControleEstoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        Produto produto1 = new Produto("Whey Protein", 10, LocalDate.of(2024, 11, 3), 150.00);  // Está pra vencer
        Produto produto2 = new Produto("Creatina", 15, LocalDate.of(2024, 12, 15), 80.00);
        Produto produto3 = new Produto("BCAA", 2, LocalDate.of(2024, 11, 3), 50.00);  // Está pra vencer e com estoque baixo
        Produto produto4 = new Produto("Glutamina", 5, LocalDate.of(2025, 1, 10), 120.00);
        Produto produto5 = new Produto("Multivitamínico", 8, LocalDate.of(2025, 2, 5), 70.00);

        List<Produto> produtos = Arrays.asList(produto1, produto2, produto3, produto4, produto5);

        Estoque controleEstoque = new Estoque(produtos);

        controleEstoque.verificarProdutos("+5571983167357"); 
    }
}
