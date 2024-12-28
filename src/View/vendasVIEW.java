package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import DAO.ProdutosDAO;

public class vendasVIEW extends JFrame {
    private JTable tabelaVendas;
    private JButton btnVoltar;

    public vendasVIEW() {
        setTitle("Vendas Realizadas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração da tabela
        tabelaVendas = new JTable(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nome do Produto", "Valor", "Status" }
        ));

        JScrollPane scrollPane = new JScrollPane(tabelaVendas);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(btnVoltar);

        add(panel);
    }

    public void listarVendas() {
        
        DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
    model.setNumRows(0); // Limpa a tabela antes de carregar novas informações

    ProdutosDAO produtosDAO = new ProdutosDAO();
    ArrayList<Object[]> vendas = produtosDAO.listarProdutosVendidos();

    for (Object[] venda : vendas) {
        model.addRow(venda); // Adiciona cada venda na tabela
    }

    if (vendas.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nenhuma venda encontrada.");
    }
    }
}