package br.com.estudo.dev.jsf.testes;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Grupo;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

public class TesteInserirGrupo {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		Grupo g = new Grupo();
		g.setNome("Gestores");
		g.setNome("Alunos");
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		System.out.println("Inserção realizada com sucesso!");
	}
}