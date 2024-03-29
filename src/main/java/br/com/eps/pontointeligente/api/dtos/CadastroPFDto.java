package br.com.eps.pontointeligente.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import java.util.Optional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastroPFDto {
	
	private Long id;
	
	@NotBlank(message = "O campo Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "O campo Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@NotBlank(message = "O campo E-mail não pode ser vazio.")
	@Length(min = 3, max = 200, message = "O campo E-mail deve conter entre 3 e 200 caracteres.")
	@Email(message = "E-mail inválido.")
	private String email;
	
	@NotBlank(message = "O campo Senha não pode ser vazio.")
	private String senha;
	
	@NotBlank(message = "O campo CPF não pode ser vazio.")
	@CPF(message = "CPF inválido.")
	private String cpf;
	
	@NotBlank(message = "O campo CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;
	
	private Optional<String> valorHora = Optional.empty();
	
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	
	private Optional<String> qtdHorasAlmoco = Optional.empty();
	
	public CadastroPFDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	public void setQtdHorasTrabalhoDia(Optional<String> qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@Override
	public String toString() {
		return "CadastroPFDto [id=" + id + ", nome=" + nome + ", email="
				+ email + ", senha=" + senha + ", cpf=" + cpf
				+ ", cnpj=" + cnpj + ", valorHora=" + valorHora
				+ ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia
				+ ", qtdHorasAlmoco=" + qtdHorasAlmoco + "]";
	}
}
