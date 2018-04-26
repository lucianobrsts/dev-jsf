package br.com.estudo.dev.jsf.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

@SuppressWarnings({ "serial" })
public class ConverterFuncionario implements Converter, Serializable{

	// converte da tela para o objeto 
	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String string) {
		if(string == null || string.equals("Selecione um funcionario"))
			return null;
		return EntityManagerUtil.getEntityManager().find(Funcionario.class, Integer.parseInt(string));
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object obj) {
		if(obj == null)
			return null;
		Funcionario f = (Funcionario) obj;
		return f.getId().toString();
	}

}
