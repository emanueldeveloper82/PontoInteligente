package br.com.eps.pontointeligente.api.entity;

import br.com.eps.pontointeligente.api.enums.PerfilEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FUNCIONARIO", schema = "PONTO_ELETRONICO")
public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONARIO_ID_FUNCIONARIO_SEQ")
	@SequenceGenerator(name = "FUNCIONARIO_ID_FUNCIONARIO_SEQ", sequenceName = "FUNCIONARIO_ID_FUNCIONARIO_SEQ",
			schema = "PONTO_ELETRONICO", allocationSize = 1)
	@Column(name = "ID_FUNCIONARIO")
	private Long idFuncionario;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@Column(name = "NUM_CPF", nullable = false)
	private String numCpf;
	
	@Column(name = "VALOR_HORA", nullable = true)
	private BigDecimal valorHora;
	
	@Column(name = "QTD_HORAS_TRABALHO_DIA", nullable = true)
	private Float qtdHorasTrabalhoDia;
	
	@Column(name = "QTD_HORAS_ALMOCO", nullable = true)
	private Float qtdHorasAlmoco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false)
	private PerfilEnum perfil;
	
	@Column(name = "DATA_CRIACAO", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA_FK")
	private Empresa empresa;
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	
	public Funcionario() {}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNumCpf() {
		return numCpf;
	}

	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}

	public BigDecimal getValorHora() {
		return valorHora;
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}
	
	@Transient
	public Optional<BigDecimal> getValorHoraOpt() {
		return Optional.ofNullable(valorHora);
	}

	public Float getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}
	
	@Transient
	public Optional<Float> getQtdHorasTrabalhoDiaOpt() {
		return Optional.ofNullable(qtdHorasTrabalhoDia);
	}

	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}
	
	@Transient
	public Optional<Float> getQtdHorasAlmocoOpt() {
		return Optional.ofNullable(qtdHorasAlmoco);
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
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
		return "Funcionario [" +
			   " id=" + idFuncionario + 
			   ", nome=" + nome + ", email=" + email + 
			   ", senha=" + senha + ", cpf=" + numCpf + 
			   ", valorHora=" + valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + 
			   ", qtdHorasAlmoco=" + qtdHorasAlmoco + ", perfil=" + perfil +
			   ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao +
			   ", empresa=" + empresa + "]";
	}

}
