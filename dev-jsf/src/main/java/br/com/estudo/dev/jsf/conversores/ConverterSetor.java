package br.com.estudo.dev.jsf.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.estudo.dev.jsf.bean.Setor;
import br.com.estudo.dev.jsf.jpa.EntityManagerUtil;

@SuppressWarnings({ "serial", "rawtypes" })
public class ConverterSetor implements Converter, Serializable{

	// converte da tela para o objeto 
	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String string) {
		if(string == null || string.equals("Selecione um setor"))
			return null;
		return EntityManagerUtil.getEntityManager().find(Setor.class, Integer.parseInt(string));
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object obj) {
		if(obj == null)
			return null;
		Setor o = (Setor) obj;
		return o.getId().toString();
	}

}
