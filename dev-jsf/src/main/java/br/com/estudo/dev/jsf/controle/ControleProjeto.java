package br.com.estudo.dev.jsf.controle;

 import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.bean.Projeto;
import br.com.estudo.dev.jsf.bean.ProjetoFuncionario;
import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.conversores.ConverterFuncionario;
import br.com.estudo.dev.jsf.conversores.ConverterSetor;
import br.com.estudo.dev.jsf.modelo.FuncionarioDAO;
import br.com.estudo.dev.jsf.modelo.ProjetoDAO;
import br.com.estudo.dev.jsf.modelo.SetorDAO;

@SuppressWarnings({ "serial" })
@ManagedBean(name="controleProjeto")
@SessionScoped
public class ControleProjeto implements Serializable {

	private Projeto objeto;
	private Funcionario funcionario;
	private Setor setor;
	
	private ProjetoDAO dao;
	private FuncionarioDAO daoFuncionario;
	private SetorDAO daoSetor;
	
	private ConverterFuncionario converterFuncionario;
	private ConverterSetor converterSetor;
	
	private Integer cargaHoraria;
	private Boolean gestor;
	private Boolean addFunc = false;
	private Calendar inicioParticipacao;
	private Calendar fimParticipacao;

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
		this.addFunc = false;
		return "form";
	}
	
	public String cancelar() {
		this.addFunc = false;
		this.dao.rollback();
		return "listar";
	}
	
	public String gravar() {
		if(this.dao.gravar(this.objeto)) {
			this.addFunc = false;
			return "listar";
		} else return "form";
	}
	
	public String alterar(Projeto obj) {
		this.objeto = obj;
		this.addFunc = false;
		return "form";
	}
	
	public String excluir(Projeto obj) {
		this.dao.excluir(obj);
		return "listar";
	}
	
	public void removerFuncionario(ProjetoFuncionario obj) {
		this.objeto.removerFuncionario(obj);
	}
	
	public void adicionarFuncionario(ProjetoFuncionario obj) {
		this.addFunc = true;
	}
	
	public void cancelarFuncionario() {
		this.addFunc = false;
	}
	
	public void salvarFuncionario() {
		ProjetoFuncionario obj = new ProjetoFuncionario();
		obj.setCargaHoraria(cargaHoraria);
		obj.setFuncionario(funcionario);
		obj.setInicioParticipacao(inicioParticipacao);
		obj.setFimParticipacao(fimParticipacao);
		obj.setGestor(gestor);
		
		this.objeto.adicionarFuncionario(obj);
		this.addFunc = false;
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

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Boolean getGestor() {
		return gestor;
	}

	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}

	public Boolean getAddFunc() {
		return addFunc;
	}

	public void setAddFunc(Boolean addFunc) {
		this.addFunc = addFunc;
	}

	public Calendar getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(Calendar inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	public Calendar getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(Calendar fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	} 
}
