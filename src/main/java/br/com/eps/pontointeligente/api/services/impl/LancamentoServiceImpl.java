package br.com.eps.pontointeligente.api.services.impl;

import br.com.eps.pontointeligente.api.entity.Lancamento;
import br.com.eps.pontointeligente.api.repository.LancamentoRepository;
import br.com.eps.pontointeligente.api.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@CachePut("lancamentoPorId")
	@Override
	public Lancamento persistirLancamento(Lancamento lancamento) {
		log.info("Persistindo um Lancamento {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void removerLancamento(Long idLancamento) {
		log.info("Removendo o Lancamento {}", idLancamento);
		this.lancamentoRepository.deleteById(idLancamento);
	}

	@Override
	public Page<Lancamento> buscarPorIdFuncionario(Long idFuncionario, PageRequest pageRequest) {
		log.info("Buscar lançamentos paginados pelo id do funcionario {}", idFuncionario);
		return this.lancamentoRepository.findByFuncionarioIdFuncionario(idFuncionario, pageRequest);
	}

	@Cacheable("lancamentoPorId")
	@Override
	public Optional<Lancamento> buscarPorIdLancamento(Long idLancamento) {
		log.info("Buscar lançamento pelo id do lançamento {}", idLancamento);
		return Optional.ofNullable(this.lancamentoRepository.findById(idLancamento).orElse(null));
	}
	
	public List<Lancamento> buscarTodosPorFuncionarioId(Long funcionarioId) {
		log.info("Buscando todos os lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioIdFuncionarioOrderByDataLancamentoDesc(funcionarioId);
	}
	
	@Override
	public Optional<Lancamento> buscarUltimoPorFuncionarioId(Long funcionarioId) {
		log.info("Buscando o último lançamento por ID de funcionário {}", funcionarioId);
		return Optional.ofNullable(
				this.lancamentoRepository
				.findFirstByFuncionarioIdFuncionarioOrderByDataCriacaoDesc(funcionarioId));
	}
}
