package br.com.estudo.dev.jsf.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable{

	private static final long serialVersionUID = -9162384700438085455L;

	@Id
	@Column(name = "ID_FUNCIONARIO")
	@SequenceGenerator(name = "SEQ_FUNCIONARIO", sequenceName = "SEQ_FUNCIONARIO_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUNCIONARIO")
	private Integer id;
	
	@Column(name = "NOME", length = 50, nullable = false)
	@NotEmpty(message = "O nome não pode ser nulo")
	@Length(max = 50, message = "O nome não pode ultrapassar {max} caracteres")
	private String nome;
	
	@NotEmpty(message = "O CPF deve ser informado")
	@CPF(message = "Informe um CPF válido")
	@Column(name = "CPF", length=14, nullable=false, unique=true)
	private String cpf;
	
	@NotEmpty(message = "O email deve ser informado")
	@Email(message = "Informe um email válido")
	@Column(name = "EMAIL", length=40, nullable=false)
	private String email;
	
	@NotNull(message = "O salário deve ser informado")	
	@Column(name = "SALARIO", nullable=false, columnDefinition="numeric(10,2)")
	private Double salario;
	
	@NotNull(message = "A data de nascimento deve ser informada")	
	@Temporal(TemporalType.DATE)
	@Column(name = "NASCIMENTO", nullable=false)
	private Calendar nascimento;
	
	@NotNull(message = "O campo ativo deve ser informada")	
	@Column(name = "ATIVO", nullable=false)
	private boolean ativo;
	
	@Column(name = "FOTO")
	@Lob
	private byte[] foto;
	
	@NotEmpty(message = "O nome do usuario deve ser informado")
	@Length(max=20, message="O nome de usuario não pode ultrapassar {max} caracteres")
	@Column(name = "USUARIO", length=20, nullable=false, unique=true)
	private String nomeUsuario;
	
	@NotEmpty(message = "A senha deve ser informado")
	@Length(max=10, message="A senha não pode ultrapassar {max} caracteres")
	@Column(name = "SENHA", length=10, nullable=false, unique=true)
	private String senha;
	
	@NotNull(message="O grupo deve ser informado")
	@ManyToOne
	@JoinColumn(name = "GRUPO", referencedColumnName="ID_GRUPO", nullable=false)
	private Grupo grupo;
	
	@NotNull(message="O setor deve ser informado")
	@ManyToOne
	@JoinColumn(name = "SETOR", referencedColumnName="ID_SETOR", nullable=false)
	private Setor setor;
	
	@Transient
	private StreamedContent imagem;
	
	public Funcionario() {}
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Calendar getNascimento() {
		return nascimento;
	}
	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	public StreamedContent getImagem() {
		if(this.getFoto() != null) 
			return new DefaultStreamedContent(new ByteArrayInputStream(this.getFoto()), "");
		return new DefaultStreamedContent();
	}
	
	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Funcionario [nome = " + this.nome + " "
					+ "cpf = " + this.cpf + " "
					+ "usuario = " + this.nomeUsuario + " "
					+ "email = " + this.email + "]";
	}

}
