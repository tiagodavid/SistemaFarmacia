package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	
	public void salvar(Produtos produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setDouble(3, produto.getPreco());
		comando.setLong(4, produto.getFornecedores().getCodigo());
		comando.executeUpdate();
	
	}
	
	public ArrayList<Produtos> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultSet = comando.executeQuery();
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		while(resultSet.next()) {
			Fornecedores fornecedores = new Fornecedores();
			fornecedores.setCodigo(resultSet.getLong("f.codigo"));
			fornecedores.setDescricao(resultSet.getString("f.descricao"));
			
			Produtos produtos = new Produtos();
			produtos.setCodigo(resultSet.getLong("p.codigo"));
			produtos.setDescricao(resultSet.getString("p.descricao"));
			produtos.setQuantidade(resultSet.getLong("p.quantidade"));
			produtos.setPreco(resultSet.getDouble("p.preco"));
			produtos.setFornecedores(fornecedores);
			lista.add(produtos);
		}
		return lista;
	}
	
	public void excluir(Produtos produtos) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, produtos.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Produtos produtos) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, produtos.getDescricao());
		comando.setDouble(2, produtos.getPreco());
		comando.setLong(3, produtos.getQuantidade());
		comando.setLong(4, produtos.getFornecedores().getCodigo());
		comando.setLong(5, produtos.getCodigo());
		comando.executeUpdate();
	}
	
}
