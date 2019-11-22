package br.com.eps.pontointeligente.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eps.pontointeligente.api.entity.Funcionario;


@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	public Funcionario findByCpf(String numCpf);
	
	public Funcionario findByEmail(String email);
	
	public Funcionario findByCpfOrEmail(String numCpf, String email);
	
	

}
