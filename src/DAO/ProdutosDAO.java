package DAO;

import DTO.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import DAO.conectaDAO;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getValor());
        stmt.setString(3, produto.getStatus());

        stmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
    }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
    String sql = "SELECT id, nome, valor, status FROM produtos";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            produtos.add(produto);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao listar produtos: " + e.getMessage());
    }

    return produtos;
        
        //return listagem;
    }
    
    public boolean venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar produto: " + e.getMessage());
        return false;
    }
}
    
    public ArrayList<Object[]> listarVendas() {
    ArrayList<Object[]> vendas = new ArrayList<>();
    String sql = "SELECT id, nome, preco, status FROM produtos WHERE status = 'Vendido'";
    
    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet rs = prep.executeQuery()) {

        while (rs.next()) {
            vendas.add(new Object[]{
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("preco"),
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        System.out.println("Erro ao listar vendas: " + e.getMessage());
    }
    
    return vendas;
}
}