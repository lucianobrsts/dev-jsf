package br.com.estudo.dev.jsf.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PROJETO_FUNCIONARIO")
public class ProjetoFuncionario implements Serializable {

	private static final long serialVersionUID = 7003627403113293102L;

	@Id
	@Column(name = "ID_PRO_FUNC")
	@SequenceGenerator(name="SEQ_PROJ_FUNC",sequenceName="GEN_PRO_FUNC_ID", allocationSize=1 )
	@GeneratedValue(generator="SEQ_PROJ_FUNC", strategy=GenerationType.SEQUENCE)	
	private Integer id;
	
	@Column(name = "CARGA_HORARIA", nullable=false)
	@NotNull(message = "A carga horária deve ser informada")	
	private Integer cargaHoraria;
	
	@Column(name = "GESTOR", nullable=false)
	@NotNull(message = "O gestor deve ser informado")
	private Boolean gestor;
	
	@NotNull(message = "A data inicio da participação deve ser informada")	
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_PARTICIPACAO", nullable=false)
	private Calendar inicioParticipacao;
	
	@NotNull(message = "A data fim da participação deve ser informada")	
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM_PARTICIPACAO", nullable=false)
	private Calendar fimParticipacao;
	
	@NotNull(message="O funcionario deve ser informado")
	@ManyToOne
	@JoinColumn(name = "FUNCIONARIO", referencedColumnName="ID_FUNCIONARIO", nullable=false)
	private Funcionario funcionario;
	
	@NotNull(message="O projeto deve ser informado")
	@ManyToOne
	@JoinColumn(name = "PROJETO", referencedColumnName="ID_PROJETO", nullable=false)
	private Projeto projeto;
	
	public ProjetoFuncionario() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Boolean getGestor() {
		return gestor;
	}
	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}
	public Calendar getInicioParticipacao() {
		return inicioParticipacao;
	}
	public void setInicioParticipacao(Calendar inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}
	public Calendar getFimParticipacao() {
		return fimParticipacao;
	}
	public void setFimParticipacao(Calendar fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
		ProjetoFuncionario other = (ProjetoFuncionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
