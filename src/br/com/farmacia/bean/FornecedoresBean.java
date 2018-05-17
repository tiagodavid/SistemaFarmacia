package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private ArrayList<Fornecedores> itens;
	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itensFiltrados;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	
	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			itens = fDAO.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");

		}

	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.salvar(fornecedores);

			
			itens = fDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedore salvo com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
		}
	}

	

	public void excluir() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.excluir(fornecedores);

			itens = fDAO.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um fornecedor que tenho um produto associado");
		}
	}
	
	
	public void editar() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.editar(fornecedores);

			itens = fDAO.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
		}
	}

}
