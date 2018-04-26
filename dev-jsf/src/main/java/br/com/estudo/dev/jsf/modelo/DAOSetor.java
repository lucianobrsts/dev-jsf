package br.com.estudo.dev.jsf.modelo;

import java.io.Serializable;

import br.com.estudo.dev.jsf.bean.Ordem;
import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.conversores.ConverterOrdem;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOSetor<T> extends GenericDAO<T> implements Serializable {

	public DAOSetor() {
		super.setClasse(Setor.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "id"));
		super.getListaOrdem().add(new Ordem("Nome", "nome"));
		super.setOrdemAtual((Ordem) this.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(2);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
}
