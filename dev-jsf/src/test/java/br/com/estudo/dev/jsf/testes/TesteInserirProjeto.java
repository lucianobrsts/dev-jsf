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
		
		Setor setor = em.find(Setor.class, 1);
		Funcionario func = em.find(Funcionario.class, 2);
		
		Projeto projeto = new Projeto();
		projeto.setAtivo(true);
		projeto.setDescricao("Meu novo projeto com JSF");
		projeto.setFim(Calendar.getInstance());
		projeto.setInicio(Calendar.getInstance());
		projeto.setNome("Sistema de funcionarios");
		projeto.setSetor(setor);
		
		ProjetoFuncionario pf = new ProjetoFuncionario();
		pf.setCargaHoraria(100);
		pf.setFimParticipacao(Calendar.getInstance());
		pf.setFuncionario(func);
		pf.setGestor(true);
		pf.setInicioParticipacao(Calendar.getInstance());
		
		projeto.adicionarFuncionario(pf);
		
		em.getTransaction().begin();
		em.persist(projeto);
		em.getTransaction().commit();
		System.out.println("Inserção realizada com sucesso!");

	}

}
