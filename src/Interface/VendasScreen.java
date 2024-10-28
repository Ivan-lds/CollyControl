package Interface;

import ControleEstoque.Produto;
import RelatorioProduto.ControleVendas;
import RelatorioProduto.Venda;

import javax.swing.*;

import CobrançaCliente.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VendasScreen extends JPanel {
    private ControleVendas controleVendas;
    private JTextField produtoField, clienteField, valorVendaField, dataVendaField;

    public VendasScreen(List<Produto> produtos) {
        controleVendas = new ControleVendas();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel produtoLabel = new JLabel("Produto:");
        produtoField = new JTextField(15);
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteField = new JTextField(15);
        JLabel valorVendaLabel = new JLabel("Valor da Venda:");
        valorVendaField = new JTextField(15);
        JLabel dataVendaLabel = new JLabel("Data da Venda (dd/MM/yyyy):");
        dataVendaField = new JTextField(15);

        JButton adicionarBtn = new JButton("Adicionar Venda");
        JButton editarBtn = new JButton("Editar Venda");
        JButton excluirBtn = new JButton("Excluir Venda");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(produtoLabel, gbc);
        gbc.gridx = 1;
        add(produtoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(clienteLabel, gbc);
        gbc.gridx = 1;
        add(clienteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(valorVendaLabel, gbc);
        gbc.gridx = 1;
        add(valorVendaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dataVendaLabel, gbc);
        gbc.gridx = 1;
        add(dataVendaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(adicionarBtn, gbc);

        gbc.gridy = 5;
        add(editarBtn, gbc);

        gbc.gridy = 6;
        add(excluirBtn, gbc);

        adicionarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarVenda(produtos);
            }
        });

        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVenda();
            }
        });

        excluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirVenda();
            }
        });
    }

    private void adicionarVenda(List<Produto> produtos) {
        String nomeProduto = produtoField.getText();
        Produto produto = produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto))
                .findFirst().orElse(null);
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            return;
        }

        String nomeCliente = clienteField.getText();
        Cliente cliente = new Cliente(nomeCliente, "", LocalDate.now(), 0);

        double valor = Double.parseDouble(valorVendaField.getText());
        LocalDate data = LocalDate.parse(dataVendaField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Venda venda = new Venda(produto, cliente, valor, data);
        controleVendas.adicionarVenda(venda);

        JOptionPane.showMessageDialog(this, "Venda adicionada com sucesso!");
    }

    private void editarVenda() {
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto da venda para editar:");
        Venda venda = controleVendas.buscarVenda(nomeProduto);
        if (venda != null) {
            produtoField.setText(venda.getProduto().getNome());
            clienteField.setText(venda.getCliente().getNome());
            valorVendaField.setText(String.valueOf(venda.getValor()));
            dataVendaField.setText(venda.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            JButton salvarBtn = new JButton("Salvar Alterações");
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 2;
            add(salvarBtn, gbc);
            revalidate();
            repaint();

            salvarBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    venda.setCliente(clienteField.getText());
                    venda.setValor(Double.parseDouble(valorVendaField.getText()));
                    venda.setDataVenda(
                            LocalDate.parse(dataVendaField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");

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

    private void excluirVenda() {
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto da venda para excluir:");
        boolean sucesso = controleVendas.excluirVenda(nomeProduto);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Venda excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
        }
    }
}
