package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private ArrayList<Produtos> itens;
	private Produtos produtos;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores> comboFornecedores;

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			itens = pDAO.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");

		}

	}

	public void prepararNovo() {
		try {
			produtos = new Produtos();
			
			FornecedoresDAO fDao = new FornecedoresDAO();
			comboFornecedores = fDao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
		
	}
	
	public void novo() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.salvar(produtos);

			
			itens = pDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.excluir(produtos);

			itens = pDAO.listar();
			
			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
		}
	}
	
	public void editar() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.editar(produtos);

			itens = pDAO.listar();
			
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
		}
	}
	
	public void prepararEditar() {
		try {
			produtos = new Produtos();
			
			FornecedoresDAO fDao = new FornecedoresDAO();
			comboFornecedores = fDao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
		
	}

}
