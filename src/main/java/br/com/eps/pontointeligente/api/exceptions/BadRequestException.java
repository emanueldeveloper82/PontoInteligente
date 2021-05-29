package br.com.eps.pontointeligente.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nomeOperacao;
	private String motivoFalha;
	private Object nomeDado;

	public BadRequestException(String nomeOperacao, String motivoFalha, Object nomeDado) {
		super(String.format("Não foi possível realizar a operação: %s." + "<br>"
						+ "Motivo: %s.<br>" + "Registro: '%s'",
				nomeOperacao, motivoFalha, nomeDado));
		this.nomeOperacao = nomeOperacao;
		this.motivoFalha = motivoFalha;
		this.nomeDado = nomeDado;
	}

	public BadRequestException(String erro) {
		super(erro);
	}
	
	public String getNomeOperacao() {
		return nomeOperacao;
	}

	public String getMotivoFalha() {
		return motivoFalha;
	}

	public Object getNomeDado() {
		return nomeDado;
	}
	
	

}
