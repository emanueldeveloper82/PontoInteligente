package br.com.eps.pontointeligente.api.dtos;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;

public class LancamentoDto {
	
	private Optional<Long> idLancamento = Optional.empty();
	@NotEmpty(message = "O campo Data não pode ser vazio.")
	private String dataLancamento;
	private String tipoLancamento;
	private String descricao;
	private String localizacao;
	private Long funcionarioId;
	
	public LancamentoDto() {}

	public Optional<Long> getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(Optional<Long> idLancamento) {
		this.idLancamento = idLancamento;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
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

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	@Override
	public String toString() {
		return "LancamentoDto [id=" + idLancamento + ", data=" + dataLancamento
				+ ", tipoLancamento=" + tipoLancamento + ", descricao="
				+ descricao + ", localizacao=" + localizacao + ", funcionarioId=" + funcionarioId + "]";
	}

}
