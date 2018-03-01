package br.com.estudo.dev.jsf.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.bean.Grupo;
import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

public class TesteInserirFuncionario {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		Grupo g = em.find(Grupo.class, 1);
		Setor s = em.find(Setor.class, 1);
		
		Funcionario f = new Funcionario();
		f.setNome("Francisco");
		f.setCpf("111.111.111-11");
		f.setEmail("chico@gmail.com");
		f.setSalario(2000.00);
		f.setNascimento(Calendar.getInstance());
		f.setAtivo(true);
		f.setNomeUsuario("chico");
		f.setSenha("123456");
		f.setGrupo(g);
		f.setSetor(s);
		
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		System.out.println("Inserção realizada com sucesso!");
	}
}
