package br.com.estudo.dev.jsf.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Grupo;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;
import br.com.estudo.dev.jsf.util.UtilErros;
import br.com.estudo.dev.jsf.util.UtilMensagens;

@SuppressWarnings("unchecked")
public class GrupoDAO {

	private EntityManager em;
	private String ordem = "id";
	private String filtro = "";
	private Integer maximosObjetos = 2;
	private Integer posicaoAtual = 0;
	private Integer totalObjetos = 0;

	public GrupoDAO() {
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Grupo> listar() {
		String where = "";
		if(filtro.length() > 0) {
			if(ordem.equals("id")) {
				try {
					Integer.parseInt(filtro);
					where = " where " + this.ordem + " = '" + this.filtro + "' ";
				} catch (Exception e) {
				}
			} 
			else {
				where = " where upper("+ this.ordem +") like "+ this.filtro.toUpperCase() +"% ";
			}
		}
		
		String jpql = "from Grupo" + where + " order by " + this.ordem;
		totalObjetos = em.createQuery("select id from Grupo " + where + "order by " + this.ordem).getResultList().size();
		return em.createQuery(jpql).setFirstResult(this.posicaoAtual).setMaxResults(this.maximosObjetos).getResultList();
	}

	public void primeiro() {
		this.posicaoAtual = 0;
	}
	
	public void anterior() {
		this.posicaoAtual -= maximosObjetos;
		if(this.posicaoAtual < 0)
			this.posicaoAtual = 0;
	}
	
	public void proximo() {
		if( this.posicaoAtual + this.maximosObjetos < this.totalObjetos )
			this.posicaoAtual += maximosObjetos;
	}
	
	public void ultimo() {
		int resto = this.totalObjetos % this.maximosObjetos;
		if(resto > 0)
			this.posicaoAtual = this.totalObjetos - resto;
		else
			this.posicaoAtual = this.totalObjetos - this.maximosObjetos;
	}
	
	public String getMensagemNavegacao() {
		int ate = this.posicaoAtual + this.maximosObjetos;
		if ( ate > this.getTotalObjetos() ) {
			ate = this.totalObjetos;
		}
		
		return "Listando de " + (this.posicaoAtual + 1) + " até " + ate + " de " + this.totalObjetos + " registros.";
	}
			
	
	public List<Grupo> listarTodos() {
		return em.createQuery("from Grupo order by nome").getResultList();
	}

	public boolean gravar(Grupo obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null)
				em.persist(obj);
			else
				em.merge(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso.");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false)
				em.getTransaction().begin();
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public boolean excluir(Grupo obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso.");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false)
				em.getTransaction().begin();
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover objeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public Grupo localizar(Integer id) {
		return em.find(Grupo.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getMaximosObjetos() {
		return maximosObjetos;
	}

	public void setMaximosObjetos(Integer maximosObjetos) {
		this.maximosObjetos = maximosObjetos;
	}

	public Integer getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(Integer posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public Integer getTotalObjetos() {
		return totalObjetos;
	}

	public void setTotalObjetos(Integer totalObjetos) {
		this.totalObjetos = totalObjetos;
	}
}
