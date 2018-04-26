package br.com.estudo.dev.jsf.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.estudo.dev.jsf.bean.Grupo;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

@SuppressWarnings({ "serial" })
public class ConverterGrupo implements Converter, Serializable{

	// converte da tela para o objeto 
	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String string) {
		if(string == null || string.equals("Selecione um grupo"))
			return null;
		return EntityManagerUtil.getEntityManager().find(Grupo.class, Integer.parseInt(string));
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object obj) {
		if(obj == null)
			return null;
		Grupo o = (Grupo) obj;
		return o.getId().toString();
	}

}
