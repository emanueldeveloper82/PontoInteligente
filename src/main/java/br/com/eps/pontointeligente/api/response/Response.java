package br.com.eps.pontointeligente.api.response;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que contém uma estrutura mínima para retornar respostas de sucesso ou erro.
 * 
 * @author Emanuel da Anunciação Silva
 *
 * @param <T>
 */
public class Response<T> {

	private T data;
	private List<String> errors;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
