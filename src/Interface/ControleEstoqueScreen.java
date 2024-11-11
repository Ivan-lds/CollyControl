package Interface;

import ControleEstoque.Estoque;
import ControleEstoque.Produto;
import RelatorioProduto.Venda;
import RelatorioProduto.ControleVendas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ControleEstoqueScreen extends JPanel {
    private Estoque controleEstoque; // Controle de estoque
    private JTextField nomeField, quantidadeField, validadeField, precoField;
    private List<Produto> produtos;
    private ControleVendas controleVendas;
    private BufferedImage backgroundImage; // Para armazenar a imagem de fundo

    public ControleEstoqueScreen(List<Produto> produtos) {
        this.produtos = produtos;
        this.controleVendas = new ControleVendas();
        controleEstoque = new Estoque(produtos);

        try {
            backgroundImage = ImageIO.read(new File("assets//background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nomeLabel = new JLabel("Nome do Produto:");
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeField = new JTextField(20);
        nomeField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantidadeField = new JTextField(20);
        quantidadeField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel validadeLabel = new JLabel("Data de Validade (dd/MM/yyyy):");
        validadeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        validadeField = new JTextField(20);
        validadeField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        precoField = new JTextField(20);
        precoField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton adicionarBtn = new JButton("Adicionar Produto");
        adicionarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton verificarBtn = new JButton("Verificar Produto");
        verificarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton editarBtn = new JButton("Editar Produto");
        editarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton excluirBtn = new JButton("Excluir Produto");
        excluirBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton listarBtn = new JButton("Listar Produtos");
        listarBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.insets = new Insets(50, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);
        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.insets = new Insets(3, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(quantidadeLabel, gbc);
        gbc.gridx = 1;
        add(quantidadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(validadeLabel, gbc);
        gbc.gridx = 1;
        add(validadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(precoLabel, gbc);
        gbc.gridx = 1;
        add(precoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 3);
        add(adicionarBtn, gbc);

        gbc.insets = new Insets(3, 5, 5, 5);

        gbc.gridy = 5;
        add(verificarBtn, gbc);

        gbc.gridy = 6;
        add(editarBtn, gbc);

        gbc.gridy = 7;
        add(excluirBtn, gbc);

        gbc.gridy = 8;
        add(listarBtn, gbc);

        adicionarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        verificarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarProduto();
            }
        });

        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });

        excluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        listarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProdutos();
            }
        });
    }

    private void adicionarProduto() {
        try {
            String nome = nomeField.getText();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            LocalDate validade = LocalDate.parse(validadeField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double preco = Double.parseDouble(precoField.getText());

            controleEstoque.adicionarProduto(nome, quantidade, validade, preco);

            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");

            nomeField.setText("");
            quantidadeField.setText("");
            validadeField.setText("");
            precoField.setText("");

            // Atualiza a lista de produtos no comboBox do RelatorioVendasScreen
            MainScreen.getRelatorioVendasScreen().atualizarProdutos(controleEstoque.getProdutos());

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data de validade inválida! Use o formato dd/MM/yyyy.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade ou preço inválido! Por favor, insira valores numéricos.");
        }
    }

    private void verificarProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para verificar:");
        Produto produto = controleEstoque.buscarProduto(nome);
        if (produto != null) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = produto.getDataValidade().format(formato);
            JOptionPane.showMessageDialog(this, "Produto encontrado:\n" +
                    "Nome: " + produto.getNome() + "\n" +
                    "Quantidade: " + produto.getQuantidade() + "\n" +
                    "Validade: " + dataFormatada + "\n" +
                    "Preço: R$" + produto.getPreco());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
    }

    private void editarProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para editar:");
        Produto produto = controleEstoque.buscarProduto(nome);
        if (produto != null) {
            nomeField.setText(produto.getNome());
            quantidadeField.setText(String.valueOf(produto.getQuantidade()));
            validadeField.setText(produto.getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            precoField.setText(String.valueOf(produto.getPreco()));

            JButton salvarBtn = new JButton("Salvar Alterações");
            salvarBtn.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.gridwidth = 2;
            add(salvarBtn, gbc);
            revalidate();
            repaint();

            salvarBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    produto.setNome(nomeField.getText());
                    produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                    produto.setDataValidade(
                            LocalDate.parse(validadeField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    produto.setPreco(Double.parseDouble(precoField.getText()));
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

                    // Limpa os campos de texto após salvar
                    nomeField.setText("");
                    quantidadeField.setText("");
                    validadeField.setText("");
                    precoField.setText("");

                    remove(salvarBtn);
                    revalidate();
                    repaint();
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
    }

    private void excluirProduto() {
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto para excluir:");

        boolean sucesso = controleEstoque.removerProduto(nomeProduto);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
    }

    private void editarVenda() {
        String nomeVenda = JOptionPane.showInputDialog("Digite o nome do produto da venda para editar:");
        Venda venda = controleVendas.buscarVenda(nomeVenda);
        if (venda != null) {
            JTextField produtoField = new JTextField(venda.getProduto().getNome(), 15);
            JTextField clienteField = new JTextField(venda.getCliente().getNome(), 15);
            JTextField valorVendaField = new JTextField(String.valueOf(venda.getValor()), 15);
            JTextField dataVendaField = new JTextField(
                    venda.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 15);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;

            add(produtoField, gbc);
            gbc.gridy++;
            add(clienteField, gbc);
            gbc.gridy++;
            add(valorVendaField, gbc);
            gbc.gridy++;
            add(dataVendaField, gbc);

            JButton salvarBtn = new JButton("Salvar Alterações");
            gbc.gridy++;
            add(salvarBtn, gbc);

            revalidate();
            repaint();

            salvarBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    venda.setProduto(controleEstoque.buscarProduto(produtoField.getText()));
                    venda.setCliente(clienteField.getText());
                    venda.setValor(Double.parseDouble(valorVendaField.getText()));
                    venda.setDataVenda(
                            LocalDate.parse(dataVendaField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");

                    // Limpa os campos de texto
                    produtoField.setText("");
                    clienteField.setText("");
                    valorVendaField.setText("");
                    dataVendaField.setText("");

                    remove(salvarBtn);
                    revalidate();
                    repaint();
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void excluirVenda() {
        String nomeVenda = JOptionPane.showInputDialog("Digite o nome do produto da venda para excluir:");
        boolean sucesso = controleVendas.excluirVenda(nomeVenda);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Venda excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = controleEstoque.getProdutos();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder lista = new StringBuilder("Produtos no Estoque:\n");
        for (Produto produto : produtos) {
            lista.append("\nNome: ").append(produto.getNome())
                    .append("\nQuantidade: ").append(produto.getQuantidade())
                    .append("\nValidade: ").append(produto.getDataValidade().format(formato))
                    .append("\nPreço: R$").append(produto.getPreco())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, lista.toString());
    }
}
