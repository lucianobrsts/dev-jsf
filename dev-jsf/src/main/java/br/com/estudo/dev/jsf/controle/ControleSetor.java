package br.com.estudo.dev.jsf.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.modelo.DAOSetor;

@SuppressWarnings({ "serial"})
@ManagedBean(name="controleSetor")
@SessionScoped
public class ControleSetor implements Serializable{

	private DAOSetor<Setor> dao;
	private Setor objeto;
	
	public String listar(){
		return "privado/setor/listar?faces-redirect=true";
	}
	
	public String novo() {
		this.objeto = new Setor();
		return "form";
	}
	
	public String cancelar() {
		return "listar";
	}
	
	public String gravar() {
		boolean gravou = false;
		if(objeto.getId() == null)
			gravou = dao.persist(objeto);
		else gravou = dao.merge(objeto);
		
		if(gravou)
			return "listar";
		else return "form";
	}
	
	public String alterar(Setor obj) {
		this.objeto = obj;
		return "form";
	}
	
	public String excluir(Setor obj) {
		dao.remove(obj);
		return "listar";
	}

	public DAOSetor<Setor> getDao() {
		return dao;
	}

	public void setDao(DAOSetor<Setor> dao) {
		this.dao = dao;
	}

	public Setor getObjeto() {
		return objeto;
	}

	public void setObjeto(Setor objeto) {
		this.objeto = objeto;
	}
}
