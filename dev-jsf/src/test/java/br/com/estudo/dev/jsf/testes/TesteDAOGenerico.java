package br.com.estudo.dev.jsf.testes;

import java.util.List;

import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.modelo.DAOSetor;

public class TesteDAOGenerico {

	public static void main(String[] args) {
		DAOSetor<Setor> dao = new DAOSetor<>();
		List<Setor> lista = dao.listar();
		for (Setor o : lista) {
			System.out.println("Código: " + o.getId() + " - Nome: " + o.getNome());
		}
	}
}
