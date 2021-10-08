package br.com.eps.pontointeligente.api.services.impl;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.repository.FuncionarioRepository;
import br.com.eps.pontointeligente.api.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	@Override
	public Funcionario persistirFuncionario(Funcionario funcionario) {
		log.info("Persistindo um Funcionário {}", funcionario);
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
		log.info("Buscando um Funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarFuncionarioPorEmail(String email) {
		log.info("Buscando um Funcionário pelo e-mail {}", email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long idFuncionario) {
		log.info("Buscando um Funcionário pelo id {}", idFuncionario);
		return Optional.ofNullable(this.funcionarioRepository.getOne(idFuncionario));
	}

	@Override
	public List<Funcionario> buscarPorIdEmpresa(Long idEmpresa) {
		log.info("Buscando funcionários pela empresa ID {}", idEmpresa);
		return funcionarioRepository.findByEmpresaId(idEmpresa);
	}

}
