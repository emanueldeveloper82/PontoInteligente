package br.com.eps.pontointeligente.api.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eps.pontointeligente.api.enums.TipoLancamentoEnum;

@Entity
@Table(name = "LANCAMENTO", schema = "PONTO_ELETRONICO")
public class Lancamento implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LANCAMENTO_ID_LANCAMENTO_SEQ")
	@SequenceGenerator(name="LANCAMENTO_ID_LANCAMENTO_SEQ", sequenceName="LANCAMENTO_ID_LANCAMENTO_SEQ", schema="PONTO_ELETRONICO", allocationSize=1)  	
	@Column(name="ID_LANCAMENTO")
	private Long idLancamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_LANCAMENTO", nullable = false)
	private Date dataLancamento;
	
	@Column(name="DESCRICAO", nullable = true)
	private String descricao;
	
	@Column(name="LOCALIZACAO", nullable = true)
	private String localizacao;
	
	@Column(name="DATA_CRIACAO", nullable = false)
	private Date dataCriacao;
	
	@Column(name="DATA_ATUALIZACAO", nullable = false)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_LANCAMENTO", nullable = false)
	private TipoLancamentoEnum tipoLancamentoEnum;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FUNCIONARIO_FK")
	private Funcionario funcionario;
	
	
	public Lancamento() {}


	public Long getIdLancamento() {
		return idLancamento;
	}


	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}


	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getLocalizacao() {
		return localizacao;
	}


	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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


	public TipoLancamentoEnum getTipoLancamentoEnum() {
		return tipoLancamentoEnum;
	}


	public void setTipoLancamentoEnum(TipoLancamentoEnum tipoLancamentoEnum) {
		this.tipoLancamentoEnum = tipoLancamentoEnum;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;		
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
		return "Lancamento ["+
			   " id=" + idLancamento + ", data=" + dataLancamento + ", descricao=" + descricao + 
			   ", localizacao=" + localizacao + ", dataCriacao=" + dataCriacao + 
			   ", dataAtualizacao=" + dataAtualizacao + ", tipo=" + tipoLancamentoEnum + 
			   ", funcionario=" + funcionario + "]";
	}

}
