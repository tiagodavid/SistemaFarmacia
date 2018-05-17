package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


public class ProdutoDAOTeste {
	
	@Test
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		Fornecedores f1 = new Fornecedores();

		f1.setCodigo(19L);

		p1.setDescricao("DIPIRONA");
		p1.setPreco(2.99);
		p1.setQuantidade(12L);
		p1.setFornecedores(f1);

		ProdutoDAO produtosDAO = new ProdutoDAO();

		produtosDAO.salvar(p1);

	}
	
	@Test

	public void listar() throws SQLException {
		
		ProdutoDAO produtosDAO = new ProdutoDAO();
		ArrayList<Produtos> lista = produtosDAO.listar();
		
		for(Produtos p : lista) {
			System.out.println("Código do Produto:" + p.getCodigo());
			System.out.println("Descrição do Produto:" + p.getDescricao());
			System.out.println("Valor do Produto:" + p.getPreco());
			System.out.println("Quantidade:" + p.getQuantidade());
			System.out.println("Código do Fornecedor:" + p.getFornecedores().getCodigo());
			System.out.println("Descrição do Fornecedor:" + p.getFornecedores().getDescricao());
		}

	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produtos p1 = new Produtos();

		p1.setCodigo(2l);

		ProdutoDAO produtosDAO = new ProdutoDAO();

		produtosDAO.excluir(p1);

	}
	
	@Test
	@Ignore
	public void editar() throws SQLException {
		Produtos p1 = new Produtos();
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(20l);

		p1.setCodigo(3l);
		p1.setDescricao("ASPIRINA");
		p1.setPreco(5.86);
		p1.setQuantidade(12l);
		p1.setFornecedores(f1);

		ProdutoDAO produtosDAO = new ProdutoDAO();

		produtosDAO.editar(p1);

	}
}
