package br.com.eps.pontointeligente.api.services.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eps.pontointeligente.api.entity.Lancamento;
import br.com.eps.pontointeligente.api.repository.LancamentoRepository;
import br.com.eps.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
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

	@Override
	public Optional<Lancamento> buscarPorIdLancamento(Long idLancamento) {
		log.info("Buscar lançamento pelo id do lançamento {}", idLancamento);
		return Optional.ofNullable(this.lancamentoRepository.findById(idLancamento).orElse(null));
	}
}
