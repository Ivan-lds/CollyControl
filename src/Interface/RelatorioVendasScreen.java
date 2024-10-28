package Interface;

import ControleEstoque.Produto;
import RelatorioProduto.RelatorioVendas;
import RelatorioProduto.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RelatorioVendasScreen extends JPanel {
    private JTextArea relatorioArea;
    private JTextField clienteField, valorField, dataVendaField, pesquisaField;
    private JComboBox<String> produtoComboBox;
    private JButton gerarRelatorioBtn, adicionarVendaBtn, editarVendaBtn, listarExcluirVendasBtn, salvarAlteracoesBtn;
    private List<Venda> vendas;
    private List<Produto> produtos;

    private Venda vendaSelecionada;
    private LocalDate ultimaAtualizacao;
    private BufferedImage backgroundImage; // Para armazenar a imagem de fundo

    public RelatorioVendasScreen(List<Produto> produtos) {
        this.vendas = new ArrayList<>();
        this.produtos = produtos;
        this.ultimaAtualizacao = LocalDate.now(); // Inicializa com a data atual

        try {
            backgroundImage = ImageIO.read(new File("assets//background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 5, 0);

        // Área de texto para exibir o relatório
        relatorioArea = new JTextArea(12, 50);
        relatorioArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(relatorioArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel produtoLabel = new JLabel("Produto:");
        produtoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        produtoComboBox = new JComboBox<>();
        atualizarProdutosComboBox();

        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        clienteField = new JTextField(20);
        clienteField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel valorLabel = new JLabel("Valor da Venda:");
        valorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        valorField = new JTextField(20);
        valorField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel dataVendaLabel = new JLabel("Data da Venda (dd/MM/yyyy):");
        dataVendaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dataVendaField = new JTextField(20);
        dataVendaField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel pesquisaLabel = new JLabel("Pesquisar Venda por Cliente:");
        pesquisaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pesquisaField = new JTextField(20);
        pesquisaField.setFont(new Font("Arial", Font.PLAIN, 16));

        gerarRelatorioBtn = new JButton("Gerar Relatório");
        gerarRelatorioBtn.setFont(new Font("Arial", Font.BOLD, 16));
        adicionarVendaBtn = new JButton("Adicionar Venda");
        adicionarVendaBtn.setFont(new Font("Arial", Font.BOLD, 16));
        editarVendaBtn = new JButton("Editar Venda");
        editarVendaBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(100, 5, 5, 0);
        add(scrollPane, gbc);

        gbc.insets = new Insets(0, 5, 5, 0);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(pesquisaLabel, gbc);
        gbc.gridx = 1;
        add(pesquisaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(produtoLabel, gbc);
        gbc.gridx = 1;
        add(produtoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(clienteLabel, gbc);
        gbc.gridx = 1;
        add(clienteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(valorLabel, gbc);
        gbc.gridx = 1;
        add(valorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(dataVendaLabel, gbc);
        gbc.gridx = 1;
        add(dataVendaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(adicionarVendaBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(editarVendaBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(gerarRelatorioBtn, gbc);

        // Iniciar um Timer para verificar a data a cada 24 horas
        Timer timer = new Timer(1000 * 60 * 60 * 24, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEZerarVendas();
            }
        });
        timer.start();

        // Ações dos botões
        gerarRelatorioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioVendas relatorioVendas = new RelatorioVendas();
                String relatorio = relatorioVendas.gerarRelatorio(vendas);
                relatorioArea.setText(relatorio);
                atualizarRelatorio();
            }
        });

        adicionarVendaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeProduto = (String) produtoComboBox.getSelectedItem();
                    String nomeCliente = clienteField.getText();
                    double valorVenda = Double.parseDouble(valorField.getText());
                    String dataVenda = dataVendaField.getText();

                    Venda.registrarVenda(vendas, produtos, nomeProduto, nomeCliente, valorVenda, dataVenda);
                    JOptionPane.showMessageDialog(RelatorioVendasScreen.this, "Venda registrada com sucesso!");

                    // Limpa os campos de texto após adicionar a venda
                    clienteField.setText("");
                    valorField.setText("");
                    dataVendaField.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RelatorioVendasScreen.this,
                            "Erro ao registrar venda: " + ex.getMessage());
                }
            }
        });

        editarVendaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = pesquisaField.getText();

                // Encontrar as vendas do cliente
                List<Venda> vendasDoCliente = vendas.stream()
                        .filter(v -> v.getCliente().getNome().equals(nomeCliente))
                        .collect(Collectors.toList());

                if (vendasDoCliente.isEmpty()) {
                    JOptionPane.showMessageDialog(RelatorioVendasScreen.this,
                            "Nenhuma venda encontrada para este cliente.");
                    return;
                }

                // Preencher o JComboBox de produtos apenas uma vez
                atualizarProdutosComboBox();

                // Preencher os campos com a primeira venda encontrada
                Venda primeiraVenda = vendasDoCliente.get(0);
                valorField.setText(String.valueOf(primeiraVenda.getValor()));
                clienteField.setText(primeiraVenda.getCliente().getNome());
                produtoComboBox.setSelectedItem(primeiraVenda.getProduto().getNome());

                if (primeiraVenda.getDataVenda() != null) {
                    dataVendaField.setText(
                            primeiraVenda.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    dataVendaField.setText("");
                }

                // Listener para o produtoComboBox
                produtoComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nomeProdutoSelecionado = (String) produtoComboBox.getSelectedItem();

                        // Filtra para encontrar a venda específica do produto selecionado
                        List<Venda> vendasDoProduto = vendas.stream()
                                .filter(v -> v.getProduto().getNome().equals(nomeProdutoSelecionado))
                                .collect(Collectors.toList());

                        if (!vendasDoProduto.isEmpty()) {
                            vendaSelecionada = vendasDoProduto.get(0);
                            valorField.setText(String.valueOf(vendaSelecionada.getValor()));
                            clienteField.setText(vendaSelecionada.getCliente().getNome());

                            if (vendaSelecionada.getDataVenda() != null) {
                                dataVendaField.setText(
                                        vendaSelecionada.getDataVenda()
                                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            } else {
                                dataVendaField.setText("");
                            }
                        } else {
                            vendaSelecionada = null;
                            valorField.setText("");
                            clienteField.setText("");
                            dataVendaField.setText("");
                        }
                    }
                });

                atualizarRelatorio();

                listarExcluirVendasBtn = new JButton("Listar e Excluir Vendas");
                listarExcluirVendasBtn.setFont(new Font("Arial", Font.BOLD, 16));
                salvarAlteracoesBtn = new JButton("Salvar Alterações");
                salvarAlteracoesBtn.setFont(new Font("Arial", Font.BOLD, 16));

                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 2;
                add(listarExcluirVendasBtn, gbc);
                gbc.gridy = 14;
                add(salvarAlteracoesBtn, gbc);
                revalidate();
                repaint();

                // Listener para o botão salvarAlteracoesBtn
                salvarAlteracoesBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (vendaSelecionada != null) {
                            try {
                                // Atualiza a venda selecionada com as novas informações
                                vendaSelecionada.setValor(Double.parseDouble(valorField.getText()));
                                vendaSelecionada.setDataVenda(LocalDate.parse(dataVendaField.getText(),
                                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                JOptionPane.showMessageDialog(RelatorioVendasScreen.this, "Venda editada com sucesso!");

                                // Limpa os campos após a edição
                                clienteField.setText("");
                                valorField.setText("");
                                dataVendaField.setText("");

                                remove(listarExcluirVendasBtn);
                                remove(salvarAlteracoesBtn);
                                revalidate();
                                repaint();

                                atualizarRelatorio();

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(RelatorioVendasScreen.this,
                                        "Erro ao editar venda: " + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(RelatorioVendasScreen.this,
                                    "Nenhuma venda selecionada para editar.");
                        }
                    }
                });

                listarExcluirVendasBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel painelVendas = new JPanel(new GridLayout(vendasDoCliente.size(), 1, 10, 10));

                        // Atualiza a lista de vendas do cliente, caso tenha sido alterada
                        List<Venda> vendasDoClienteAtualizadas = vendas.stream()
                                .filter(v -> v.getCliente().getNome().equals(nomeCliente))
                                .collect(Collectors.toList());

                        for (Venda venda : vendasDoClienteAtualizadas) {
                            JPanel vendaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            JLabel labelVenda = new JLabel(
                                    "Produto: " + venda.getProduto().getNome() + " | Valor: R$" + venda.getValor());

                            JButton btnExcluir = new JButton("Excluir");

                            btnExcluir.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    vendas.remove(venda);
                                    JOptionPane.showMessageDialog(RelatorioVendasScreen.this,
                                            "Venda excluída com sucesso!");
                                    atualizarRelatorio();

                                    // Atualiza a lista de vendas do cliente
                                    painelVendas.removeAll();
                                    for (Venda vendaAtualizada : vendas.stream()
                                            .filter(v -> v.getCliente().getNome().equals(nomeCliente))
                                            .collect(Collectors.toList())) {
                                        JPanel vendaPanelAtualizada = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                        JLabel labelVendaAtualizada = new JLabel(
                                                "Produto: " + vendaAtualizada.getProduto().getNome() + " | Valor: R$"
                                                        + vendaAtualizada.getValor());
                                        vendaPanelAtualizada.add(labelVendaAtualizada);
                                        painelVendas.add(vendaPanelAtualizada);
                                    }
                                    painelVendas.revalidate();
                                    painelVendas.repaint();
                                }
                            });

                            vendaPanel.add(labelVenda);
                            vendaPanel.add(btnExcluir);
                            painelVendas.add(vendaPanel);
                        }

                        // Mostrar as vendas em uma nova janela
                        JOptionPane.showMessageDialog(RelatorioVendasScreen.this, painelVendas,
                                "Lista de Vendas para " + nomeCliente, JOptionPane.PLAIN_MESSAGE);
                    }
                });
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void verificarEZerarVendas() {
        LocalDate hoje = LocalDate.now();
        long diasPassados = ChronoUnit.DAYS.between(ultimaAtualizacao, hoje);

        // Zerar as vendas depois de 30 dias
        if (diasPassados >= 30) {
            vendas.clear();
            ultimaAtualizacao = hoje; // Atualiza a data da última zeragem
            atualizarRelatorio();
        }
    }

    private void atualizarProdutosComboBox() {
        produtoComboBox.removeAllItems();
        for (Produto produto : produtos) {
            produtoComboBox.addItem(produto.getNome());
        }
    }

    public void atualizarProdutos(List<Produto> produtos) {
        produtoComboBox.removeAllItems();
        for (Produto produto : produtos) {
            produtoComboBox.addItem(produto.getNome());
        }
    }

    private void atualizarRelatorio() {
        relatorioArea.setText(""); // Limpa a área de texto antes de atualizar

        relatorioArea.append("Relatório de vendas - Produtos mais vendidos:\n\n");

        // Verifica se há vendas
        if (vendas.isEmpty()) {
            relatorioArea.append("Nenhuma venda registrada para gerar o relatório.");
            return;
        }

        // Mapa para contar as vendas por produto
        Map<String, Long> contagemVendas = vendas.stream()
                .collect(Collectors.groupingBy(venda -> venda.getProduto().getNome(), Collectors.counting()));

        // Ordena os produtos por número de vendas em ordem decrescente
        List<Map.Entry<String, Long>> listaOrdenada = contagemVendas.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());

        for (Map.Entry<String, Long> entry : listaOrdenada) {
            relatorioArea.append("Produto: " + entry.getKey() + " | Vendas: " + entry.getValue() + "\n");
        }
    }
}
