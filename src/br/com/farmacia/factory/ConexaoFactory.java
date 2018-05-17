package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	private static final String USER = "";
	private static final String PASSWORD = "";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema";
	
	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexão bem sucedida!!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Conexão falhou!!");
		}
	}
	
}
