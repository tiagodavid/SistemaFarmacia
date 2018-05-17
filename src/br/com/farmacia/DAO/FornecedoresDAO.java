package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	
	public void salvar(Fornecedores fornecedores) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, fornecedores.getDescricao());
		comando.executeUpdate();
	
	}
	
	public void excluir(Fornecedores fornecedores) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, fornecedores.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Fornecedores fornecedores) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, fornecedores.getDescricao());
		comando.setLong(2, fornecedores.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedores buscaPorCodigo(Fornecedores fornecedores) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, fornecedores.getCodigo());
		ResultSet resultSet = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultSet.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultSet.getLong("codigo"));
			retorno.setDescricao(resultSet.getString("descricao"));
		}
		
		return retorno;
		
	}
	
	public ArrayList<Fornecedores> buscaPorDescricao(Fornecedores fornecedores) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao  = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + fornecedores.getDescricao() + "%");
		ResultSet resultSet = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultSet.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultSet.getLong("codigo"));
			item.setDescricao(resultSet.getString("descricao"));
			lista.add(item);
		}
		return lista;
	}
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY codigo ASC");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultSet = comando.executeQuery();
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		while(resultSet.next()) {
			Fornecedores fornecedores = new Fornecedores();
			fornecedores.setCodigo(resultSet.getLong("codigo"));
			fornecedores.setDescricao(resultSet.getString("descricao"));
			lista.add(fornecedores);
		}
		return lista;
	}

}
