package br.com.estudo.dev.jsf.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Ordem;
import br.com.estudo.dev.jsf.conversores.ConverterOrdem;
import br.com.estudo.dev.jsf.util.UtilErros;
import br.com.estudo.dev.jsf.util.UtilMensagens;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class GenericDAO<T> implements Serializable {

	private Class classe;
	private EntityManager em;
	private String filtro;
	private List<Ordem> listaOrdem = new ArrayList<>();
	private Ordem ordemAtual;
	private int maximoObjetos = 10;
	private int posicao = 0;
	private int totalObjetos = 0;
	private ConverterOrdem converterOrdem;
	
	public void iniciarTransacao() {
		if(em.getTransaction().isActive() == false)
			em.getTransaction().begin();
	}
	
	public void rollback() {
		iniciarTransacao();
		em.getTransaction().rollback();
	}
	
	public void commitTransacao() {
		iniciarTransacao();
		em.getTransaction().commit();
	}
	
	public boolean persist(T objeto) {
		try {
			iniciarTransacao();
			em.persist(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			rollback();
			UtilMensagens.mensagemInformacao("Erro ao persistir o objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean merge(T objeto) {
		try {
			iniciarTransacao();
			em.merge(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			rollback();
			UtilMensagens.mensagemInformacao("Erro ao persistir o objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean remove(T objeto) {
		try {
			iniciarTransacao();
			em.remove(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso");
			return true;
		} catch (Exception e) {
			rollback();
			UtilMensagens.mensagemInformacao("Erro ao remover o objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public List<T> listarTodos(){
		String jpql = "from " + classe.getSimpleName();
		if(ordemAtual != null)
			jpql += "order by " + ordemAtual.getAtributo();
		return em.createQuery(jpql).getResultList();
	}
	
	public String protegeFiltro(String filtro) {
		filtro = filtro.replaceAll("[';-]", "");
		return filtro;
	}
	
	public List<T> listar() {
		String jpql = "from " + classe.getSimpleName();
		String where = "";
		
		if(filtro != null && filtro.length() > 0) {
			filtro = protegeFiltro(filtro);
			if(ordemAtual.getAtributo().equals("id")) {
				try {
					Integer.parseInt(filtro);
					where = " where " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else
				where = " where upper(" + ordemAtual.getAtributo() + ") like '" + filtro.toUpperCase() + "%'";
			
			jpql += where;
			
			if(ordemAtual != null)
				jpql += " order by " + ordemAtual.getAtributo();
		}
		
		totalObjetos = em.createQuery("select id from Grupo " + classe.getSimpleName()).getResultList().size();
		
		if(maximoObjetos == 0)
			maximoObjetos = totalObjetos;
		
		return em.createQuery(jpql).setMaxResults(maximoObjetos).setFirstResult(posicao).getResultList();
	}
	
	public void primeiro() {
		this.posicao = 0;
	}
	
	public void anterior() {
		this.posicao -= maximoObjetos;
		if(this.posicao < 0)
			this.posicao = 0;
	}
	
	public void proximo() {
		if(this.posicao + this.maximoObjetos < this.totalObjetos)
			this.posicao += maximoObjetos;
	}
	
	public void ultimo() {
		int resto = this.totalObjetos % this.maximoObjetos;
		if(resto > 0)
			this.posicao = this.totalObjetos - resto;
		else
			this.posicao = this.totalObjetos - this.maximoObjetos;
	}
	
	public String getMensagemNavegacao() {
		int ate = this.posicao + this.maximoObjetos;
		if ( ate > this.getTotalObjetos() ) {
			ate = this.totalObjetos;
		}
		
		return "Listando de " + (this.posicao + 1) + " até " + ate + " de " + this.totalObjetos + " registros.";
	}
	
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Ordem> getListaOrdem() {
		return listaOrdem;
	}
	public void setListaOrdem(List<Ordem> listaOrdem) {
		this.listaOrdem = listaOrdem;
	}
	public Ordem getOrdemAtual() {
		return ordemAtual;
	}
	public void setOrdemAtual(Ordem ordemAtual) {
		this.ordemAtual = ordemAtual;
	}
	public int getMaximoObjetos() {
		return maximoObjetos;
	}
	public void setMaximoObjetos(int maximoObjetos) {
		this.maximoObjetos = maximoObjetos;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getTotalObjetos() {
		return totalObjetos;
	}
	public void setTotalObjetos(int totalObjetos) {
		this.totalObjetos = totalObjetos;
	}
	public ConverterOrdem getConverterOrdem() {
		return converterOrdem;
	}
	public void setConverterOrdem(ConverterOrdem converterOrdem) {
		this.converterOrdem = converterOrdem;
	}
}
