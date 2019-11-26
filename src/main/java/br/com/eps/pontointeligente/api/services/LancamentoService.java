package br.com.eps.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.eps.pontointeligente.api.entity.Lancamento;

public interface LancamentoService {
	
	/**
	 * Persistir um Lançamento no banco de dados.
	 * 
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento persistirLancamento(Lancamento lancamento);
	
	/**
	 * Remover um Lançamento no banco de dados.
	 * 
	 * @param lancamento
	 * 
	 */
	void removerLancamento(Long idLancamento);
	
	
	/**
	 * Retornar uma lista paginada de lançamentos pelo id do funcionário.
	 * 
	 * @param idFuncionario
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> buscarPorIdFuncionario(Long idFuncionario, PageRequest pageRequest);
	
	/**
	 * Buscar um lançamento pelo seu id.
	 * 
	 * @param idLancamento
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorIdLancamento(Long idLancamento);

}
