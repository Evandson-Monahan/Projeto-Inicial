package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
    public Connection connectDB() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/EmpresaLeiloesBD?useSSL=false&useTimezone=true&serverTimezone=UTC";
            String user = "Admin";
            String password = "04abr1994";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conn;
    }
}
