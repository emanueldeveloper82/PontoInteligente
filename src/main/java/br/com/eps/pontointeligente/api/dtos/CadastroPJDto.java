package br.com.eps.pontointeligente.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastroPJDto {
	
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
		
	@NotBlank(message = "O campo Razão Social não pode ser vazio.")
	@Length(min = 5, max = 200, message = "O campo Razão Social deve conter entre 5 e 200 caracteres.")
	private String razaoSocial;
	
	@NotBlank(message = "O campo CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;

	public CadastroPJDto(Long id, String nome, String email, String senha, String cpf,
						 String razaoSocial, String cnpj) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	public CadastroPJDto() {
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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPJDto [id=" + id + ", nome=" + nome
				+ ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}
	

}
