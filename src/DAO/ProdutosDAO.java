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
        
        
        conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
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
}