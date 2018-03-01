package br.com.estudo.dev.jsf.controle;

 import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.estudo.dev.jsf.bean.Grupo;
import br.com.estudo.dev.jsf.modelo.GrupoDAO;

@ManagedBean(name = "controleGrupo")
@SessionScoped
public class ControleGrupo implements Serializable {

	private static final long serialVersionUID = -4324966222074044364L;
	
	private GrupoDAO dao;
	private Grupo objeto;
	
	public ControleGrupo() {
		this.dao = new GrupoDAO();
	}
	
	public String listar() {
		return "privado/grupo/listar?faces-redirect=true";
	}
	
	public String novo() {
		this.objeto = new Grupo();
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
	
	public String alterar(Grupo obj) {
		this.objeto = obj;
		return "listar";
	}
	
	public String excluir(Grupo obj) {
		this.dao.excluir(obj);
		return "listar";
	}
	
	public GrupoDAO getDao() {
		return dao;
	}
	public void setDao(GrupoDAO dao) {
		this.dao = dao;
	}
	public Grupo getObjeto() {
		return objeto;
	}
	public void setObjeto(Grupo objeto) {
		this.objeto = objeto;
	} 
}
