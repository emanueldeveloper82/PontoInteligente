package br.com.eps.pontointeligente.api.services;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import java.util.List;
import java.util.Optional;

public interface FuncionarioService {
	
	/**
	 * Persistindo um funcionário.
	 * 
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistirFuncionario(Funcionario funcionario);
	
	/**
	 * Buscando um funcionário pelo número do CPF.
	 * 
	 * @param numCpf
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarFuncionarioPorCpf(String numCpf);
	
	/**
	 * Buscando um funcionário pelo E-mail.
	 * 
	 * @param email
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarFuncionarioPorEmail(String email);	
	
	/**
	 * Buscando um funcionário pelo id. <br/>
	 * Trocar o nome para findById
	 * 
	 * @param idFuncionario
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorId(Long idFuncionario);
	
	
	/**
	 * Busca funcionario por empresa.
	 * @param idEmpresa
	 * @return List<Funcionario> 
	 */
	List<Funcionario> buscarPorIdEmpresa(Long idEmpresa);

	
	
	
	
	
	
	
	

}
