package br.com.eps.pontointeligente.api.dtos;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;

public class LancamentoDto {
	
	private Optional<Long> id = Optional.empty();
	@NotEmpty(message="O campo Data não pod eser vazio.")
	private String data;
	private String tipoLancamento;
	private String descricao;
	private String localizacao;
	private Long funcionarioId;
	
	public LancamentoDto() {}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
		return "LancamentoDto [id=" + id + ", data=" + data + ", tipoLancamento=" + tipoLancamento + ", descricao="
				+ descricao + ", localizacao=" + localizacao + ", funcionarioId=" + funcionarioId + "]";
	}

}
