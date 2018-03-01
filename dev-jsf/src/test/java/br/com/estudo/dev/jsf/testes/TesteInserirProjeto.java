package br.com.estudo.dev.jsf.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.bean.Projeto;
import br.com.estudo.dev.jsf.bean.ProjetoFuncionario;
import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

public class TesteInserirProjeto {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		Setor s = em.find(Setor.class, 1);
		Funcionario f = em.find(Funcionario.class, 1);
		
		Projeto p = new Projeto();
		p.setAtivo(true);
		p.setDescricao("Meu novo projeto com JSF");
		p.setFim(Calendar.getInstance());
		p.setInicio(Calendar.getInstance());
		p.setNome("Sistema de funcionarios");
		p.setSetor(s);
		
		ProjetoFuncionario pf = new ProjetoFuncionario();
		pf.setCargaHoraria(100);
		pf.setFimParticipacao(Calendar.getInstance());
		pf.setInicioParticipacao(Calendar.getInstance());
		pf.setGestor(true);
		pf.setFuncionario(f);
		
		p.adicionarFuncionario(pf);		
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("Inserção realizada com sucesso!");

	}

}
