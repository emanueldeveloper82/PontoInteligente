package br.com.eps.pontointeligente.api.services.impl;

import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.repository.EmpresaRepository;
import br.com.eps.pontointeligente.api.services.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorNumCnpj(String numCnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", numCnpj);
		return Optional.ofNullable(empresaRepository.findByNumCnpj(numCnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo uma empresa {}", empresa);
		return this.empresaRepository.save(empresa);
	}

}
