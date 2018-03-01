package br.com.estudo.dev.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "PROJETO")
public class Projeto implements Serializable{

	private static final long serialVersionUID = -6961376403554846150L;

	@Id
	@Column(name = "ID_PROJETO")
	@SequenceGenerator(name = "SEQ_PRO", sequenceName = "GEN_PRO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRO")
	private Integer id;
	
	@Column(name = "NOME", length = 50, nullable = false)
	@NotEmpty (message = "O nome não pode ser nulo")
	@Length(max = 50, message = "O nome não pode ultrapassar {max} caracteres")
	private String nome;
	
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "DESCRICAO", length = 50, nullable = false)
	@NotEmpty(message = "A descrição não pode ser nula")
	@Lob
	private String descricao;
	
	@NotNull(message = "A data inicial deve ser informada")	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INICIO", nullable=false)
	private Calendar inicio;
	
	@NotNull(message = "A data final deve ser informada")	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FIM", nullable=false)
	private Calendar fim;
	
	@NotNull(message = "O campo ativo deve ser informada")	
	@Column(name = "ATIVO", nullable=false)
	private Boolean ativo;
	
	@NotNull(message="O setor deve ser informado")
	@ManyToOne
	@JoinColumn(name = "SETOR", referencedColumnName="ID_SETOR", nullable=false)
	private Setor setor;
	
	@OneToMany(mappedBy="projeto", cascade= {CascadeType.ALL}, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<ProjetoFuncionario> funcionarios = new ArrayList<>();
	
	public Projeto() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getInicio() {
		return inicio;
	}
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFim() {
		return fim;
	}
	public void setFim(Calendar fim) {
		this.fim = fim;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<ProjetoFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<ProjetoFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	public void adicionarFuncionario(ProjetoFuncionario obj) {
		obj.setProjeto(this);
		this.funcionarios.add(obj);
	}
	
	public void removerFuncionario(ProjetoFuncionario obj) {
		if(this.funcionarios.contains(obj))
			this.funcionarios.remove(obj);
	}
	
	public void removerTodosFuncionarios() {
		this.funcionarios.clear();
	}
	
	
}
