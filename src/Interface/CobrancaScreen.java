package Interface;

import CobrançaCliente.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CobrancaScreen extends JPanel {
    private JTextField nomeField, whatsappField, dataPagamentoField, valorPagamentoField;
    private BufferedImage backgroundImage; // Armazenar a imagem de fundo

    public CobrancaScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        try {
            backgroundImage = ImageIO.read(new File("assets//background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeField = new JTextField(20);
        nomeField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel whatsappLabel = new JLabel("Número do WhatsApp:");
        whatsappLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        whatsappField = new JTextField(20);
        whatsappField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel dataPagamentoLabel = new JLabel("Data de Pagamento (dd/MM/yyyy):");
        dataPagamentoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dataPagamentoField = new JTextField(20);
        dataPagamentoField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel valorPagamentoLabel = new JLabel("Valor do Pagamento:");
        valorPagamentoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        valorPagamentoField = new JTextField(20);
        valorPagamentoField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton adicionarBtn = new JButton("Adicionar Cliente");
        adicionarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton listarBtn = new JButton("Listar Clientes");
        listarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton editarBtn = new JButton("Editar Cliente");
        editarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton excluirBtn = new JButton("Excluir Cliente");
        excluirBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton pesquisarBtn = new JButton("Pesquisar Cliente");
        pesquisarBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.insets = new Insets(50, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);
        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.insets = new Insets(3, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(whatsappLabel, gbc);
        gbc.gridx = 1;
        add(whatsappField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(dataPagamentoLabel, gbc);
        gbc.gridx = 1;
        add(dataPagamentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(valorPagamentoLabel, gbc);
        gbc.gridx = 1;
        add(valorPagamentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 3);
        add(adicionarBtn, gbc);

        gbc.insets = new Insets(3, 5, 5, 5);

        gbc.gridy = 5;
        add(listarBtn, gbc);

        gbc.gridy = 6;
        add(editarBtn, gbc);

        gbc.gridy = 7;
        add(excluirBtn, gbc);

        gbc.gridy = 8;
        add(pesquisarBtn, gbc);

        adicionarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });

        listarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        excluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        pesquisarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarCliente();
            }
        });
    }

    private void adicionarCliente() {
        try {
            String nome = nomeField.getText();
            String numeroWhatsapp = whatsappField.getText();
            LocalDate dataPagamento = LocalDate.parse(dataPagamentoField.getText(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            float valorPagamento = Float.parseFloat(valorPagamentoField.getText());

            Cliente cliente = new Cliente(nome, numeroWhatsapp, dataPagamento, valorPagamento);
            Cliente.adicionarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");

            nomeField.setText("");
            whatsappField.setText("");
            dataPagamentoField.setText("");
            valorPagamentoField.setText("");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data de pagamento inválida! Use o formato dd/MM/yyyy.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor de pagamento inválido! Por favor, insira um valor numérico.");
        }
    }

    private void listarClientes() {
        StringBuilder lista = new StringBuilder("Clientes Cadastrados:\n");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Cliente cliente : Cliente.listarClientes()) {
            lista.append("\nNome: ").append(cliente.getNome())
                    .append("\nWhatsApp: ").append(cliente.getNumeroWhatsapp())
                    .append("\nData de Pagamento: ").append(cliente.getDataPagamento().format(formato))
                    .append("\nValor do Pagamento: R$").append(cliente.getValorPagamento())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    private void editarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente para editar:");
        Cliente cliente = Cliente.buscarClientePorNome(nome);
        if (cliente != null) {
            // Preenche os campos de texto com as informações do cliente
            nomeField.setText(cliente.getNome());
            whatsappField.setText(cliente.getNumeroWhatsapp());
            dataPagamentoField.setText(cliente.getDataPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            valorPagamentoField.setText(String.valueOf(cliente.getValorPagamento()));

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
                    cliente.setNome(nomeField.getText());
                    cliente.setNumeroWhatsapp(whatsappField.getText());
                    cliente.setDataPagamento(
                            LocalDate.parse(dataPagamentoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    cliente.setValorPagamento(Float.parseFloat(valorPagamentoField.getText()));
                    JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");

                    // Limpa os campos de texto após salvar
                    nomeField.setText("");
                    whatsappField.setText("");
                    dataPagamentoField.setText("");
                    valorPagamentoField.setText("");

                    remove(salvarBtn);
                    revalidate();
                    repaint();
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void excluirCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente para excluir:");
        boolean sucesso = Cliente.excluirCliente(nome);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }
    }

    private void pesquisarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente para pesquisar:");
        Cliente cliente = Cliente.buscarClientePorNome(nome);
        if (cliente != null) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String detalhes = "Nome: " + cliente.getNome() +
                    "\nWhatsApp: " + cliente.getNumeroWhatsapp() +
                    "\nData de Pagamento: " + cliente.getDataPagamento().format(formato) +
                    "\nValor do Pagamento: R$" + cliente.getValorPagamento();
            JOptionPane.showMessageDialog(this, detalhes);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }
    }
}
