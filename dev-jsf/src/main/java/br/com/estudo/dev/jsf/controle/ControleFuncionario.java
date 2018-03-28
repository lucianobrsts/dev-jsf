package br.com.estudo.dev.jsf.controle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.estudo.dev.jsf.bean.Funcionario;
import br.com.estudo.dev.jsf.conversores.ConverterGrupo;
import br.com.estudo.dev.jsf.conversores.ConverterSetor;
import br.com.estudo.dev.jsf.modelo.FuncionarioDAO;
import br.com.estudo.dev.jsf.modelo.GrupoDAO;
import br.com.estudo.dev.jsf.modelo.SetorDAO;
import br.com.estudo.dev.jsf.util.UtilErros;
import br.com.estudo.dev.jsf.util.UtilMensagens;

@SuppressWarnings("deprecation")
@ManagedBean(name="controleFuncionario")
@SessionScoped
public class ControleFuncionario implements Serializable{

	private static final long serialVersionUID = -5903801810115235100L;

	private Funcionario objeto;

	private FuncionarioDAO dao;
	private GrupoDAO daoGrupo;
	private SetorDAO daoSetor;
	
	private ConverterGrupo converterGrupo;
	private ConverterSetor converterSetor;
	
	public ControleFuncionario() {
		dao = new FuncionarioDAO();
		daoGrupo = new GrupoDAO();
		daoSetor = new SetorDAO();
		
		converterGrupo = new ConverterGrupo();
		converterSetor = new ConverterSetor();
	}
	
	public String listar() {
		return "privado/funcionario/listar?faces-redirect=true";
	}
	
	public String novo() {
		this.objeto = new Funcionario();
		return "form";
	}
	
	public String cancelar() {
		return "listar";
	}

	public String gravar() {
		if(dao.gravar(objeto))
			return "listar";
		return "form";
	}
	
	public String alterar(Funcionario func) {
		this.objeto = func;
		return "form";
	}
	
	public String excluir(Funcionario func) {
		dao.excluir(func);
		return "listar";
	}
	
	public void enviarFoto(FileUploadEvent event) {
		try {
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
			this.objeto.setFoto(foto);
			UtilMensagens.mensagemInformacao("Arquivo enviado com sucesso!");
			event.getFile().getFileName();
		} catch (Exception e) {
			UtilMensagens.mensagemErro("Erro ao enviar arquivo: " + UtilErros.getMensagemErro(e));
		}
	}

	public StreamedContent getImagemDinamica() {
		String strid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_imagem");
		if(strid != null) {
			Integer id =  Integer.parseInt(strid);
			Funcionario obj = dao.localizar(id);
			return obj.getImagem();
		}
		return new DefaultStreamedContent();
	}
	
	public Funcionario getObjeto() {
		return objeto;
	}
	public void setObjeto(Funcionario funcionario) {
		this.objeto = funcionario;
	}
	public FuncionarioDAO getDao() {
		return dao;
	}
	public void setDao(FuncionarioDAO daoFuncionario) {
		this.dao = daoFuncionario;
	}
	public GrupoDAO getDaoGrupo() {
		return daoGrupo;
	}
	public void setDaoGrupo(GrupoDAO daoGrupo) {
		this.daoGrupo = daoGrupo;
	}
	public SetorDAO getDaoSetor() {
		return daoSetor;
	}
	public void setDaoSetor(SetorDAO daoSetor) {
		this.daoSetor = daoSetor;
	}
	public ConverterGrupo getConverterGrupo() {
		return converterGrupo;
	}
	public void setConverterGrupo(ConverterGrupo converterGrupo) {
		this.converterGrupo = converterGrupo;
	}
	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}
	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}
}
