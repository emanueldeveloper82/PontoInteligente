package br.com.eps.pontointeligente.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESA", schema = "PONTO_ELETRONICO")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_ID_EMPRESA_SEQ")
	@SequenceGenerator(name = "EMPRESA_ID_EMPRESA_SEQ",
			sequenceName = "EMPRESA_ID_EMPRESA_SEQ",
			schema = "PONTO_ELETRONICO", allocationSize = 1)
	@Column(name = "ID_EMPRESA")
	private Long id;
	
	@Column(name = "NUM_CNPJ", nullable = false)
	private String cnpj;

	@Column(name = "DATA_CRIACAO", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date dataAtualizacao;
	
	@Column(name = "RAZAO_SOCIAL", nullable = false)
	private String razaoSocial;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

	@Override
	public String toString() {
		return "Empresa [id=" + id +
			   ", razaoSocial=" + razaoSocial + 
			   ", cnpj=" + cnpj +
			   ", dataCriacao=" + dataCriacao +
			   ", dataAtualizacao=" + dataAtualizacao + "]";
	}

}
