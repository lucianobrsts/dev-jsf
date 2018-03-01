package br.com.estudo.dev.jsf.testes;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

public class TesteInserirSetor {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		Setor s1 = new Setor();
		s1.setNome("Administrativo");
		Setor s2 = new Setor();
		s2.setNome("Operacional");
		em.getTransaction().begin();
		em.persist(s1);
		em.persist(s2);
		em.getTransaction().commit();
		System.out.println("Inserção realizada com sucesso!");
	}
}
