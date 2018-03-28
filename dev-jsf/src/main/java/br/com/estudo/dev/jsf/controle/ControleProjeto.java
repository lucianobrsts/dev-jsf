package br.com.estudo.dev.jsf.controle;

 import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.bean.Projeto;
import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.conversores.ConverterFuncionario;
import br.com.estudo.dev.jsf.conversores.ConverterSetor;
import br.com.estudo.dev.jsf.modelo.FuncionarioDAO;
import br.com.estudo.dev.jsf.modelo.ProjetoDAO;
import br.com.estudo.dev.jsf.modelo.SetorDAO;

@SuppressWarnings("deprecation")
@ManagedBean(name="controleProjeto")
@SessionScoped
public class ControleProjeto implements Serializable {

	private static final long serialVersionUID = -4324966222074044364L;
	
	private Projeto objeto;
	private Funcionario funcionario;
	private Setor setor;
	
	private ProjetoDAO dao;
	private FuncionarioDAO daoFuncionario;
	private SetorDAO daoSetor;
	
	private ConverterFuncionario converterFuncionario;
	private ConverterSetor converterSetor;

	public ControleProjeto() {
		this.dao = new ProjetoDAO();
		this.daoFuncionario = new FuncionarioDAO();
		this.converterFuncionario = new ConverterFuncionario();
		this.converterSetor = new ConverterSetor();
	}
	
	public String listar(){
		return "privado/projeto/listar?faces-redirect=true";
	}
	
	public String novo() {
		this.objeto = new Projeto();
		return "form";
	}
	
	public String cancelar() {
		return "listar";
	}
	
	public String gravar() {
		if(this.dao.gravar(this.objeto))
			return "listar";
		else return "form";
	}
	
	public String alterar(Projeto obj) {
		this.objeto = obj;
		return "form";
	}
	
	public String excluir(Projeto obj) {
		this.dao.excluir(obj);
		return "listar";
	}
	
	// Get and Set
	public ProjetoDAO getDao() {
		return dao;
	}
	public void setDao(ProjetoDAO dao) {
		this.dao = dao;
	}
	public Projeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Projeto objeto) {
		this.objeto = objeto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioDAO getDaoFuncionario() {
		return daoFuncionario;
	}

	public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}

	public ConverterFuncionario getConverterFuncionario() {
		return converterFuncionario;
	}

	public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
		this.converterFuncionario = converterFuncionario;
	}

	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}

	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public SetorDAO getDaoSetor() {
		return daoSetor;
	}

	public void setDaoSetor(SetorDAO daoSetor) {
		this.daoSetor = daoSetor;
	} 
}
