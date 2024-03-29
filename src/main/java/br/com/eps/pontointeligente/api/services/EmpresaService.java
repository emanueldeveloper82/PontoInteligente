package br.com.eps.pontointeligente.api.services;

import br.com.eps.pontointeligente.api.entity.Empresa;
import java.util.Optional;

public interface EmpresaService {
	
	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorNumCnpj(String numCnpj);
	
	
	/**
	 * 
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}
